
package com.rac021.jax.api.analyzer ;


import java.util.List ;
import java.util.Arrays ;
import java.util.ArrayList ;
import java.sql.Connection ;
import java.io.StringReader ;
import java.sql.SQLException ;
import java.util.logging.Level ;
import java.util.logging.Logger ;
import java.sql.ResultSetMetaData ;
import java.sql.PreparedStatement ;
import java.util.stream.Collectors ;
import com.rac021.jax.api.pojos.Query ;
import net.sf.jsqlparser.schema.Column ;
import javax.ws.rs.core.MultivaluedMap ;
import net.sf.jsqlparser.expression.Function ;
import net.sf.jsqlparser.JSQLParserException ;
import net.sf.jsqlparser.expression.Expression ;
import net.sf.jsqlparser.statement.select.Limit ;
import net.sf.jsqlparser.parser.CCJSqlParserUtil ;
import net.sf.jsqlparser.statement.select.Offset ;
import static java.util.stream.Collectors.toList ;
import net.sf.jsqlparser.statement.select.Select ;
import net.sf.jsqlparser.parser.CCJSqlParserManager ;
import net.sf.jsqlparser.statement.select.SelectBody ;
import net.sf.jsqlparser.statement.select.SelectItem ;
import net.sf.jsqlparser.statement.select.AllColumns ;
import net.sf.jsqlparser.statement.select.PlainSelect ;
import net.sf.jsqlparser.statement.select.OrderByElement ;
import net.sf.jsqlparser.statement.select.SelectExpressionItem ;

/**
 *
 * @author ryahiaoui
 */

public class SqlAnalyzer {
    
    
    public static Query buildQueryObject( Connection cnn, String sqlQuery )           {
         
        System.out.println( "  - Processing SQL Query "         )         ;
       
        String sub_sql = sqlQuery.length() > 40 ? 
                         sqlQuery.substring ( 0, 40 ) : sqlQuery          ;
           
        System.out.println("      - Extracted SQL Query : " + sub_sql + " ..." )      ;
           
        try {

            CCJSqlParserManager parserManager = new CCJSqlParserManager()             ;
            Select select = (Select) parserManager.parse(new StringReader( sqlQuery)) ;
           
            SelectBody selectBody      = select.getSelectBody()         ;
            PreparedStatement ps       = cnn.prepareStatement(sqlQuery) ;
            ResultSetMetaData metaData = ps.getMetaData()               ;
        
            int count = metaData.getColumnCount(); //number of column
           
            Query query = new Query() ;
            query.setContainsAggregationFunction( containsAggregationFuntion(sqlQuery)) ;
              
            List<String> orderColumnNames = new ArrayList();
               
            for (int i = 1; i <= count; i++) {
                  
                String columnName      = metaData.getColumnLabel(i)               ;
                String columnType      = metaData.getColumnClassName(i)           ;
                String fullNameColumn  = getFullNameSqlParamAt ( sqlQuery, i -1 ) ;
                  
                if(fullNameColumn == null ) fullNameColumn = columnName  ;
                
                query.register(columnName, fullNameColumn, columnType )  ;
                  
                orderColumnNames.add(metaData.getColumnName(i) )         ;
            }
              
            ((PlainSelect) selectBody).setOrderByElements(Arrays.asList()) ;
            List orderElementsList = new ArrayList<>() ;

            orderColumnNames.stream()
                            .forEach(( String col) -> { 
                              try {
                                   Expression expr = CCJSqlParserUtil.parseCondExpression( col ) ;
                                   OrderByElement orderByElement =  new OrderByElement() ;
                                   orderByElement.setExpression(expr)    ;
                                   orderElementsList.add(orderByElement) ;
                               } catch (JSQLParserException ex ) {
                                   Logger.getLogger(SqlAnalyzer.class.getName())
                                                               .log(Level.SEVERE, null, ex ) ;
                               }
;
            }) ;
                     
            System.out.println("      - OrderElementsList : " + orderElementsList  )  ;
             
            ((PlainSelect) selectBody).setOrderByElements(orderElementsList )         ;

            query.setQuery(select.toString()) ;
            
            System.out.println ( "      - SQL Object Query  : " + query ) ;
            
            System.out.println ( " \n------------------------------- "  ) ;

            
            return  query                                                 ; 
              
          } catch( SQLException | JSQLParserException ex )  {
              ex.printStackTrace() ;
          }
          
          return null ;
    }
   
