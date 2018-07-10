package com.rac021.ui;

/**
 *
 * @author ryahiaoui
 */
import com.rac021.jax.api.exceptions.BusinessException;
import java.util.Map;
import java.util.List;
import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import com.rac021.jax.api.qualifiers.ServiceRegistry;
import com.rac021.jax.api.qualifiers.security.Custom;
import com.rac021.jax.api.qualifiers.security.Policy;
import com.rac021.jax.api.root.ServicesManager;
import com.rac021.jax.api.security.ISignOn;
import com.rac021.jax.service.discovery.InfoServices;
import com.rac021.jaxy.ghosts.services.manager.IPManager;
import com.rac021.jax.service.discovery.ServiceDescription;
import com.rac021.jax.api.streamers.DefaultStreamerConfigurator;
import com.rac021.jax.security.provider.configuration.YamlConfigurator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringEscapeUtils;

@SessionScoped
@Named(value = "myBean")
public class MyBean implements Serializable {

    @Inject
    @ServiceRegistry("infoServices")
    transient InfoServices infoServices;

    @Inject
    transient ServicesManager servicesManager;

    @Inject
    transient YamlConfigurator yamlConfigurator;

    @Inject
    @Any
    private Instance<ISignOn> signOn;

    private ServiceDescription selectedServiceDescription;

    private List<ServiceDescription> services = null;

    public List<ServiceDescription> getServices() {
        if (services == null) {
            services = infoServices.extractServices();
        }
        return services;
    }

    public void selectedService(ServiceDescription sd) {
        this.cipherItem = null ;
        if( sd != null && ! sd.getAccepts().isEmpty() )  {
          this.acceptItem = sd.getAccepts().iterator().next() ;
        }
        this.selectedFilter = null ;
        this.selectedValue = null ;
        this.selectedExpression = null ;
        this.script             = null ;
        this.scriptDecryptor    = null ;
        this.selectedServiceDescription = sd;
    }

    public boolean isSelectedService() {
        return this.selectedServiceDescription != null;
    }

