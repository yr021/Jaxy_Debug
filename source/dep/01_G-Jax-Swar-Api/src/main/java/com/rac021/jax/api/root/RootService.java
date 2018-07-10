
package com.rac021.jax.api.root ;

import javax.ws.rs.Path ;
import javax.inject.Inject ;
import javax.ws.rs.PathParam ;
import javax.ws.rs.HeaderParam ;
import javax.enterprise.inject.Any ;
import javax.annotation.PostConstruct ;
import javax.enterprise.inject.Instance ;
import com.rac021.jax.api.security.ISignOn ;
import com.rac021.jax.api.crypto.CipherTypes ;
import com.rac021.jax.api.crypto.JceSecurity ;
import javax.enterprise.util.AnnotationLiteral ;
import javax.enterprise.context.ApplicationScoped ;
import com.rac021.jax.api.qualifiers.security.Custom ;
import com.rac021.jax.api.qualifiers.security.Policy ;
import com.rac021.jax.api.exceptions.BusinessException ;

/**
 * REST Web Service
 *
 * @author yahiaoui
 */

@Path(RootService.PATH_RESOURCE)
@ApplicationScoped

public class RootService implements IRootService {

    public static final String LOGIN         = "{login}"          ;

    public static final String SIGNATURE     = "{signature}"      ;

    public static final String TIMESTAMP     = "{timeStamp}"      ;

    public static final String SERVICENAME   = "{_service_Name_}" ;

    public static final String SERVICENAME_P = "_service_Name_"   ;

    public static final String PATH_RESOURCE = "/resources"       ;

    public static final String SEPARATOR     = "/"                ;
    
    @Inject 
    ServicesManager servicesManager ;

    
    @Inject @Any
    private Instance<ISignOn> signOn ;

    public RootService()   {
    }

   @PostConstruct
   public void init()       {
      JceSecurity.unlimit() ;
   }

    @Override
    @Path( SERVICENAME )
    public Object subResourceLocators( @HeaderParam("API-key-Token")   String token   ,
                                         @HeaderParam("Accept")          String accept  ,
                                         @HeaderParam("Cipher")          String cipher  ,
                                         @PathParam(SERVICENAME_P) final String codeService ) throws BusinessException {
  
        return root (codeService, accept, token, cipher) ;
    }

    private Object root ( final String codeService , 
                          final String accept      ,
                          final String token       , 
                          final String cipher) throws BusinessException {
        
        Policy policy = servicesManager.contains(codeService) ;
        
        if( policy == null ) throw new BusinessException("Unavailable Service")    ;
        
        if( servicesManager.getTemplate(codeService) == null &&
                accept != null && accept.contains("template") )   {
            throw new BusinessException(" No Template assigned to the service "
                    + "[ " +codeService + " ] ")          ;
        }
            
        if( policy == Policy.Public )                             {
            
            if( accept != null && accept.contains("encrypted") )  {
                throw new BusinessException(" Public Services can't be Encrypted ") ;
            }
           
            ISignOn.SERVICE_NAME.set( codeService )   ;
            return servicesManager.get(codeService )  ;
        }
        
        if( policy == Policy.SSO ) {
            ISignOn.SERVICE_NAME.set( codeService )   ;
            return servicesManager.get(codeService)   ;
        }

        /* The following need Authentication */
          
        if( accept != null && accept.contains("encrypted") && token == null )
            throw new BusinessException(" Header [ API-key-Token ] can't be NULL for secured services ") ;
      
        if( token == null )  throw new BusinessException( " Authentication Required. "
                + "Missing Header [ API-key-Token ] ") ;
        
        if( ! servicesManager.containAcceptForService( codeService, accept ))   {
            throw new BusinessException (" The service [ " + codeService
                    + " ] Doesn't authorize Accept Header "
                            + "[ " + accept + " ] " )             ;
        }
        if( ! servicesManager.containCiphersForService( codeService, cipher) 
              && accept != null  && accept.contains("crypt")  )            {
            throw new BusinessException ( " The service [ " + codeService + " ] Doesn't authorize "
                    + "[ " + cipher + " ] Cipher ") ;
        }
        
        if( policy == Policy.CustomSignOn ) {

            if( signOn.select(new AnnotationLiteral<Custom>() {}).get() == null ) {
                throw new BusinessException(" No Provider found for Custom Authentication ") ;
            }
            
            if ( signOn.select(new AnnotationLiteral<Custom>() {}).get()
                    .checkIntegrity (
                            token,
                            signOn.select(new AnnotationLiteral<Custom>() {})
                                    .get().getConfigurator()
                                    .getValidRequestTimeout() ) ) {
                if( cipher == null ) {
                    System.out.println(" -- Default cipher : " + CipherTypes.AES_128_ECB.name()) ;
                    ISignOn.CIPHER.set( CipherTypes.AES_128_ECB.name() ) ;
                }
                else {
                    ISignOn.CIPHER.set(cipher.trim())   ; 
                }
                
                ISignOn.SERVICE_NAME.set( codeService ) ;
                return servicesManager.get(codeService) ;
            }
        }
       
        throw new BusinessException(" Unauthorized Resource ") ;
    }

    @Override
    @Path(LOGIN + SEPARATOR + SIGNATURE + SEPARATOR + TIMESTAMP )
    public Object authenticationCheck( @PathParam("login")     final String login     ,
                                       @PathParam("signature") final String signature ,
                                       @PathParam("timeStamp") final String timeStamp) throws BusinessException   {

        if ( signOn.select(new AnnotationLiteral<Custom>() {}).get().checkIntegrity(login, timeStamp, signature)) {
            throw new BusinessException("OK_Authorization") ;
        }
        throw new BusinessException("KO_Authorization")     ;
    }

}
