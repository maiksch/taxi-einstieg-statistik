package edu.tuc.taxieinstiegstatistik.datenbank;


import org.junit.Assert;
import org.junit.Test;
import org.postgis.Point;
import javafx.util.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


public class DatenbankAdapterTest{

    @Test
    public void testCoordinateQuery()  {
        DatenbankAdapter datenbankAdapter = DatenbankAdapter.getInstance();
<<<<<<< HEAD
//      ArrayList<SimpleEntry<Point,Timestamp>> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");
        ArrayList<Point> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");
=======
        List<SimpleEntry<Point,Date>> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");

        
        
        for (SimpleEntry<Point, Date> list : objects)
            System.out.println(list.getKey() + ", " + list.getValue());
>>>>>>> refs/remotes/origin/KyleBranch

//        for (Entry<Point, Timestamp> p : objects)
//            System.out.println("(" + p.getKey() + ", " + p.getKey() + ")");
          for (Point p : objects)
              System.out.println("(" + p.getX() + ", " + p.getY() + ")");

        Assert.assertTrue(!objects.isEmpty());
    }


}