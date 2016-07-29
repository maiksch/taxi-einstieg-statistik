package edu.tuc.taxieinstiegstatistik.datenbank;


import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatenbankAdapterTest {

    @Test
    public void sequence() {
        Statement s = null;
        ResultSet r = null;
        DatenbankAdapter dbAdapter = new DatenbankAdapter();
        Connection conn = dbAdapter.getConnection();

        try {
            s = conn.createStatement();
            r = s.executeQuery("select * from fcd_osm_1day WHERE assetid = '1614556_1800649'");

            while (r.next())
                System.out.println(r.getString("source_link"));

            s.close();
            dbAdapter.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
