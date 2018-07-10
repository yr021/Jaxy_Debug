
package com.rac021.jaxy.service.time ;

/**
 *
 * @author ryahiaoui
 */

import javax.ws.rs.GET ;
import java.time.Instant ;
import javax.ws.rs.Produces ;
import javax.ws.rs.core.Response ;
import javax.annotation.PostConstruct ;
import com.rac021.jax.api.qualifiers.ServiceRegistry ;
import org.eclipse.microprofile.metrics.annotation.Counted ;

/**
 *
 * @author R.Yahiaoui
 */

@ServiceRegistry("time")
public class ServiceTime    {
 
    @PostConstruct
    public void init() {
    }

    public ServiceTime() {
    }
    
    @GET
    @Produces( { "xml/plain" , "json/plain" } )
    @Counted(name = "countServiceTime", absolute = true, reusable = true, monotonic = true)
    public Response getTime ( ) {    
      
      return Response.status( Response.Status.OK )
                     .entity( String.valueOf(Instant.now().toEpochMilli()) )
                     .build() ;
    }
}
