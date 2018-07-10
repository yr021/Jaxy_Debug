
package com.rac021.jax.api.streamers ;

import com.rac021.jax.api.crypto.AcceptType;
import java.util.Map ;
import java.util.List ;
import java.io.Writer ;
import java.util.Objects ;
import java.io.IOException ;
import java.io.OutputStream ;
import java.io.BufferedWriter ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.io.OutputStreamWriter ;
import java.util.concurrent.TimeUnit ;
import java.io.ByteArrayOutputStream ;
import javax.ws.rs.core.MultivaluedMap ;
import com.rac021.jax.api.manager.IDto ;
import javax.ws.rs.core.StreamingOutput ;
import com.rac021.jax.api.security.ISignOn ;
import com.rac021.jax.api.qualifiers.Format ;
import com.rac021.jax.api.manager.IResource ;
import com.rac021.jax.api.exceptions.BusinessException ;
import static com.rac021.jax.api.manager.TemplateManager.* ;
import static com.rac021.jax.api.streamers.DefaultStreamerConfigurator.* ;
import static com.rac021.jax.api.manager.DtoMapper.extractValuesFromObject ;

/**
 *
 * @author yahiaoui
 */

@Format(AcceptType.TEMPLATE_PLAIN)
public class StreamerOutputTemplate extends Streamer implements StreamingOutput {

    public StreamerOutputTemplate() {
    }

    @Override
    public void write(OutputStream output) throws IOException {
        
       System.out.println(" Processing data in StreamerOutputTemplate ... ") ;
       
       configureStreamer() ;

       /* Submit Producers */
       poolProducer.submit( () -> producerScheduler() ) ;      
      
       Writer writer = new BufferedWriter ( new OutputStreamWriter(output, "UTF8")) ;

       ByteArrayOutputStream baoStream = new ByteArrayOutputStream() ;

       String template = 
       servicesManager.getTemplate(ISignOn.SERVICE_NAME.get())               ;
       
       Objects.requireNonNull(template)                                      ;
       
       String templateBody        = removeMultipleBlankSpaces(
                                                 extractBody  ( template ))  ;
      
       String templateHeader      = extractHeader( template )                ;
       
       String templateFooter      = extractFooter( template )                ;
       
       List<String> extractedArgs = extractArgs  ( templateBody )            ;
 
       if( templateHeader != null && ! templateHeader.trim().isEmpty() )     {
           writer.write( templateHeader + "\n") ;
       }
       
       if( templateBody == null || templateBody.isEmpty() )      {

          writer.write( " \n Empty Template Generated ! \n\n" ) ;
          writer.flush()                                         ;
          writer.close()                                         ;
       } 

       else try {

            int iteration          =  0                             ;
            
            while ( !isFinishedProcess || !dtos.isEmpty() )         {

                IDto poll = dtos.poll( 50 , TimeUnit.MILLISECONDS)  ;
                   
                if( poll != null ) {
                    
                    Map<String, String> ext = extractValuesFromObject( poll            , 
                                                                       poll.getClass() , 
                                                                       extractedArgs ) ;
                    
                    writer.write( applyValue( ext, templateBody + "\n" ))    ;
                    
                    baoStream.reset()                                        ;
                    iteration ++                                             ;
                      
                    if (iteration % responseCacheSize == 0 )  writer.flush() ;
                }
             }

             if( templateFooter != null && ! templateFooter.isEmpty() ) {
                writer.write( templateFooter + "\n" ) ;
             }
             
             writer.flush()         ;
             writer.close()         ;
             baoStream.close()      ;

        } catch (IOException ex) {
            
            if (ex.getClass().getName().endsWith(".ClientAbortException")) {
                try {
                    writer.close()    ;
                    baoStream.close() ;
                    throw new BusinessException("ClientAbortException !! " + ex.getMessage()) ;
                } catch (IOException | BusinessException ex1) {
                      Logger.getLogger(StreamerOutputTemplate.class.getName()).log(Level.SEVERE, null, ex1) ;
                }
            } else {
                try {
                    writer.close()    ;
                    baoStream.close() ;
                    throw new BusinessException("Exception : " + ex.getMessage(), ex) ;
                } catch (IOException | BusinessException ex1) {
                    Logger.getLogger(StreamerOutputTemplate.class.getName()).log(Level.SEVERE, null, ex1) ;
                }
            }
            
            isFinishedProcess = true ;
            
        }   catch (InterruptedException ex) {
            Logger.getLogger(StreamerOutputJson.class.getName()).log(Level.SEVERE, null, ex) ;
        }
    }

    public ResourceWraper getResource() {
        return resource ;
    }
 
    @Override
    public StreamerOutputTemplate wrapResource( IResource resource      , 
                                                 Class     dto            ,
                                                 String    filteredNmames ,
                                                 MultivaluedMap<String, String> ... sqlParams ) {

        rootResourceWraper( resource, dto, filteredNmames, sqlParams ) ;
        
        return this ;
    }
    
    public StreamerOutputTemplate wrapResource( IResource resource , Class dto ) {

        rootResourceWraper( resource, dto, null ) ;        
        return this                               ;
    }
}
