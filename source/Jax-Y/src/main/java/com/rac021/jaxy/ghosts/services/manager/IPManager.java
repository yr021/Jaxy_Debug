
package com.rac021.jaxy.ghosts.services.manager;

import java.net.InetSocketAddress;
import java.net.Socket;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author ryahiaoui
 */

@Singleton
@Startup
@ApplicationScoped
public class IPManager {
    
   public static String IP_ADRESS = ""   ;
   
   @PostConstruct
   public void init()            {
      IP_ADRESS = getIPAdresse() ;
   }
   
   public static String getIPAdresse()                                     {

      try ( final Socket socket = new Socket())  {
          socket.connect(new InetSocketAddress("google.com", 80))          ;
          return socket.getLocalAddress().toString().replace("/", "") ;
      } catch( Exception ex )        {
          ex.printStackTrace()       ;
          IP_ADRESS = "IP_EXCEPTION" ;
          return IP_ADRESS ;
      }
   }
    
}
    