    public String getSelectedServiceName() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getServiceName() : "--";
    }

    public String getSelectedServiceSecurity() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getSecurity() : "--";
    }

    public String getSelectedServiceAcceptsAsString() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getAccepts().toString() : "--";
    }

    public List<String> getSelectedServiceAccepts() {
        return this.selectedServiceDescription != null
                ? new ArrayList(this.selectedServiceDescription.getAccepts()) : null;
    }

    public String getSelectedServiceCiphersAsString() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getCiphers().toString() : "--";
    }

    public List<String> getSelectedServiceCiphers() {
        return this.selectedServiceDescription != null
                ? new ArrayList<>(this.selectedServiceDescription.getCiphers()) : null;
    }

    public String getSelectedServiceParamsAsString() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getParams().toString() : "--";
    }

    public List getSelectedServiceFilters() {
        return this.selectedServiceDescription != null
                ? new ArrayList<>(this.selectedServiceDescription.getParams().keySet()) : null;
    }
    
    List<String> expressions = Arrays.asList("=", "&gt;", "&gt;=", "&lt;", "&lt=;", "!") ;
            
    public List setChainedExpresion() {
     
       expressions =  Arrays.asList( " AND "+ selectedFilter + " = "     ,
                                     " AND "+ selectedFilter + " &gt; "  ,
                                     " AND "+ selectedFilter + " &gt;= " , 
                                     " AND "+ selectedFilter + " &lt; "  ,
                                     " AND "+ selectedFilter + " &lt;= " ,
                                     " AND "+ selectedFilter + " ! "     ,
                                     " OR "+ selectedFilter + " = "      ,
                                     " OR "+ selectedFilter + " &gt; "   ,
                                     " OR "+ selectedFilter + " &gt;= "  ,
                                     " OR "+ selectedFilter + " &lt; "   ,
                                     " OR "+ selectedFilter + " &lt;= "  ,
                                     " OR "+ selectedFilter + " ! " 
        );
       return expressions ;
    }
    
    public List resetExpresion() {
     
        expressions = Arrays.asList("=", "&gt;", "&gt;=", "&lt;", "&lt=;", "!") ;
        return expressions ;
    }

    public String getSelectedServiceTemplate() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getTemplate() : "--";
    }

    public List<String> getExpressions() {
        return expressions;
    }
    
    
    public void initData() {
        this.selectedExpression= null;
        this.selectedValue= null     ;
        this.selectedFilter= null    ;
        resetExpresion()             ;
        expressionsMap.clear()       ;
    }
    
    public Integer getSelectedServiceTotalLinesInTemplate() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getTemplate() != null
                ? this.selectedServiceDescription.getTemplate()
                        .split("\r\n|\r|\n").length - 2 : 2 : 2;
    }

    public String getSelectedServiceMaxThreads() {
        return this.selectedServiceDescription != null
                ? this.selectedServiceDescription.getMaxThreads().toString() : "--";
    }

    public String getSelectedServiceUriTemplate() {

        return this.selectedServiceDescription != null
                ? IPManager.IP_ADRESS + ":"
                + yamlConfigurator.getHttpPort()
                + "/rest/resources/"
                + this.selectedServiceDescription
                        .getServiceName() : "--";
    }

    public Integer getMaxConcurrentUsers() {
        return DefaultStreamerConfigurator.maxConcurrentUsers;
    }

    public Integer getThreadPoolSize() {
        return DefaultStreamerConfigurator.threadPoolSize;
    }

    public Integer getDefaultMaxThread() {
        return DefaultStreamerConfigurator.maxThreadsPerService;
    }

    public Integer getSelectSize() {
        return DefaultStreamerConfigurator.selectSize;
    }

    public Integer getResponseCacheSize() {
        return DefaultStreamerConfigurator.responseCacheSize;
    }

    public Integer getRatio() {
        return DefaultStreamerConfigurator.ratio;
    }

    public Integer getWorkerQueue() {
        return DefaultStreamerConfigurator.workerQueue;
    }

    public Integer getWorkerTimeOut() {
        return DefaultStreamerConfigurator.workerTimeOut;
    }

    public void invock() {

    }

    public String getAlgoSigneture() {
        return yamlConfigurator.getAlgoSign();
    }

    public String getLoginSignature() {
        return yamlConfigurator.getLoginSignature();
    }

    public String getPasswordSignature() {
        return yamlConfigurator.getPasswordSignature();
    }

    public String getTimeStampSignature() {
        return yamlConfigurator.getTimeStampSignature();
    }

    public String getValidRequestTimeout() {
        return yamlConfigurator.getValidRequestTimeout()
                .toString();
    }

    public String getSignatureTemplate() {
        return " Login␣" + "timeStamp␣<b>"
                + getAlgoSigneture() + " </b> [<b> "
                + getLoginSignature() + "</b> ( Login )  <b>"
                + getPasswordSignature() + "</b> ( Password ) <b> "
                + getTimeStampSignature() + "</b> ( timeStamp )  "
                + " ] ";
    }
    public String getSignatureTemplate_Simple() {
        return " Login␣" + "timeStamp␣"
                + getAlgoSigneture() + "  [ "
                + getLoginSignature() + " ( Login )  "
                + getPasswordSignature() + " ( Password )  "
                + getTimeStampSignature() + " ( timeStamp )  "
                + " ] ";
    }

    public void checkAuth() throws BusinessException {

        FacesContext context = FacesContext.getCurrentInstance();

        //to retrieve additional parameter
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();

        String login = params.get("login");
        String timestamp = params.get("timestamp");
        String token = params.get("token");

        Policy securityLevel = servicesManager.getSecurityLevel();

        if (securityLevel == Policy.CustomSignOn) {

            if (signOn.select(new AnnotationLiteral<Custom>() {
            }).get() == null) {
                throw new BusinessException(" No Provider found for Custom Authentication ");
            }

            if (signOn.select(new AnnotationLiteral<Custom>() {
            }).get()
                    .checkIntegrity(login, timestamp, token)) {

                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                                "   ** OK **  "));
            } else {

                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                "   ** ERROR **  "));
            }
        }

    }

    private Map< String, List<Expression> > expressionsMap = new HashMap<>();
    
    public void isEmptyListExpression() {
      //  selectedFilter = null ;
     //   selectedExpression = null ;
        selectedValue =  null ;
    }
    public void updateRequirementValidation() {
       selectedFilter      = null  ;
       selectedExpression  = null  ;
       selectedValue       = null  ;
       resetExpresion();
    }

    public void undoLastFilter() {
        if (!this.expressionsMap.isEmpty()) {
            this.expressionsMap.clear()   ;
            updateRequirementValidation() ;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(  null, 
                                 new FacesMessage(FacesMessage.SEVERITY_WARN, "",
                                 " Filter Cleaned ")) ;
        }
    }

    String expressionAsString = "" ;
   
    
    private String toExpressString( String filter, List<Expression> _expressions) {
            
            StringBuilder expr = new StringBuilder("")  ;
            
            _expressions.forEach( e -> {
                
                String expression = e.getExpression() ;
                String value      = e.getValue()      ;
                 String _filter   = ""                ;
                
                expression = StringEscapeUtils.unescapeHtml4(expression) ;
                
                String operande       = "" ;
                String comparator     = "" ;
//                String _chainedFilter = "" ;
                
                if( expression.trim().startsWith("AND") ||
                    expression.trim().startsWith("OR")  ) {
                    
                      operande   = "_" + expression.trim().split(" ")[0].trim() ; // OR - AND
                      comparator = "_" + expression.trim().split(" ")[1].trim() + "_" ; // = > >= <
                }
                else if ( ! expr.toString().isEmpty()             &&
                          ( ! expression.trim().startsWith("AND") && 
                            ! expression.trim().startsWith("OR") ) )  {
                       operande  = "&"    ;
                      _filter    = filter     ;
                      comparator = expression ;
                }
                else {
                      _filter = filter       ;
                      comparator = expression ;
                }
                boolean b = expr.toString().isEmpty();
                
                if( comparator.equalsIgnoreCase("!")) {
                    expression = "_NOT_" ;
                }
                    
                if( !comparator.trim().equalsIgnoreCase("=")) {
                    if ( ! _filter.isEmpty() )
                    comparator = "=_" + comparator + "_" ;
                }
                
                expr.append( operande)
                    .append(_filter)
                    .append( comparator) 
                    .append( value ) ;
            });
            
            return expr.toString() ;
    }
    
    
    private String buildFilters() {
        
       
        return 
        expressionsMap.entrySet()
                      .stream()
                      .map( s -> toExpressString(s.getKey(), s.getValue())) 
                      .collect( Collectors.joining("&")) ;
//        
        
//           G   ( "_>_"   ) ,
//   L   ( "_<_"   ) ,
//   or  ( "_or_"  ) ,
//   OR  ( "_OR_"  ) ,
//   not ( "_not_" ) ,
//   NOT ( "_NOT_" ) ,
//   and ( "_and_" ) ,
//   AND ( "_AND_" ) ,
//   GE  ( "_>=_"  ) ,
//   LE  ( "_<=_"  ) ,
//   EQ  ( "_=_"   ) ;

    }
    
    private boolean disabledChangeFilerButtom = true ;

    public boolean isDisabledChangeFilerButtom() {
        return disabledChangeFilerButtom;
    }
    
    
    public void calculateExpression() throws UnsupportedEncodingException {
       
        if( this.expressionsMap.isEmpty()) {
           // this.operands.add("&")
           // TODO
        }
        this.expressionsMap.computeIfAbsent( selectedFilter, 
                                           k ->  new ArrayList<>()  )
                                          .add( new Expression( selectedFilter     ,
                                                                selectedExpression.replace( selectedFilter + " ", "") ,
                                                                selectedValue ) ) ;
       // selectedFilter     = null ;
       // selectedExpression = null ;
        selectedValue      = null ;
        setChainedExpresion();
        genScript()               ;
        
        disabledChangeFilerButtom = false ;
        FacesContext context = FacesContext.getCurrentInstance() ;
        context.addMessage(  null , 
                             new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                             " Filter Included ")) ;
    }
    
    private String script          = null ;
    private String scriptDecryptor = null ;

    public String getScript() throws UnsupportedEncodingException {
        
        this.script = genScript() ;
        return this.script ;
    }

    public void setScript(String script) {
        this.script = script;
    }
     
    
    private String genScript() throws UnsupportedEncodingException    {
       
      return  generateScriptCUSTOM (  getSelectedServiceUriTemplate()  , 
                                      "login"                          , 
                                      "password"                       ,
                                      buildFilters()                   , 
                                      keep.replaceAll(",", " - ")      ,
                                      acceptItem                       ,
                                      getLoginSignature()              ,
                                      getPasswordSignature()           , 
                                      getTimeStampSignature()          ,
                                      getAlgoSigneture()               ,
                                      cipherItem                       ,
                                      false                            ,
                                      selectedServiceDescription.getSecurity() ) ;
    }
    
    public String genScriptDecryptor() throws UnsupportedEncodingException {
       
      return  generateScriptDecryptor (  getSelectedServiceUriTemplate()  , 
                                        "login"                           , 
                                        "password"                        ,
                                        buildFilters()                    , 
                                        keep.replaceAll(",", " - ")       ,
                                        acceptItem                        ,
                                        getLoginSignature()               ,
                                        getPasswordSignature()            , 
                                        getTimeStampSignature()           ,
                                        getAlgoSigneture()                ,
                                        cipherItem                        ,
                                        false                             ,
                                        selectedServiceDescription.getSecurity() ) ;
    }
    
    private String selectedExpression;

    public String getSelectedExpression() {
        return selectedExpression;
    }

    public void setSelectedExpression(String selectedExpression) {
        this.selectedExpression = selectedExpression;
    }

    private String selectedValue;

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(@NotNull String value) {
        this.selectedValue = value;
    }

    private String cipherItem = null;

    public String getCipherItem() {
        return cipherItem;
    }

    private String selectedFilter = null;

    public String getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedFilter(String filter) {
        this.selectedFilter = filter;
    }

    public void setCipherItem(String cipherItem) {
        this.cipherItem = cipherItem;
    }

    private String acceptItem = null;

    public String getAcceptItem() {
        return acceptItem;
    }

    public void setAcceptItem(String acceptItem) {
        this.acceptItem = acceptItem;
    }
    
    public void redirectIfEmptyService() throws IOException {
        if (selectedServiceDescription == null  ) {
          ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
          ec.redirect( ec.getRequestContextPath() + "/index.xhtml");
    }
    }

    public void updateCipher()  {
        System.out.println(" Update Cipher......") ;
        if( acceptItem != null && ! acceptItem.contains("crypt")) {
            cipherItem = null ;
        } else {
            if( selectedServiceDescription != null ) {
              cipherItem = selectedServiceDescription.getCiphers().iterator().next();
            }
        }
        if( acceptItem != null && acceptItem.contains("template")) {
            keep = "" ;
        } 

    }
    public void updateTemplate() throws UnsupportedEncodingException  {
        System.out.println(" Update Template......") ;
        this.script = genScript() ;

    }
    
    public boolean disableFilterSelection() {
        //System.out.println("//////////////////////////////////////");
       //  System.out.println("disableFilterSelection ");
       //  System.out.println(" expressions --> " + expressionsMap);
      //   System.out.println(" selectedFilter --> " + selectedFilter);
      //  System.out.println("//////////////////////////////////////");
      
        return expressionsMap.containsKey(this.selectedFilter) ||
               this.selectedServiceDescription.getParams().keySet().isEmpty() ;
    }
    
    public boolean disableSelectionForExpressionAnsValueAndBottom() {
        return this.selectedServiceDescription.getParams().keySet().isEmpty() ;
    }
    
 //  private  boolean requiredFilter =  true ;
  // private  boolean requiredExpression =  true ;
   
