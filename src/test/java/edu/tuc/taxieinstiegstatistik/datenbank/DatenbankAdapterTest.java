package edu.tuc.taxieinstiegstatistik.datenbank;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;


public class DatenbankAdapterTest {

    @Test
    public void testConnection() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // Beispiel Query
        String query = "select * from fcd_osm_1day WHERE assetid = ?";

        try {
            // Statement vorbereiten
            connection = DatenbankAdapter.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, "1614556_1800649");
            // Statement abschicken
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("source_link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Assert.assertTrue(resultSet != null);

    }


}
