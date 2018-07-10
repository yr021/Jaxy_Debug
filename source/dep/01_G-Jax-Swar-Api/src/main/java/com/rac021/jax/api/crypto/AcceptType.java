
package com.rac021.jax.api.crypto ;

import java.util.List ;
import java.util.Arrays ;
import java.util.ArrayList ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.util.stream.Collectors ;
import com.rac021.jax.api.exceptions.BusinessException ;

/**
 *
 * @author ryahiaoui
 */

public enum AcceptType {
    
    XML_PLAIN          ("xml/plain"     )     ,
    XML_ENCRYPTED      ("xml/encrypted" )     ,
    JSON_PLAIN         ("json/plain"    )     ,
    JSON_ENCRYPTED     ("json/encrypted")     ,
    TEMPLATE_PLAIN     ("template/plain")     ,
    TEMPLATE_ENCRYPTED ("template/encrypted") ,
    TEXT_EVENT__STREAM ("text/event-stream")  ;
    
    private final String name         ;

    private AcceptType( String name)  {
        this.name = name ;
    }
    
    public static List<AcceptType> toList( List<String> list ) {
     
       try {
           return list.stream()
                      .map( accept_t -> AcceptType.valueOf( accept_t.trim()
                                                  .replace("-", "__"  )
                                                  .replace("/", "_")) )
                      .collect(Collectors.toList()) ;
       } catch( Exception ex ) {
           try {
           throw new BusinessException( " AcceptType List [ " + list + " ] "
                                        + "not supported ! \n "
                                        + "// Cause :" + ex.getCause()  )    ;
           } catch( BusinessException ex1 ) {
             Logger.getLogger(AcceptType.class.getName())
                                              .log(Level.SEVERE, null, ex1 )  ;   
             Logger.getLogger(AcceptType.class.getName())
                                              .log(Level.SEVERE, null, ex ) ;   
           }
       }
       return null ;
        
    }
    
    public static AcceptType toAcceptTypes( String acceptType ) throws BusinessException  {
       
       if( acceptType == null ) {
         throw new BusinessException( " AcceptType [  NULL ] not supported ! " ) ;
      }
      try {
            return  AcceptType.valueOf( acceptType.trim().replace("-", "__")
                                                         .replace("/", "_") ) ;
      } catch( Exception ex ) {
         
         Logger.getLogger(AcceptType.class.getName())
                                    .log(Level.SEVERE, null, ex  )   ;
         throw new BusinessException( " AcceptType  [ " + acceptType + 
                                      " ] not supported ! " )        ;
      }
    }
    
    
    public static List<AcceptType> getPlainTypes() {
       return new ArrayList( Arrays.asList( AcceptType.XML_PLAIN    ,
                                            AcceptType.JSON_PLAIN)) ;
    }
    
    public static List<AcceptType> getEncryptedTypes() {
       return new ArrayList( Arrays.asList( AcceptType.XML_ENCRYPTED    ,
                                             AcceptType.JSON_ENCRYPTED)) ;
    }
    
    public static List<AcceptType> getPlainTemplate() {
       return new ArrayList( Arrays.asList( AcceptType.TEMPLATE_PLAIN ) ) ;
    }
    
    public static List<AcceptType> getEncryptedTemplate() {
       return new ArrayList( Arrays.asList( AcceptType.TEMPLATE_ENCRYPTED )) ;
    }
    
}
