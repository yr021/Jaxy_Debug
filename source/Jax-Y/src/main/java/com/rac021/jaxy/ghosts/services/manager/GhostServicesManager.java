
package com.rac021.jaxy.ghosts.services.manager ;

import java.net.URL ;
import java.util.Map ;
import java.util.List ;
import java.util.Arrays ;
import javax.ejb.Startup ;
import java.util.Optional ;
import java.nio.file.Path ;
import javax.ejb.DependsOn ;
import java.nio.file.Files ;
import javax.inject.Inject ;
import java.nio.file.Paths ;
import javax.ejb.Singleton ;
import java.io.IOException ;
import java.util.ArrayList ;
import java.sql.Connection ;
import java.io.BufferedReader ;
import java.util.logging.Level ;
import java.lang.reflect.Field ;
import java.util.logging.Logger ; 
import java.io.InputStreamReader ;
import com.rac021.jax.api.pojos.Query ;
import javax.annotation.PostConstruct ;
import javax.persistence.EntityManager ;
import javax.enterprise.inject.spi.Bean ;
import net.openhft.compiler.CompilerUtils ;
import javax.persistence.PersistenceContext ;
import com.rac021.jax.api.crypto.AcceptType ;
import com.rac021.jax.api.crypto.CipherTypes ;
import javax.enterprise.inject.spi.Unmanaged ;
import com.rac021.jaxy.unzipper.UnzipUtility ;
import com.rac021.jax.api.qualifiers.SqlQuery ;
import com.rac021.jax.api.root.ServicesManager ;
import com.rac021.jax.api.analyzer.SqlAnalyzer ;
import javax.enterprise.inject.spi.BeanManager ;
import com.rac021.jax.api.manager.TemplateManager ;
import javax.enterprise.context.ApplicationScoped ;
import static java.util.stream.Collectors.joining ;
import com.rac021.jax.api.qualifiers.security.Policy ;
import com.rac021.jax.api.qualifiers.ServiceRegistry ;
import com.rac021.jax.api.qualifiers.ResourceRegistry ;
import javax.enterprise.inject.spi.Unmanaged.UnmanagedInstance ;
import com.rac021.jax.api.streamers.DefaultStreamerConfigurator ;
import com.rac021.jax.security.provider.configuration.YamlConfigurator ;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.Timer;

/**
 *
 * @author ryahiaoui
 */

@Singleton
@Startup
@ApplicationScoped
@DependsOn("ServicesManager")

public class GhostServicesManager {
    
    @Inject 
    MetricRegistry metrics ;
    
    @Inject 
    ServicesManager servicesManager ;
    
    @Inject
    YamlConfigurator yamlConfigurator ;
    
    @PersistenceContext  (unitName = "MyPU")
    private EntityManager entityManager    ;

    @Inject
    private BeanManager bm ;
    
