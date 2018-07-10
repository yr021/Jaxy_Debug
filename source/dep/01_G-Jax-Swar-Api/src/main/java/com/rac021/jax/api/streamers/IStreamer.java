
package com.rac021.jax.api.streamers ;

import javax.ws.rs.core.MultivaluedMap ;
import com.rac021.jax.api.manager.IResource ;

/**
 *
 * @author yahiaoui
 */

public interface IStreamer {

   void setStreamerConfigurator( IStreamerConfigurator iStreamerConfigurator ) ;
   
   public IStreamer wrapResource(  IResource resource       , 
                                    Class     dto            ,
                                    String    filteredNmames ,
                                    MultivaluedMap<String, String> ... sqlParams ) ;
   
}
