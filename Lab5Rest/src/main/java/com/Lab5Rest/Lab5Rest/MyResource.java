
package com.Lab5Rest.Lab5Rest;

import javax.ws.rs.core.MediaType; 

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/myresource")
public class MyResource {

	@GET
	@Path("/{name}") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String helloName(@PathParam("name") String name) 
	{ 
		return "Hello " + name; 
	}
}