    private final String VIRTUAL_FILE_SYSTEM  = "java.io.tmpdir"       ;
    
    
    public void processGhostService ( Map<String, Object > service ) {
     
       try {
           
           String serviceCode = (String) service.keySet().stream()
                                                .findFirst().orElse( null )     ;

           System.out.println( " Processig Service : " + serviceCode  )         ;
           
           System.out.println( " ------------------- \n" )                      ;
           
           String sql  = ((String) ( (Map)service.get(serviceCode)).get("Query"))
                                                 .replaceAll(" +", " ")                 ;
           
           String security = yamlConfigurator.getAuthenticationType( serviceCode )      ;
          
           List<CipherTypes> ciphersType = yamlConfigurator.getCiphers(serviceCode )    ;
           
           if( ciphersType.isEmpty() && ! 
               security.equalsIgnoreCase(Policy.Public.name()) )      {
               
               ciphersType = Arrays.asList ( CipherTypes.AES_256_ECB  ,
                                             CipherTypes.AES_256_CBC) ;
           }

           servicesManager.registerCiphers(serviceCode.trim(), ciphersType )            ;
           
           String cipherString = ciphersType.stream()
                                            .map( cipher -> "CipherTypes." + cipher  )
                                            .collect(joining(", "))                     ;
          
           String tempUri         = yamlConfigurator.getTemplateUri(serviceCode )       ;
           
           if( tempUri != null )                                           {
              String templateData = TemplateManager.readFile(tempUri )     ;
              System.out.println( "  -  Register Template : " + tempUri )  ;
              servicesManager.registerTemplate(serviceCode, templateData)  ;
           }
           
           List<AcceptType> acceptTypes = yamlConfigurator.getAcceptTypes(serviceCode ) ;
           
           if ( acceptTypes.isEmpty()) {
               acceptTypes.addAll(AcceptType.getPlainTypes()) ;
           }
           
           if ( ! security.equalsIgnoreCase(Policy.Public.name()) ) {
               acceptTypes.addAll(AcceptType.getEncryptedTypes())   ;
           }
           
           if ( tempUri != null &&  
                security.equalsIgnoreCase(Policy.Public.name()) )   {
               acceptTypes.addAll(AcceptType.getPlainTemplate() )   ;
           } 
           
           if ( tempUri != null &&  
                ! security.equalsIgnoreCase(Policy.Public.name()) )  {
               acceptTypes.addAll(AcceptType.getPlainTemplate() )    ;
               acceptTypes.addAll(AcceptType.getEncryptedTemplate()) ;
           }
           
           servicesManager.registerAcceptTypes( serviceCode.trim(), acceptTypes )  ;
     
           System.out.println( "  -  Securing Service : " + serviceCode     +  
                               " With --> [ "  +  security  + " ] security ")      ;
           
           System.out.println( "  -  Ciphers   : [ " + 
                               cipherString.replace("CipherTypes.", "")  + " ] ")  ;
             
           String dtoClassName        = "Dto"                              ;
           String serviceClassName    = "Service"                          ;
           String resourceClassName   = "Resource"                         ;
           String packageName         = "com.rac021.jaxy.ghosts.services." ;
           
           if(  yamlConfigurator.getMaxThreadsByServiceCode ( serviceCode ) != null )      {
                    
             int maxThreads = yamlConfigurator.getMaxThreadsByServiceCode ( serviceCode )  ;
                  
              servicesManager.apllyMaxThreads( serviceCode.trim() , maxThreads )           ;
                   
              System.out.println( "  -  Extracting MaxThreads ... // Value : " 
                                  +  maxThreads )                                          ;
               
           } else {
               System.out.println( "  -  Applying Default MaxThreads ... // Default Value : "  +
                                         DefaultStreamerConfigurator.maxThreadsPerService  )   ;
           }
           
           URL    resourceDto         = new GhostServicesManager().getClass().getClassLoader()
                                                                  .getResource("templates/Dto")       ;
           String contentDto          = readFile(resourceDto) ;
           
           URL    resourceResource    = new GhostServicesManager().getClass().getClassLoader()
                                                                  .getResource("templates/Resource")  ;
           String contentResource     = readFile(resourceResource)                                    ;
           
           URL    resourceService     = new GhostServicesManager().getClass().getClassLoader()
                                                                  .getResource("templates/Service")   ;
           String contentService      = readFile(resourceService)                                     ;
           
           String stringDtoClass      = buildStringDtoClassFor( packageName + serviceCode.trim()      , 
                                                                dtoClassName, contentDto, sql )       ;
           
           String stringResourceClass = buildStringResourceClassFor( packageName + serviceCode.trim() , 
                                                                     resourceClassName                , 
                                                                     contentResource                  ,  
                                                                     Arrays.asList(sql ) )            ;
           
           Connection cnn = entityManager.unwrap(java.sql.Connection.class)  ;
           Query query    = SqlAnalyzer.getSqlParamsWithTypes(cnn, sql )     ;
           
           String stringServiceClass = buildStringServiceClassFor ( serviceCode.trim()               ,
                                                                    packageName + serviceCode.trim() ,
                                                                    serviceClassName                 ,
                                                                    contentService                   ,
                                                                    query                            ,
                                                                    resourceClassName                ,
                                                                    dtoClassName + ".class"          ,
                                                                    security                         ,
                                                                    cipherString            )        ;
        
            String rootPath  = System.getProperty (VIRTUAL_FILE_SYSTEM)   ;

            /* Unzip G-Jax-Api */
            Optional<Path> gJaxApi = Files.list(Paths.get(rootPath))
                                                     .filter( file -> file.getFileName()
                                                                          .toFile()
                                                                          .getName()
                                                                          .contains("G-Jax-Api-") &&
                                                                      file.getFileName().toFile()
                                                                          .getName().endsWith(".jar") )
                                                     .findFirst() ;

            try {
                if( gJaxApi.isPresent() ) UnzipUtility.unzip( gJaxApi.get().toString()   , rootPath ) ;
            } catch (Exception ex)   {
                ex.printStackTrace() ;
           }

           System.out.println( "  -  Compiling Dto : " +  
                               packageName + serviceCode.trim() + "." + dtoClassName )         ;
           
           Class<?> dto       = CompilerUtils.CACHED_COMPILER.loadFromJava( packageName        + 
                                                                            serviceCode.trim() + 
                                                                            "."                + 
                                                                            dtoClassName       , 
                                                                            stringDtoClass )   ;

           System.out.println( "  -  Compiling Resource : " + 
                               packageName + serviceCode.trim() + "." + resourceClassName ) ;
           
           Class<?> _resource = CompilerUtils.CACHED_COMPILER.loadFromJava( packageName           + 
                                                                            serviceCode.trim()    + 
                                                                            "."                   + 
                                                                            resourceClassName     , 
                                                                            stringResourceClass ) ;

           System.out.println("  -  Compiling Service  : " + packageName + serviceCode.trim() + 
                                                        "." + serviceClassName )              ;
         
           /* Create Metric before compiling the service */
           Metadata counterMetadata = new Metadata( packageName +  serviceCode + "_Service.Service" , MetricType.COUNTER, "") ;
           // Create or retrieve the metric by Metadata.getName()
           Counter counterMetric = metrics.counter(counterMetadata) ;
           // Register a gauge metric by Metadata.getName()
           // metrics.register(counterMetadata, counterMetric) ;

           System.out.println("******************************");
           System.out.println("******************************");
            System.out.println(stringServiceClass);
           System.out.println("******************************");
           System.out.println("******************************");
            
           Class<?> _service  = CompilerUtils.CACHED_COMPILER.loadFromJava( packageName            +
                                                                            serviceCode.trim()     + 
                                                                            "." + serviceClassName , 
                                                                            stringServiceClass )   ;
               
           ServiceRegistry serviceRegistry = _service.getAnnotation(ServiceRegistry.class) ;

           Bean<Object> bean = (Bean<Object>) bm.resolve(bm.getBeans(_service, serviceRegistry ) ) ;
               
           if( bean != null) {
               Object cdiService = (Object) bm.getReference ( bean                 , 
                                                              bean.getBeanClass()  , 
                                                              bm.createCreationalContext(bean))    ;
               
               servicesManager.registerService( serviceCode , cdiService )                         ;
           }
           
           else {
    
                Object managedResource = getInstance(_resource) ;

                servicesManager.extractAndRegisterQueries( _resource , SqlQuery.class )       ;

                try {

                    Object managedService = getInstance(_service)                             ;

                    Field resourceField = servicesManager.getFieldFor( _service, ResourceRegistry.class) ;
                    resourceField.setAccessible(true)                                                    ;
                    resourceField.set( managedService, managedResource)                                  ;

                    servicesManager.registerService( serviceCode , managedService )                      ;

                } catch (Exception ex)    {
                     Logger.getLogger( GhostServicesManager.class.getName())
                                                           .log(Level.SEVERE, null, ex) ;
                     ex.printStackTrace() ;
                }
            }

       } catch( Exception ex)  {
          Logger.getLogger(GhostServicesManager.class.getName()).log(Level.SEVERE, null, ex) ;
          ex.printStackTrace() ;
       }
    }
    
