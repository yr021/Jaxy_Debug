
package com.rac021.jax.service.discovery ;

import java.util.Map ;
import java.util.Set ;
import javax.xml.bind.annotation.XmlType ;
import javax.xml.bind.annotation.XmlRootElement ;

/**
 *
 * @author ryahiaoui
 */

 @XmlRootElement
 @XmlType(propOrder = { "serviceName", "security", "ciphers" , "accepts" ,
                        "params" , "maxThreads", "template"  } )
 
 public class ServiceDescription {
        
    private  String              serviceName ;
    private  String              security    ;

    private  Map<String, String> params      ;
    
    private  Set<String>         ciphers     ;
    private  Set<String>         accepts     ;
        
    private  Integer             maxThreads  ;
    
    private  String              template    ;
    
    public ServiceDescription( String name                , 
                                 String security            , 
                                 Map<String, String> params , 
                                 Set<String> ciphers        ,
                                 Set<String> accepts        ,
                                 int maxThreads             ,
                                 String template         )  {
        
        this.serviceName  = name       ;
        this.security     = security   ;
        this.params       = params     ;
        this.accepts      = accepts    ;
        this.ciphers      = ciphers    ;
        this.maxThreads   = maxThreads ;
        this.template     = template != null ?
                            template.replaceAll("(?i)header *\\{\\{\\{ *\n", "")
                                    .replaceAll("(?i)body *\\{\\{\\{ *\n", "")
                                    .replaceAll("(?i)footer *\\{\\{\\{ *\n", "")
                                    .replaceAll("\\}\\}\\} *\n", "") : null ;
    }
    
    public ServiceDescription() {
    }

    public String getServiceName() {
        return serviceName ;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName ;
    }
  
    public void setSecurity(String security) {
        this.security = security ;
    }

    public String getSecurity() {
        return security ;
    }

    public Map<String, String> getParams() {
        return params ;
    }

    public void setParams(Map<String, String> params) {
        this.params = params ;
    }

    public Set<String> getCiphers() {
        return ciphers ;
    }

    public void setCiphers(Set<String> ciphers) {
        this.ciphers = ciphers ;
    }

    public Set<String> getAccepts() {
        return accepts ;
    }

    public void setAccepts(Set<String> accepts) {
        this.accepts = accepts ;
    }

    public Integer getMaxThreads() {
        return maxThreads ;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads ;
    }

    public String getTemplate() {
        return template ;
    }

    public void setTemplate(String template) {
        this.template = template ;
    }
    
 }
