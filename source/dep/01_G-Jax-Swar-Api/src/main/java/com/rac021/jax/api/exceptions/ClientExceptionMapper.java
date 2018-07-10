
package com.rac021.jax.api.exceptions ;

import javax.ws.rs.ext.Provider ;
import javax.ws.rs.core.Response ;
import javax.ws.rs.core.MediaType ;
import javax.ws.rs.ext.ExceptionMapper ;
import javax.ws.rs.WebApplicationException ;

/**
 *
 * @author yahiaoui
 */

@Provider
public class ClientExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
       return Response.status(Response.Status.OK).header("x-reason",
                exception.getMessage()).entity(
                        "<status>" + exception.getLocalizedMessage() + "</status>").
                type(MediaType.APPLICATION_XML).build() ;
    }
}
