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
        List<SimpleEntry<Point,Date>> objects = datenbankAdapter.getStartingPointCoordinates("00:00:00", "24:00:00");

        
        
        for (SimpleEntry<Point, Date> list : objects)
            System.out.println(list.getKey() + ", " + list.getValue());


        Assert.assertTrue(!objects.isEmpty());
    }


}