    private Object getInstance( Class<?> clazz )  {

      Unmanaged unmanagedService = new Unmanaged<>(bm, clazz)                   ;
      UnmanagedInstance serviceInstanceService = unmanagedService.newInstance() ;
      serviceInstanceService.produce().inject().postConstruct()                 ;
      Object managedService = serviceInstanceService.get()                      ;
      return managedService                                                     ;
    }
    
    @PostConstruct
    public void init() {
        
      List<Map<String, Object>> ghostsServices = yamlConfigurator.getServices()    ;
      
      if( ghostsServices != null && ! ghostsServices.isEmpty() )                   {
          
          System.out.println("                                                 " ) ;
          System.out.println(" *********************************************** " ) ;
          System.out.println(" *********************************************** " ) ;
          System.out.println(" ==>>> Compiling Ghosts Services <<<==   ******* " ) ;
          System.out.println(" *********************************************** " ) ;
          System.out.println(" *********************************************** " ) ;
         
          if( ! ghostsServices.isEmpty() ) {
              try {
                unzipJavaEEdependency()    ; 
                 unzipJavaDependency("wfswarmmicroprofile-metrics-api-1.") ; 
                 unzipJavaDependency("wfswarmmicroprofile-metrics-2." )  ; 
                 
                CompilerUtils.addClassPath ( 
                                        System.getProperty (VIRTUAL_FILE_SYSTEM) ) ;
                
              } catch( Exception ex)   {
                  ex.printStackTrace() ;
              }
          }
          
          ghostsServices.forEach ( svice ->      { 
              System.out.println ("          " ) ;
              processGhostService( (Map) svice ) ;
          }) ;
          
          System.out.println( "                                   " ) ;
      }
      else {
          System.out.println( "                                   " ) ;
          System.out.println( " ********************************* " ) ;
          System.out.println( " *** Zero Ghost Service Found  *** " ) ;
          System.out.println( " ********************************* " ) ;
          System.out.println( "                                   " ) ;
      }
      
      System.out.println( " ******************************     \n " ) ;
      System.out.println( " Applying Global Configuration ***     " ) ;
      System.out.println( "                                       " ) ;
          
      if( DefaultStreamerConfigurator.maxConcurrentUsers != yamlConfigurator.getMaxConcurrentUsers() )  {
          DefaultStreamerConfigurator.maxConcurrentUsers =  yamlConfigurator.getMaxConcurrentUsers()    ;
          DefaultStreamerConfigurator.initSemaphoreConcurrentUsers()                                    ;
      }
          
      if( DefaultStreamerConfigurator.responseCacheSize != yamlConfigurator.getResponseCacheSize() )    {
          DefaultStreamerConfigurator.responseCacheSize =  yamlConfigurator.getResponseCacheSize()      ;
      }
          
      if( DefaultStreamerConfigurator.selectSize != yamlConfigurator.getSelectSize() )                  {
          DefaultStreamerConfigurator.selectSize =  yamlConfigurator.getSelectSize()                    ;
      }
      
      if( DefaultStreamerConfigurator.ratio !=  yamlConfigurator.getRatio() )                           {
          DefaultStreamerConfigurator.ratio  =  yamlConfigurator.getRatio()                             ;
      }
          
      if( DefaultStreamerConfigurator.threadPoolSize !=  yamlConfigurator.getThreadPoolSize() )         {
          DefaultStreamerConfigurator.threadPoolSize  =  yamlConfigurator.getThreadPoolSize()           ;
      }
          
      System.out.println(" -> maxConcurrentUsers :  " + DefaultStreamerConfigurator.maxConcurrentUsers ) ;
      System.out.println(" -> responseCacheSize  :  " + DefaultStreamerConfigurator.responseCacheSize  ) ;
      System.out.println(" -> threadPoolSize     :  " + DefaultStreamerConfigurator.threadPoolSize     ) ;
      System.out.println(" -> selectSize         :  " + DefaultStreamerConfigurator.selectSize         ) ;
      System.out.println(" -> ratio              :  " + DefaultStreamerConfigurator.ratio              ) ;
      System.out.println( "                                                                         "  ) ;
      System.out.println( " *********************************                                       "  ) ;
      System.out.println( "                                                                      \n "  ) ;
    
    }
    
    
    public GhostServicesManager() {
    }

