
package com.rac021.jax.api.streamers ;

import com.rac021.jax.api.crypto.AcceptType;
import java.util.Map ;
import java.util.List ;
import java.io.Writer ;
import java.util.Queue ;
import java.util.Arrays ;
import java.util.Base64 ;
import java.util.Objects ;
import java.io.IOException ;
import java.util.LinkedList ;
import java.io.OutputStream ;
import java.io.BufferedWriter ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.io.OutputStreamWriter ;
import java.io.ByteArrayOutputStream ;
import java.util.concurrent.TimeUnit ;
import javax.ws.rs.core.MultivaluedMap ;
import com.rac021.jax.api.manager.IDto ;
import javax.ws.rs.core.StreamingOutput ;
import com.rac021.jax.api.crypto.ICryptor ;
import com.rac021.jax.api.security.ISignOn ;
import org.apache.commons.lang3.ArrayUtils ;
import javax.ws.rs.WebApplicationException ;
import com.rac021.jax.api.qualifiers.Format ;
import com.rac021.jax.api.manager.IResource ;
import com.rac021.jax.api.crypto.EncDecRyptor ;
import com.rac021.jax.api.crypto.FactoryCipher ;
import com.rac021.jax.api.exceptions.BusinessException ;
import static com.rac021.jax.api.manager.TemplateManager.applyValue;
import static com.rac021.jax.api.manager.TemplateManager.extractArgs;
import static com.rac021.jax.api.manager.TemplateManager.extractBody;
import static com.rac021.jax.api.manager.TemplateManager.extractFooter;
import static com.rac021.jax.api.manager.TemplateManager.extractHeader;
import static com.rac021.jax.api.streamers.DefaultStreamerConfigurator.* ;
import static com.rac021.jax.api.manager.DtoMapper.extractValuesFromObject ;
import static com.rac021.jax.api.manager.TemplateManager.removeMultipleBlankSpaces ;

/**
 *
 * @author yahiaoui
 */

@Format(AcceptType.TEMPLATE_ENCRYPTED)
public class StreamerOutputTemplateEncrypted extends Streamer implements StreamingOutput {

    public StreamerOutputTemplateEncrypted() { }

