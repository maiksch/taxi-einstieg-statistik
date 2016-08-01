package edu.tuc.taxieinstiegstatistik.datenbank;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatenbankAdapter {

    Connection conn = null;
    private String port;
    private String host;
    private String db;
    private String user;
    private String password;
    private String ssl;

    public DatenbankAdapter() {

        loadConfig();

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

    /**
     * db.properties Datei muss im root-Ordner des Projekts sein (gibts bei Disco)
     */
    private void loadConfig() {

        Properties props = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("db.properties");

            // load a properties file
            props.load(input);

            // get the property value and print it out
            port = props.getProperty("port");
            host = props.getProperty("host");
            db = props.getProperty("db");
            user = props.getProperty("usr");
            password = props.getProperty("pw");
            ssl = props.getProperty("ssl");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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