//    public boolean requiredFilter() {
//           System.out.println(" +++ requiredFilter --> " + requiredFilter);
//        return requiredFilter ;
//    }
//    public boolean requiredExpression() {
//        return requiredExpression ;
//    }
    

//    public void validateInput() throws ValidatorException {
//
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Set<ConstraintViolation<MyBean>> constraintViolations = validator.validate(this);
//
//        if (constraintViolations.size() > 0) {
//            Set<String> violationMessages = new HashSet<>();
//
//            for (ConstraintViolation constraintViolation : constraintViolations) {
//                violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
//            }
//            
//          //  throw new RuntimeException("Bean is not valid:\n" + StringUtils.join(violationMessages, "\n"));
//        }
//    }
    
    
      public       String generateScriptCUSTOM ( String url          , 
                                                   String login         , 
                                                   String password      , 
                                                   String params        , 
                                                   String keep          ,
                                                   String accept        ,
                                                   String hashLogin     , 
                                                   String hashPassword  , 
                                                   String hashTimeStamp ,
                                                   String algoSign      ,
                                                   String cipher        ,
                                                   boolean encodeParams ,
                                                   String security      ) throws UnsupportedEncodingException {
            
        String _url =  url.trim() ;
        
        if( encodeParams) {
          params  = URLEncoder.encode( params.trim(), "UTF-8") ;
        } else {
          params  =  params.trim() ;
        }
        if( params != null && ! params.isEmpty() )
           _url += "?" + params ;
        
        String trustCert = _url.trim().startsWith("https") ? " -k " : " " ;
        
        String invokeService =  " curl "        +
                                trustCert       +
                                "-H \"accept: " +
                                accept + "\"  " ;
               
        if( keep != null && ! keep.isEmpty() ) {
          invokeService += " -H \"keep: " + keep + " \" " ;
        }
        
        if( cipher != null && ! cipher.isEmpty() ) {
          invokeService += " -H \"cipher: " + cipher + " \" " ;
        }
        
        return   "#!/bin/bash \n\n" 
                + " # Script generated by G-JAX-CLIENT \n" 
                + " # Author    : ---                  \n" 
                + " # Signature : " + getSignatureTemplate_Simple() + "\n\n\n" 
                + " Login=\""     + login    + "\"  \n\n" 
                + " Password=\""  + password + "\"  \n\n"
                + " TimeStamp=$(date +%s)           \n\n" 
                + getHashedScript( "Login"     , hashLogin     ) + "\n\n" 
                + getHashedScript( "Password"  , hashPassword  ) + "\n\n" 
                + getHashedScript( "TimeStamp" , hashTimeStamp ) + "\n\n"  
                + getSigneScript( algoSign )                     +  "\n\n" 
                + invokeService
                + ( ! security.toLowerCase().contains("public") ? 
                "-H \"API-key-Token: " + "$Login $TimeStamp $SIGNE\" " + "\"" : "" )
                + "\"" +_url.replaceAll(" ", "%20") + "\""             ;
    }
      
    public String generateScriptDecryptor ( String url          , 
                                               String login         , 
                                               String password      , 
                                               String params        , 
                                               String keep          ,
                                               String accept        ,
                                               String hashLogin     , 
                                               String hashPassword  , 
                                               String hashTimeStamp ,
                                               String algoSign      ,
                                               String cipher        ,
                                               boolean encodeParams ,
                                               String security      ) throws UnsupportedEncodingException {
        
        if( cipher != null && ! cipher.trim().equalsIgnoreCase("-") ) {
         
            /* Hash_256 is Always Used tu Calculate the Key */
            /* Hash_256 gives 64 characters */
            /* Divide per 4 because of HEX  */ 
            int LENGHT_KEY = Integer.parseInt(cipher.replaceAll("[^0-9]", "")) / 4 ;
            String ALGO_NAME_FOR_OPENSSL = getAlgoNameForSSL( cipher)              ;
            
            // awk '{ print > "jaxy_data_ "++i }' RS='.' data.txt
            
            return    "#!/bin/bash \n\n" 
                    + " # Script generated by G-JAX-CLIENT \n" 
                    + " # Author    : ---               \n\n" 
                    + " File=\"data.txt\"  \n\n"
                    + " Password=\""  + password + "\"  \n\n"
                    + getHashedScript( "Password"  , hashPassword  ) + "\n\n" 
                    + " KEY=` echo -n $Hashed_Password | sha256sum  | cut -d ' ' -f 1 | cut -c1-"+ LENGHT_KEY +" ` \n\n" 
                    + ( ALGO_NAME_FOR_OPENSSL.contains("cbc") ? " # Split the KEY and DATA into two files : jaxy_data_1 ; jaxy_data_2 \n"
                                                              + " # jaxy_data_1 : Containe the KEY\n" +
                                                                " # jaxy_data_2 : Containe the DATA \n\n" +
                                                                " awk '{ print > \"jaxy_data_\"++i }' RS='.' data.txt \n\n" +
                                                                " IV=` openssl enc -d -base64 -in jaxy_data_1 | xxd -ps -c 200 | tr -d '\\n'` ; \n\n" +
                                                                " openssl " + ALGO_NAME_FOR_OPENSSL + " -A -d -base64 -K \"$KEY\" -nosalt -in jaxy_data_2 -iv \"$IV\" \n\n" +
                                                                " # Remove jaxy_data_1 jaxy_data_2 \n" +
                                                                " rm  jaxy_data_1 \n" +
                                                                " rm  jaxy_data_2 \n" 
                    
                                                               : " openssl "+ ALGO_NAME_FOR_OPENSSL + " -A -d -base64 -K \"$KEY\" -nosalt -in $File " 
                      )  ;
        }
         return "" ;
     }
      
       private static String getHashedScript( String variable, String algo ) {
      
        if(algo.equalsIgnoreCase("SHA1")) {
          return " Hashed_"                            + 
                 variable.trim()                       + 
                 "=` echo -n $"                        + 
                 variable.trim()                       + 
                 " | sha1sum  | cut -d ' ' -f 1 ` \n"  + 
                 " Hashed_"                            + 
                 variable.trim()                       + 
                 "=` echo $Hashed_"                    + 
                 variable.trim()                       + 
                 " | sed 's/^0*//'`"                   ;
        }
        if(algo.equalsIgnoreCase("SHA2")) {
          return " Hashed_"                             + 
                 variable.trim()                        + 
                 "=` echo -n $"                         + 
                 variable.trim()                        + 
                 " | sha256sum  | cut -d ' ' -f 1 ` \n" + 
                 " Hashed_"                             + 
                 variable.trim()                        + 
                 "=` echo $Hashed_"                     + 
                 variable.trim()                        + 
                 " | sed 's/^0*//'`"                    ;
        }
        else if(algo.equalsIgnoreCase("MD5")) {
           return " Hashed_"                           + 
                  variable.trim()                      + 
                  "=` echo -n $"                       + 
                  variable.trim()                      + 
                  " | md5sum  | cut -d ' ' -f 1` \n"   + 
                  " Hashed_" + variable.trim()         +
                  "=` echo $Hashed_" + variable.trim() +
                  " | sed 's/^0*//'`" ;
        }
        
        return " Hashed_" + variable.trim() + "=\"$" + variable.trim() + "\""  ;
    }
    
       private static String getSigneScript( String algo ) {
      
        if(algo.equalsIgnoreCase("SHA1"))  {
          return " SIGNE=`echo -n $Hashed_Login$Hashed_Password$Hashed_TimeStamp" + 
                  " | sha1sum  | cut -d ' ' -f 1 ` \n"                            + 
                  " SIGNE=` echo $SIGNE | sed 's/^0*//' ` "                       ; 
        }
        if(algo.equalsIgnoreCase("SHA2"))  {
          return " SIGNE=`echo -n $Hashed_Login$Hashed_Password$Hashed_TimeStamp" + 
                  " | sha256sum  | cut -d ' ' -f 1 ` \n"                          + 
                  " SIGNE=` echo $SIGNE | sed 's/^0*//' ` "                       ; 
        }
        else if(algo.equalsIgnoreCase("MD5")) {
          return " SIGNE=`echo -n $Hashed_Login$Hashed_Password$Hashed_TimeStamp " + 
                 "| md5sum  | cut -d ' ' -f 1 ` \n"                                +
                 " SIGNE=` echo $SIGNE | sed 's/^0*//' ` "                         ;
        }
        return " SIGNE=`echo -n $Hashed_Login$Hashed_Password$Hashed_TimeStamp ` " ;
    }
       
    public Integer getSelectedServiceTotalLinesInScript() throws UnsupportedEncodingException {
        if( this.script == null ) {
            this.script = genScript() ;
        }
        return this.script == null  ? 2 :
               this.script.split("\r\n|\r|\n").length + 2 ;
    }
    
    public Integer getSelectedServiceTotalLinesInScriptDecryptor() throws UnsupportedEncodingException {
        if( this.scriptDecryptor == null ) {
            this.scriptDecryptor = genScriptDecryptor() ;
        }
        return this.scriptDecryptor == null  ? 2 :
               this.scriptDecryptor.split("\r\n|\r|\n").length + 2 ;
    }
    
    private String keep = "" ;

    public String getKeep() {
        System.out.println(" keep to GET !!!");
        return keep;
    }

    public void setKeep( String keep) throws UnsupportedEncodingException {
        System.out.println(" keep to SET !!!");
        this.keep = keep;
        genScript() ;
    }
    
    public String redirectToDetails( String pageRedirection)  {
        
        if( selectedServiceDescription != null ) {
          return pageRedirection ;
        }
        FacesContext context = FacesContext.getCurrentInstance()   ;
        context.addMessage( null ,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "",
                            " Select a Service before displaying Details ") ) ;
        return null ;
        
    }
    
    public void displayMessageCopy() {
    
      System.out.println(" DISPLAY CPY MESSAGE ") ;
      
      FacesMessage msg = new FacesMessage( 
              FacesMessage.SEVERITY_INFO, "Script"," Script Copied to ClipBoard") ;
      
      FacesContext context = FacesContext.getCurrentInstance() ;	
      context.addMessage("growlMsgCpy", msg) ;
        
    }	
    
    
    
//    public void enableRequirements() {
//        System.out.println(" 02 -- Enable Requirements AFTER UPDATE ! ") ;
//        requiredExpression   = true ;
//        requiredFilter   = true ;
//    }
//    

    private String getAlgoNameForSSL(String cipher) {
        
        Objects.requireNonNull(cipher) ;
        String ciph = cipher.trim().replace("_", "-").toLowerCase() ;
        if( ciph.startsWith("des")) {
            ciph = ciph.replaceAll("\\d", "").replace("--", "-") ;
        }
        if( ciph.contains("ede")) {
            ciph = ciph.replaceAll("ede", "-ede3").replace("-ecb", "");
        }
        
        return ciph ;
    }

  
   
}
