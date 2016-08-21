package edu.tuc.taxieinstiegstatistik.datenbank;


import org.junit.Assert;
import org.junit.Test;
import org.postgis.Point;

import java.sql.Timestamp;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;


public class DatenbankAdapterTest {

    @Test
    public void testCoordinateQuery() {
        DatenbankAdapter datenbankAdapter = DatenbankAdapter.getInstance();
//      ArrayList<SimpleEntry<Point,Timestamp>> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");
        ArrayList<Point> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");

//        for (Entry<Point, Timestamp> p : objects)
//            System.out.println("(" + p.getKey() + ", " + p.getKey() + ")");
          for (Point p : objects)
              System.out.println("(" + p.getX() + ", " + p.getY() + ")");

        Assert.assertTrue(!objects.isEmpty());
    }


}