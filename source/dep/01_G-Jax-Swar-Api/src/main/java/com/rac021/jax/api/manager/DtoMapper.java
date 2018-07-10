
package com.rac021.jax.api.manager ;

import com.rac021.jax.api.exceptions.BusinessException;
import java.util.List ;
import java.util.Arrays ;
import java.util.ArrayList ;
import java.lang.reflect.Field ;
import com.rac021.jax.api.qualifiers.ResultColumn ;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author ryahiaoui
 */

public class DtoMapper {

    public static <T> List<T> map( List<Object[]> objectArrayList , 
                                   Class<T> genericType           , 
                                   List<String> filterdIndex   )  {
        
        List<T> ret = new ArrayList<>()                               ;
        if(objectArrayList.isEmpty()) return ret                      ;
        List<Field> mappingFields = getAnnotatedFields( genericType ) ;
        
        try {
            for (Object[] objectArr : objectArrayList) {
                T t = genericType.newInstance() ;
                for (int i = 0; i < objectArr.length; i++) {
                    if( i < mappingFields.size() ) {
                        Field field = t.getClass()
                                       .getDeclaredField( mappingFields.get(i)
                                       .getName()) ;
                        if( filterdIndex  != null   &&
                            !filterdIndex.isEmpty() && 
                            !filterdIndex.contains(field.getName())
                            )  continue ;
                        
                           // if(field.getAnnotation(Public.class) != null ) {
                           field.setAccessible(true)    ;
                           field.set( t , objectArr[i]) ; 
                           //  }
                    }
                }
                ret.add(t) ;
            }
        } catch (Exception ex)  {
           ex.printStackTrace() ;
           ret.clear()          ;
        }
        
        return ret ;
    }

    private static <T> List<Field> getAnnotatedFields (Class<T> genericType ) {
        
        Field[] fields            = genericType.getDeclaredFields()         ;
        
        List<Field> orderedFields = Arrays.asList(new Field[fields.length]) ;
        
        for (int i = 0; i < fields.length; i++)  {
            if (fields[i].isAnnotationPresent( ResultColumn.class )) {
                ResultColumn nqrc = fields[i].getAnnotation(ResultColumn.class) ;
                orderedFields.set(nqrc.index(), fields[i])                      ;
            }
        }
        return orderedFields ;
    }
    
    
    public static Map<String, String> extractValuesFromObject ( Object dto              ,
                                                                Class genericType       ,
                                                                List<String> attributes ) {
        Field[] fields = genericType.getDeclaredFields() ;
         
        return Arrays.asList(fields).stream()
                                    .filter( f -> attributes.contains( f.getName()))
                                    .filter( f ->  getValueFromField(f, dto) != null )
                                    .collect( Collectors.toMap( f -> f.getName() ,
                                                                f -> getValueFromField(f, dto)
                                                              )) ;
    }
    
    private static String getValueFromField( Field f , Object o )  {
      
        if( f == null || o == null ) return null ;
        try {
            f.setAccessible(true)      ;
            return ( f.get(o) == null ) ? null : f.get(o).toString() ;
        } catch ( IllegalAccessException | IllegalArgumentException ex ) {
            try {
                Logger.getLogger(DtoMapper.class.getName()).log(Level.SEVERE, null, ex ) ;
                throw  new BusinessException( ex.getLocalizedMessage());
            } catch (BusinessException ex1) {
                Logger.getLogger(DtoMapper.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null ;
    }
    
}
