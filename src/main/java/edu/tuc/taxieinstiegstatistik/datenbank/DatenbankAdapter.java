package edu.tuc.taxieinstiegstatistik.datenbank;

import java.sql.*;
import java.util.Properties;

public class DatenbankAdapter {

    Connection conn = null;
    private String port = "9999";
    private String host = "tesseract.in.tu-clausthal.de";
    private String db = "gis_fcd_dlr";
    private String user = "sc";
    private String password = "WL9RUuY9wtuGmQ";
    private String ssl = "true";

    public DatenbankAdapter() {
        if (conn == null) {
            /*
            * Load the JDBC driver and establish a connection.
            */
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL Treiber nicht gefunden");
            }

            String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;

            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", password);
            props.setProperty("ssl", ssl);
            props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

            try {
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {

        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }


}