    private String readFile(URL resource ) {
 
        StringBuilder content      = new StringBuilder()  ;
        try( BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
             String line  = null ;
             while ((line = reader.readLine()) != null) content.append(line).append("\n") ;
        } catch (IOException ex) {
            Logger.getLogger(GhostServicesManager.class.getName()).log(Level.SEVERE, null, ex) ;
        }
        return content.toString() ;
    }

    
    private String buildStringDtoClassFor( String packageName  , 
                                              String className    , 
                                              String dtoTemplate  , 
                                              String sqlQuery )   {
        
        Connection cnn           = entityManager.unwrap(java.sql.Connection.class)  ;
        
        Query      query         = SqlAnalyzer.getSqlParamsWithTypes(cnn, sqlQuery ) ;
        
        String     dtoTemplate_1 = dtoTemplate.replace( "{PACKAGE_NAME}", 
                                                        packageName).replace( "{CLASS_NAME}", 
                                                                              className   ) ;
        int index = 0 ;
        
        for( String column : query.getParameters().keySet() ) {
            
           dtoTemplate_1 =  dtoTemplate_1.replace( "{SQL_FIELD}" , 
                                                   "@ResultColumn(index=" +  index++  + ") "  +
                                                   " private " + query.getType(column) + " "  + 
                                                   column + " ;" + "\n\n    {SQL_FIELD} ")    ;

           dtoTemplate_1 =  dtoTemplate_1.replace( "{SQL_GETTER}", 
                                                   generateGetter( query.getType(column), column ) + 
                                                                   "\n    {SQL_GETTER} ")          ;
          
           dtoTemplate_1 =  dtoTemplate_1.replace( "{SQL_SETTER}", 
                                                   generateSetter( query.getType(column), column ) +
                                                   "\n    {SQL_SETTER} ")                          ;
       
        }
        
        return dtoTemplate_1.replace( "{SQL_FIELD}"  , "" )
                            .replace( "{SQL_GETTER}" , "" )
                            .replace( "{SQL_SETTER}" , "" ) ;
        
    }
   
