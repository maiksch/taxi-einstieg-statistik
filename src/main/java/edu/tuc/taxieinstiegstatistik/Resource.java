package edu.tuc.taxieinstiegstatistik;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("home")
public class Resource {


    //	  @GET
//	  @Path("hello")
//	  @Produces(MediaType.TEXT_PLAIN)
//	  public String helloWorld() {
//	    return "Hello, world!";
//		}	    
    @GET
    @Path("param")
    @Produces(MediaType.TEXT_PLAIN)
    public String paramMethod(@QueryParam("name") String name) {
        return "Hello, " + name;
    }

    @GET
    @Path("path/{var}")
    @Produces(MediaType.TEXT_PLAIN)
    public String pathMethod(@PathParam("var") String name) {
        return "Hello, " + name;
    }

//	    @POST
//	    @Path("post")
//	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	    @Produces(MediaType.TEXT_HTML)
//	    public String postMethod(@FormParam("name") String name) {
//	      return "<h2>Hello, " + name + "</h2>";

    @POST
    @Path("taxi")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postMethod(@FormParam("function") String function) {

        String add;

        if (function == "start") {

            //CLZ
            add = "addMarker(layer_markers, 10.354649, 51.805850, popuptext_clz);";
        } else if (function == "ziel") {

            //GOE
            add = "addMarker(layer_markers, 9.935782, 51.540453, popuptext_goe);";
        } else {

            //Fehlerfall
            add = "addMarker(layer_markers, 9, 51, popup);";
        }


        return add;
    }

}

