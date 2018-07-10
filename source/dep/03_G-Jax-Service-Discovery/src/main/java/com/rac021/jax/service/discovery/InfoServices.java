
package com.rac021.jax.service.discovery ;

import java.util.Set ;
import java.util.Map ;
import java.util.List ;
import javax.ws.rs.GET ;
import java.util.HashMap ;
import java.util.HashSet ;
import javax.inject.Inject ;
import java.util.ArrayList ;
import javax.ws.rs.Produces ;
import javax.inject.Singleton ;
import java.lang.reflect.Field ;
import javax.xml.bind.Marshaller ;
import javax.ws.rs.core.Response ;
import javax.xml.bind.JAXBContext ;
import java.util.stream.Collectors ;
import javax.xml.bind.JAXBException ;
import java.io.ByteArrayOutputStream ;
import com.rac021.jax.api.pojos.Query ;
import javax.annotation.PostConstruct ;
import com.rac021.jax.api.streamers.EmptyPojo ;
import com.rac021.jax.api.root.ServicesManager ;
import com.rac021.jax.api.qualifiers.security.Policy ;
import com.rac021.jax.api.qualifiers.ServiceRegistry ;
import com.rac021.jax.api.qualifiers.ResourceRegistry ;
import org.eclipse.persistence.jaxb.MarshallerProperties ;

/**
 *
 * @author R.Yahioaui
 */

@Singleton
@ServiceRegistry("infoServices")
public class InfoServices {

    @Inject 
    ServicesManager servicesManager ;

    private List<ServiceDescription> services  = null ;
    private ByteArrayOutputStream    baoStream = null ;
    
    @PostConstruct
    public void init() {
    }

    public InfoServices() {
    }
   
    @GET
    /* Return Always JSON for all Produces */
    @Produces( {"json/plain", "xml/plain" , "json/encrypted", "xml/encrypted"} )
    public Response getResourceJson() throws Exception      {    
        return produceInfoServices() ;
    }

    private Response produceInfoServices() throws Exception {

       return  Response.ok( scannServices()).build()      ;        
    }
    
    public String scannServices()  throws  Exception    {
    
        if( services == null ) {
       
           services = extractServices()                  ;
 
           baoStream = new ByteArrayOutputStream()       ;

           Marshaller marshaller = getMashellerWithJsonProperties() ;
                      marshaller.marshal(services, baoStream )      ;
            
            return baoStream.toString("UTF8")             ;
        }
        
        return baoStream != null ? baoStream.toString("UTF8") : "" ;
    }
    
    
    public List<ServiceDescription> extractServices() {
    
        if( services != null ) return services          ;
        
        List<ServiceDescription> _services = new ArrayList<>() ;
        
        servicesManager.getMapOfAllSubServices().forEach( ( String k, Object v) -> {

            String serviceName = k ;
            Object service     = v ;
                
            String security    = servicesManager.contains(k).name() ;
                
            Field resourceField = servicesManager.getFieldFor( service.getClass(),
                                                               ResourceRegistry.class ) ;

            Map<String, String > params = new HashMap<>()           ;
            
            if( resourceField != null ) {
                    
                List<Query> queriesByResourceName = servicesManager.getQueriesByResourceName(
                                                    resourceField.getGenericType().getTypeName()) ;
                
                if( queriesByResourceName != null ) {
                    queriesByResourceName.get(0).getParameters().forEach( (s, t ) -> {
                        params.put(s, t.get("TYPE").replace("java.lang.", "")) ;
                    }) ;
                }
            }
            
            Set<String> ciphers  = new HashSet() ;
            ciphers.add(" - ")                   ;
                    
            if( ! security.equalsIgnoreCase(Policy.Public.name())) {
                 ciphers = servicesManager.getCiphersForServiceName(serviceName)
                                          .stream()
                                          .map( cipher -> cipher.name())
                                          .collect(Collectors.toSet()) ;
            }
                    
            Set<String>  accepts = servicesManager.getAcceptForServiceName(serviceName)
                                                  .stream()
                                                  .map( accept -> accept.name())
                                                  .collect(Collectors.toSet()) ;
                    
            accepts =  accepts.stream()
                              .map( accept -> accept.toLowerCase()
                                                    .replace("_", "/")
                                                    .replace("-", "__"))
                              .collect(Collectors.toSet()) ;
                            
            _services.add( new ServiceDescription( serviceName , 
                                                   security    , 
                                                   params      , 
                                                   ciphers     ,
                                                   accepts     ,
                                                   servicesManager.
                                                    getOrDefaultMaxThreadsFor (
                                                              serviceName) ,
                                                   servicesManager.
                                                    getTemplate(serviceName))) ;
        }) ;
        
        return _services ;
    }
    
    private javax.xml.bind.Marshaller getMashellerWithJsonProperties()  {
        
        Marshaller localMarshaller = null ;
        JAXBContext jc                    ;
        try {
            jc = JAXBContext.newInstance( ServiceDescription.class, EmptyPojo.class)                   ;
            localMarshaller = jc.createMarshaller()                                                    ;
            localMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json")           ;
            localMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, Boolean.FALSE)         ;
            localMarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE) ;
        } catch (JAXBException ex) {
            ex.printStackTrace() ;
        }
        return localMarshaller ;
    }
     
}
