package edu.tuc.taxieinstiegstatistik.datenbank;


import org.junit.Assert;
import org.junit.Test;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DatenbankAdapterTest {

    @Test
    public void testCoordinateQuery() {
        DatenbankAdapter datenbankAdapter = DatenbankAdapter.getInstance();
        ArrayList<Point> objects = datenbankAdapter.getStartingPointCoordinatesFor1Day();

        for (Point p : objects)
            System.out.println("SRID: " + p.getSrid() + ", X: " + p.getX() + ", Y: " + p.getY());

        Assert.assertTrue(!objects.isEmpty());
    }


}
