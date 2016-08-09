package edu.tuc.taxieinstiegstatistik.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by Smadback on 09.08.2016.
 */
@Path("/getEinstiegsPunkt")
public class EinstiegsPunktService {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public EinstiegsPunkt getEingstiegsPunkt() {

        EinstiegsPunkt ep = new EinstiegsPunkt();
        ep.setX("10.521111");
        ep.setY("52.269167,0");
        ep.setDatum(new Date());

        return ep;
    }

}
