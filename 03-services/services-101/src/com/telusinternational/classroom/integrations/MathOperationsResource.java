package com.telusinternational.classroom.integrations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.servlet.ServletContext;

@Path("/math")
public class MathOperationsResource extends Application {
	@Context
	ServletContext servletContext;

	@GET
	@Path("/add")
	@Produces("application/xml")
	public String add(@QueryParam("val1") float val1, @QueryParam("val2") float val2) {
       return "<response>" + (val1 + val2) + "</response>";
	}
	
	@GET
	@Path("/substract")
	@Produces("application/xml")
	public String substract(@QueryParam("val1") float val1, @QueryParam("val2") float val2) {
       return "<response>" + (val1 - val2) + "</response>";
	}
	
	@GET
	@Path("/multiply")
	@Produces("application/xml")
	public String multiply(@QueryParam("val1") float val1, @QueryParam("val2") float val2) {
       return "<response>" + (val1 * val2) + "</response>";
	}
	
	@GET
	@Path("/divide")
	@Produces("application/xml")
	public String divide(@QueryParam("val1") float val1, @QueryParam("val2") float val2) {
       return "<response>" + (val1 / val2) + "</response>";
	}
}
