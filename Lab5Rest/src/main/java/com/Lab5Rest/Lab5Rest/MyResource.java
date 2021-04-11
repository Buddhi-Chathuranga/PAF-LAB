
package com.Lab5Rest.Lab5Rest;

import javax.ws.rs.core.MediaType; 

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
	@GET
	@Path("/{name}") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String helloName(@PathParam("name") String name) 
	{ 
		return "Hello " + name; 
	}
}