    private String buildStringResourceClassFor( String packageName     , 
                                                String className       , 
                                                String contentResource , 
                                                List<String> sqls )    {
        
         String resourceTemplate_1 = contentResource.replace("{PACKAGE_NAME}" , packageName )
                                                    .replace("{CLASS_NAME}"   , className   )
                                                    .replace("{RESOURCE_CODE}", packageName + 
                                                    "." + className )                       ;
         
        for( int i = 0 ; i < sqls.size() ; i++ )  {
            
            String sql = sqls.get(i).replaceAll("\n", " " )
                                    .replace("\t", " "    )
                                    .replaceAll("\t+", " ")
                                    .replaceAll(" +", " " ) ;
       
            resourceTemplate_1 =  resourceTemplate_1.replace( "{SQL_QUERY}"               ,
                                                              "@SqlQuery(\" Query_"       + 
                                                              String.valueOf(i) + "\") "  + 
                                                              " private  String QUERY_"   +
                                                              String.valueOf(i) + " = \"" + 
                                                              sql                         + 
                                                              "\" ; "                     + 
                                                              "\n\n    {SQL_QUERY} ")     ;
        }
        
        return resourceTemplate_1.replace("{SQL_QUERY}", "" ) ;
                                 
    }

    private String buildStringServiceClassFor( String serviceCode    ,
                                               String packageName    , 
                                               String className      , 
                                               String contentService , 
                                               Query  query          , 
                                               String resourceName   ,
                                               String dtoClass       ,
                                               String security       ,
                                               String ciphers )      {
        
         String resourceTemplate_1 = contentService.replace("{PACKAGE_NAME}"  , packageName  )
                                                   .replace("{SERVICE_NAME}"  , className    )
                                                   .replace("{SERVICE_CODE}"  , serviceCode  )
                                                   .replace("{RESOURCE_CODE}" , packageName  + 
                                                                                "."          +  
                                                                                resourceName ) 
                                                   .replace("{RESOURCE_NAME}" , resourceName ) 
                                                   .replace("{DTO_CLASS}"     , dtoClass     )
                                                   .replace("{CIPHERS}"       , ciphers      ) ;
         
         if( security == null   || security.equalsIgnoreCase("public") ) {
             resourceTemplate_1 =  resourceTemplate_1.replace("{SECURITY}", "@Public") ;
         }
         else if ( security.equalsIgnoreCase("CustomSignOn")) {
             resourceTemplate_1 =  resourceTemplate_1.replace( "{SECURITY}", 
                                                               "@Secured( policy = Policy.CustomSignOn )") ;
         }else if( security.equalsIgnoreCase("SSO")) {
             resourceTemplate_1 =  resourceTemplate_1.replace( "{SECURITY}", 
                                                               "@Secured( policy = Policy.SSO )")          ;
         }
         else {
             throw new IllegalArgumentException(" Unknown Authentication Mode for [ " + security  + " ] ") ;
         }
         
        List<String> params = new ArrayList() ; 

        for( String column : query.getParameters().keySet() ) {
            params.add("@QueryParam(\""+column +"\") " + "java.lang.String" + " " + column ) ;
        }

//        System.out.println("*****************************************************************************");
//        System.out.println("*****************************************************************************");
//        System.out.println("*****************************************************************************");
//        System.out.println("*****************************************************************************");
//        System.out.println(resourceTemplate_1.replace(" {SQL_PARAMS}" , String.join (" , ", params) )    );
//        System.out.println("*****************************************************************************");
//        System.out.println("*****************************************************************************");
//        System.out.println("*****************************************************************************");
//        System.out.println("*****************************************************************************");
        return resourceTemplate_1.replace(" {SQL_PARAMS}" , String.join (" , ", params) )    ; 
    }

