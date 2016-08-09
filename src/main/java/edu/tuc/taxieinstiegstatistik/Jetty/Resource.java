package edu.tuc.taxieinstiegstatistik.Jetty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("home")
public class Resource {


	  @GET
	  @Path("xml.kml")
	  @Produces(MediaType.TEXT_PLAIN)
	  public String helloWorld() {
		  return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><kml xmlns=\"http://earth.google.com/kml/2.0\" xmlns:atom=\"http://www.w3.org/2005/Atom\"><Document><name>2012 Earthquakes, Magnitude 5</name><atom:author><atom:name>U.S. Geological Survey</atom:name></atom:author><atom:link href=\"http://earthquake.usgs.gov\"/><Folder><name>Magnitude 5</name><Placemark id=\"2012 Feb 9 18:52:48.39 UTC\"><name>2012 Feb 9 18:52:48.39 UTC</name><magnitude>45.0</magnitude><Point><coordinates>10.521111,52.269167,0</coordinates></Point></Placemark><Placemark id=\"2012 Feb 9 18:52:48.39 UTC\"><name>2012 Feb 9 18:52:48.39 UTC</name><magnitude>5.9</magnitude><Point><coordinates>10.521111,52.269167,0</coordinates></Point></Placemark></Folder>/Document></kml>";
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

