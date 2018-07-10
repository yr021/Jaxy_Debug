
package com.rac021.jax.api.crypto.algos ;

import java.util.Base64 ;
import javax.crypto.Cipher ;
import javax.crypto.SecretKey ;
import javax.crypto.SecretKeyFactory ;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec ;
import com.rac021.jax.api.crypto.EncDecRyptor ;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author ryahiaoui
 */
public class Desede extends EncDecRyptor {
   
    private SecretKey DES_KEY ;
    private final int FIXE_IV_BYTE_SIZE = 8 ;
    
    public Desede( _CIPHER_MODE cipher_type , _CIPHER_SIZE cipher_size, String password ) {
        init(cipher_type, cipher_size, password) ;
     }
     
    private void init( _CIPHER_MODE cipher_type , _CIPHER_SIZE cipher_size, String password  ) {
        
        try {

             CIPHER_TYPE  = cipher_type ;
             CIPHER_SIZE  = cipher_size ;
       
             SIZE_BYTE_KEY = Integer.parseInt( cipher_size.name().replace("_","") ) / 8 ;
        
             if( cipher_type == _CIPHER_MODE.CBC ) {
                  ivBytes  = randomInitIV( FIXE_IV_BYTE_SIZE ) ;
                  ivSpec   =  new IvParameterSpec(ivBytes)    ;
             }

             KEY = getKeyFromPasswordUsingSha256( password )           ;
 
             DESedeKeySpec keyspec = new DESedeKeySpec(KEY) ;
             SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede")     ;
             DES_KEY = keyfactory.generateSecret(keyspec)                             ;
 
             cipher = Cipher.getInstance( "DESede/" + CIPHER_TYPE + "/PKCS5Padding" ) ;

        } catch( Exception ex ) {
            System.out.println(ex.getCause()) ;
        }
    }
    
    @Override
    public void setOperationMode( _Operation op ) {

       OPERATION = op ;
       
       try {
            if( CIPHER_TYPE == _CIPHER_MODE.CBC  )     {
                cipher.init( op ==  _Operation.Encrypt ? Cipher.ENCRYPT_MODE      : 
                                          Cipher.DECRYPT_MODE , DES_KEY, ivSpec ) ;
            }
            else if( CIPHER_TYPE ==  _CIPHER_MODE.ECB ) {
                cipher.init( op ==  _Operation.Encrypt ? Cipher.ENCRYPT_MODE      : 
                                          Cipher.DECRYPT_MODE , DES_KEY )         ;
            }
       }
       catch( Exception ex )                  {
          ex.printStackTrace()                ;
          System.out.println( ex.getCause() ) ;
       }
   }
    
    @Override
    public byte[] process ( String message, _CipherOperation cipherOperation )  {
     
         try {  return OPERATION == _Operation.Decrypt    ?
                                     decrypt( message, cipherOperation ) :
                                     cipherOperation == _CipherOperation.dofinal ?
                                                  cipher.doFinal( message.getBytes(StandardCharsets.UTF_8)) :
                                                  cipher.update ( message.getBytes(StandardCharsets.UTF_8)) ;
         } catch( Exception ex )  {
             ex.printStackTrace() ;
             return null          ;
         }
    }
    
 
    @Override
    public byte[] getIvBytesEncoded64() {
        return ivBytes != null ?
               Base64.getEncoder().encode(ivBytes)  : null ;
    }
}