    private String generateGetter( String type, String variable ) {
        
      return " public " + type + " get" + capitalizeFirstLetter(variable) + "() { \n " +
             "    return " + variable +" ; \n" +
             "     } " ;
    }
    
    private String capitalizeFirstLetter( String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1) ;
    }
    
    private String generateSetter ( String type, String variable ) {
        
      return " public void set"                + 
               capitalizeFirstLetter(variable) + 
              "( " + type +  " "   + variable  + 
              " ) { \n "  + "    this. "       +
              variable    + " = "  + variable  +
              " ;\n"      +
              "     } "   ;
    }

    public YamlConfigurator getConfigurator() {
        return yamlConfigurator ;
    }

    private void unzipJavaEEdependency() throws Exception {
        
       String rootPath  = System.getProperty (VIRTUAL_FILE_SYSTEM)   ;
              
       Optional<Path> javaEEPath  =  Files.list(Paths.get(rootPath))
                                          .filter( file -> ( file.getFileName().toFile()
                                                                 .getName()
                                                                 .contains("javaee-api-8.") ||
                                                             file.getFileName().toFile()
                                                                 .getName()
                                                                 .contains("javaee-api-7.")
                                                            ) &&
                                                            file.getFileName().toFile()
                                                                .getName().endsWith(".jar") )
                                           .findFirst() ;
              
        if( ! javaEEPath.isPresent())   { 
            throw new IllegalAccessException( "\n   *********************************    \n"
                                              + "   Java-EE Dependency not found !       \n"
                                              + "   Compilation Abort                    \n"
                                              + "   *********************************** ")  ;
        } else {
              
            String JAVA_EE = javaEEPath.get().getFileName().toString()
                                             .contains("javaee-api-8.") ? 
                                             "JAVA EE 8 " : "JAVA EE 7 "    ;
               
            System.out.println ( "\n   *********************************   \n"
                               + "   Java-EE Dependency                    \n"
                               + "    -->  " + JAVA_EE + " <--             \n"
                               + "   *********************************     " ) ;
        }
         
        try {
              if ( javaEEPath.isPresent() ) UnzipUtility.unzip( javaEEPath.get().toString() , 
                                                                rootPath )                  ;
        } catch (Exception ex) {
             ex.printStackTrace() ;
        }
    }
    
    
    private void unzipJavaDependency( String dep) throws Exception {
        
       String rootPath  = System.getProperty (VIRTUAL_FILE_SYSTEM)   ;
              
       Optional<Path> javaDep  =  Files.list(Paths.get(rootPath))
                                       .filter( file -> ( file.getFileName().toFile()
                                                              .getName()
                                                              .contains(dep)
                                                         ) &&
                                                         file.getFileName().toFile()
                                                             .getName().endsWith(".jar") )
                                        .findFirst() ;
              
        if( ! javaDep.isPresent())   { 
            throw new IllegalAccessException( "   \n*********************************   \n"
                                              + "   " + dep + " Dependency not found !  \n"
                                              + "   Compilation Abort                    \n"
                                              + "   *********************************** ") ;
        } 
        else { 
              System.out.println ( "\n   *********************************          \n"
                                   + "   Java Dependency                            \n"
                                   + "   -->  " + javaDep.get().toString() + " <--  \n"
                                   + "   *********************************        " ) ;
              
                UnzipUtility.unzip( javaDep.get().toString() , 
                                    rootPath )              ;
        }
        
    }
    
    
}