    @Override
    public void write(OutputStream output) throws IOException {
        
        System.out.println(" Processing data in StreamerOutputTemplateEncrypted ... ") ;

        if ( ISignOn.ENCRYPTION_KEY == null ) 
          throw new WebApplicationException(" Error Key can't be NULL " ) ;
        
        configureStreamer() ;

        /* Submit Producers */
        poolProducer.submit( () -> producerScheduler() ) ;      

        Writer writer  = new BufferedWriter( new OutputStreamWriter(output, "UTF8")) ;
      
        ICryptor crypt = null ;
      
        try {
            
          crypt = FactoryCipher.getCipher ( ISignOn.CIPHER.get()           , 
                                            ISignOn.ENCRYPTION_KEY.get() ) ;
          Objects.requireNonNull(crypt)                                    ;
          
        } catch( BusinessException ex ) {
           writer.write(" Exception  : Something went wrong // " + ex.getMessage()        ) ;
           writer.write(" \n" ) ;
           writer.write(" MediaType  : TEMPLATE/ENCRYPTED "                               ) ;
           writer.write(" \n" ) ;
           writer.write(" Specify accept header in the Request if it's not already done " ) ;
           writer.write(" \n" ) ;
           writer.flush() ; 
           writer.close() ;
        }
         
        crypt.setOperationMode(EncDecRyptor._Operation.Encrypt )             ;
        
        ByteArrayOutputStream baoStream        = new ByteArrayOutputStream() ;
        Queue<Byte>           qeueBytes        = new LinkedList<>()          ;
        StringBuilder         plainTextBuilder = new StringBuilder()         ;
        ByteArrayOutputStream outString        = new ByteArrayOutputStream() ;
        
        String template = 
        servicesManager.getTemplate(ISignOn.SERVICE_NAME.get())              ;
       
        if( template == null ) {
            
            try {
                throw new BusinessException(" Exception  : No template found for "
                        + "the service [ "+ISignOn.SERVICE_NAME.get() + " ]  ")  ;
                
            } catch (BusinessException ex) {
                Logger.getLogger( StreamerOutputTemplateEncrypted
                      .class.getName()).log(Level.SEVERE, null, ex) ;
            }
        }
        Objects.requireNonNull(template)                                     ;
       
        String templateBody        = removeMultipleBlankSpaces (
                                                  extractBody  ( template )) ;
      
        String templateHeader      = extractHeader( template )               ;
       
        String templateFooter      = extractFooter( template )               ;
        
        List<String> extractedArgs = extractArgs  ( templateBody )           ;
       
        /* Send in response the Vector Initialization */
        if( crypt.getIvBytesEncoded64() != null ) {
            writer.write(new String( crypt.getIvBytesEncoded64() ) + "." ) ;
            writer.flush()  ;
        }

       if( templateHeader != null && ! templateHeader.trim().isEmpty() )     {
           plainTextBuilder.append(templateHeader).append("\n") ;
       }
 
        int nbrBlocks = 0   ;

        try {
            
            int        iteration  = 0                                ;
            
            while ( ! isFinishedProcess || !dtos.isEmpty()) {
                
                   IDto poll = dtos.poll( 50, TimeUnit.MILLISECONDS) ;
       
                   if( poll != null ) {
                       
                      Map<String, String> ext = extractValuesFromObject( poll            , 
                                                                         poll.getClass() , 
                                                                         extractedArgs ) ;
                    
                      plainTextBuilder.append(applyValue( ext, templateBody + "\n" )) ;
                      iteration ++                                  ;
                      baoStream.reset()                             ;
                            
                      if (iteration % responseCacheSize == 0 ) {
                          
                        nbrBlocks = (plainTextBuilder.length() / blockSize) ;

                        if ((plainTextBuilder.length() % blockSize > 0 ) && (nbrBlocks >= 1)) {

                            qeueBytes.addAll(Arrays.asList ( ArrayUtils.toObject (
                                                             crypt.process ( plainTextBuilder
                                                                  .substring( 0 , nbrBlocks * blockSize ) , 
                                                                   EncDecRyptor._CipherOperation.update ) ) 
                                                            ) ) ;
                            
                            plainTextBuilder.delete(0, nbrBlocks * blockSize) ;
                            
                        } else if ( nbrBlocks > 1 ) {
                            
                                    qeueBytes.addAll(Arrays.asList (
                                            ArrayUtils.toObject (
                                            crypt.process ( 
                                                     plainTextBuilder.substring(0, (nbrBlocks - 1) * blockSize) ,
                                                     EncDecRyptor._CipherOperation.update ) ) 
                                     ) ) ;
                                    
                                    plainTextBuilder.delete(0, (nbrBlocks - 1) * blockSize) ;
                        }

                        while ((qeueBytes.size() / 3) >= 1)      {
                               outString.write(qeueBytes.poll()) ;
                               outString.write(qeueBytes.poll()) ;
                               outString.write(qeueBytes.poll()) ;
                        }

                        writer.write(new String(Base64.getEncoder().encode(outString.toByteArray()))) ;
                        writer.flush()    ;
                        baoStream.reset() ;
                        outString.reset() ;
                        iteration = 0     ;
                      }
                    
                   }
            }

            if( templateFooter != null && ! templateFooter.trim().isEmpty() )     {
               plainTextBuilder.append(templateFooter).append("\n") ;
            }
            
            qeueBytes.addAll ( Arrays.asList( ArrayUtils.toObject(crypt.process (
                                              plainTextBuilder.toString() ,
                                              EncDecRyptor._CipherOperation.dofinal ) ) 
            ) ) ;
 
            while (!qeueBytes.isEmpty())          {
                outString.write(qeueBytes.poll()) ;
            }

            writer.write(new String(Base64.getEncoder().encode(outString.toByteArray() ) ) ) ;

            writer.flush()    ;
            writer.close()    ;

            plainTextBuilder.setLength(0) ;
            
            baoStream.close() ;
            outString.close() ;

        } catch (IOException ex) {
            
            if (ex.getClass().getName().endsWith(".ClientAbortException")) {
                
                try {
                    writer.close()    ;
                    baoStream.close() ;
                    outString.close() ;
                    throw new BusinessException("ClientAbortException !! " + ex.getMessage(), ex) ;
                } catch (IOException | BusinessException ex1) {
                    System.out.println( ex1.getMessage() ) ; 
                }
            } else {
                try {
                    writer.close()    ;
                    baoStream.close() ;
                    outString.close() ;
                    throw new BusinessException("Exception : " + ex.getMessage()) ;
                } catch (IOException | BusinessException ex1) {
                    System.out.println( ex1.getMessage() )    ;
                }
            }
            
            isFinishedProcess = true ;
            
        } catch (InterruptedException ex) {
            Logger.getLogger(StreamerOutputTemplateEncrypted.class.getName()).log(Level.SEVERE, null, ex) ;
        }
    }

    public ResourceWraper getResource() {
        return resource ;
    }

    @Override
    public StreamerOutputTemplateEncrypted wrapResource( IResource resource    , 
                                                          Class dto             ,
                                                          String filteredIndexs ,
                                                          MultivaluedMap<String , String> ... sqlParams ) {
      rootResourceWraper(resource, dto, filteredIndexs, sqlParams) ;
      return this ;
    }

    public StreamerOutputTemplateEncrypted wrapResource( IResource resource , Class dto ) {
        rootResourceWraper(resource, dto, null ) ;        
        return this ;
    }

}
