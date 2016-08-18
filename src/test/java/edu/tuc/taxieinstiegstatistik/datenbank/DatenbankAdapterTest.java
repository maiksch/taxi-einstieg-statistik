package edu.tuc.taxieinstiegstatistik.datenbank;


import org.junit.Assert;
import org.junit.Test;
import org.postgis.Point;

import java.util.ArrayList;


public class DatenbankAdapterTest {

    @Test
    public void testCoordinateQuery() {
        DatenbankAdapter datenbankAdapter = DatenbankAdapter.getInstance();
        ArrayList<Point> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");

        for (Point p : objects)
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");


        Assert.assertTrue(!objects.isEmpty());
    }


}