package com.rac021.jax.api.utils ;

import java.util.concurrent.Callable ;
import com.rac021.jax.api.exceptions.BusinessException ;

/**
 *
 * @author ryahiaoui
 */
public class EnumUtils {

    public static <T extends Enum<?>> T searchEnum( Class<T> enumeration ,
                                                    String search        ,
                                                    String enumName ) throws BusinessException {
      if ( search == null) {
         throw new BusinessException( " CipherTypes  [  NULL ] "
                                      + " not supported !  " ) ;
      }
      if ( search.trim().isEmpty() ) {
         throw new BusinessException( " CipherTypes  [  EMPTY ] "
                                      + " not supported !  " ) ;
      }
      for (T each : enumeration.getEnumConstants())                {
          if (each.name().compareToIgnoreCase(search.trim()) == 0) {
              return each ;
          }
      }
        
      throw new BusinessException(" " + enumName + "  [ " + search + " ] "
              + " doesn't exists ! ") ;
        
    }

    public static <T> T uncheckCall(Callable<T> callable) {
        try {
            return callable.call()   ;
        } catch (RuntimeException e) {
            throw e ;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }
}