    public static boolean containsAggregationFuntion( String query ) {
    
      try {
           CCJSqlParserManager parserManager = new CCJSqlParserManager() ;
           Select select = (Select) parserManager.parse(new StringReader(query)) ;
           PlainSelect ps = (PlainSelect) select.getSelectBody() ;
              
           List<SelectItem> selectItems = ps.getSelectItems() ;
               
           for( int i = 0; i < selectItems.size() ; i++) {
                   
              if( ps.getSelectItems().get(i) instanceof AllColumns) {
                     return false ;
              }
              if ( ((SelectExpressionItem) ps.getSelectItems().get(i)).getExpression() instanceof Function ) 
                    return true ;
           }
           
       } catch (JSQLParserException ex) {
              Logger.getLogger(SqlAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
       }
           
       return false ;
    }
           
       
    private static String applyParam( Query query , List<String> filters ) {
          
        if( query == null || filters == null ) return null ;

        String joinedParams  = "( " + filters.stream()
                                             .collect( Collectors
                                             .joining(" ) AND ( ") ) + " ) " ;
          
        try {
            
            CCJSqlParserManager parserManager = new CCJSqlParserManager() ;
            Select select  = (Select) parserManager.parse(new StringReader(query.getQuery())) ;
            PlainSelect ps = (PlainSelect) select.getSelectBody();
            
            if(joinedParams.replaceAll(" +", "").trim().equals("()")) {
                return select.toString() ;
            }
            
            String newQ ;

            if( query.isContainsAggregationFunction()) {
                
                Expression having = ps.getHaving() ;

               if( having != null ) {
                 newQ = query.getQuery().replace( having.toString() , 
                                                 having.toString() + " AND " + joinedParams ) ;
               }
               else  {
                 String hav      =  joinedParams ;
                 Expression expr = CCJSqlParserUtil.parseCondExpression(hav) ;
                 ((PlainSelect) select.getSelectBody()).setHaving(expr) ;
                 newQ =  select.toString() ;
               }
           
            } else {
          
                Expression wher = ps.getWhere() ;

                if( wher != null ) {
                     newQ = query.getQuery().replace( wher.toString(), 
                                                      wher.toString() + " AND " + joinedParams ) ;
                }
                else  {       
                       try {
                            Expression expr = CCJSqlParserUtil.parseCondExpression(joinedParams) ;
                            ((PlainSelect) select.getSelectBody()).setWhere(expr) ; 
                            newQ = select.toString()  ;
                       } catch( JSQLParserException ex ) {
                           ex.printStackTrace()      ;
                           return select.toString() ;
                       }
               }
           }
         
           return newQ ;
           
        } catch( JSQLParserException ex ) {
             ex.printStackTrace() ; 
        }
        
        return null ;
    }

    public static List<String> applyParams ( final List<Query> queries , List<String> filters ) {
            
       try {
            List<String> collect = queries.stream()
                                          .map( query -> applyParam( query, filters))
                                          .map( query -> applyLimitOffset( query))
                                          .collect(toList()) ; 

             System.out.println(" Queries --> " +collect ) ;
             System.out.println(" Filters --> " +filters ) ;
             return collect                                ;
             
           } catch( Exception ex )  {
               ex.printStackTrace() ;
           }
           return null ;
        }

     
    public static Query getSqlParamsWithTypes( Connection cnn, String sqlQuery ) {
       
       Query query = new Query( sqlQuery ) ;
       
       try {
            
            PreparedStatement ps       = cnn.prepareStatement(sqlQuery) ;
            ResultSetMetaData metaData = ps.getMetaData()               ;
        
            int count = metaData.getColumnCount(); //number of column
             
            for (int i = 1; i <= count; i++) {
                
                  String columnName = metaData.getColumnLabel(i) ;
                  String columnType = metaData.getColumnClassName(i);
                  String fullNameColumn = getFullNameSqlParamAt ( sqlQuery, i -1 );
                  if(fullNameColumn == null ) fullNameColumn = columnName ;
                  query.register(columnName, fullNameColumn, columnType)  ;
                  
            }
              
       } catch( SQLException ex ) {
            ex.printStackTrace();
         }
      return query ;

    }
   
    public static List<String> buildFilters( List<Query> queries, 
                                             MultivaluedMap<String, String> queryParams ) {
        
        if(queryParams.isEmpty() ) return queries.stream().map( query ->  
                                          applyLimitOffset(query.getQuery()))
                                         .collect(toList()) ;
        
        List<String> filters = Lexer.decodeExpression(queries.get(0), queryParams) ;
        
        List<String> okQueries = SqlAnalyzer.applyParams(queries, filters) ;
        
        return okQueries ;
        
    }

    private static String getFullNameSqlParamAt ( String sql, int index ) {

       try {
            CCJSqlParserManager parserManager = new CCJSqlParserManager() ;
            Select select  = (Select) parserManager.parse(new StringReader(sql)) ;
            PlainSelect ps = (PlainSelect) select.getSelectBody();
            
            SelectItem get = ps.getSelectItems().get(0) ;
            
            if( get instanceof AllColumns) {
                return null ;
            }
             
            Expression expression = ((SelectExpressionItem) 
                                    ps.getSelectItems().get(index)).getExpression() ;
          
           if (expression instanceof Function) {
                 
               if( ((Function) ((SelectExpressionItem) ps.getSelectItems()
                                                       .get(index)).getExpression()) != null ) {
  
                   return ((Function) ((SelectExpressionItem) ps.getSelectItems()
                                                                .get(index)).getExpression()).toString() ;
               } 
           }
             
           return ((Column) ((SelectExpressionItem) ps.getSelectItems()
                                                      .get(index)).getExpression())
                                                      .getFullyQualifiedName() ;
       } catch( JSQLParserException ex )  {
            ex.printStackTrace() ;
         }

       return null ;
    }

    private static String applyLimitOffset(String query)  {

        if( query == null) return null ;
          
        try  {
                CCJSqlParserManager parserManager = new CCJSqlParserManager();
                Select select = (Select) parserManager.parse(new StringReader( query)) ;

                Limit limit   = ((PlainSelect) select.getSelectBody()).getLimit()  ;
                Offset offset = ((PlainSelect) select.getSelectBody()).getOffset() ;

                String retQ = select.toString() ;
                retQ += offset == null ?  " OFFSET ?1 " : " "  ;
                retQ += limit == null ? " LIMIT ?2 " : " "     ;  

                return retQ ;                   
            
        } catch( JSQLParserException ex )   {
             ex.printStackTrace() ;
          }
       
       return null ;
    }
}
