package edu.tuc.taxieinstiegstatistik.datenbank;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatenbankAdapter {

    private static DatenbankAdapter datenbankAdapter;
    private BasicDataSource ds;
    private String port;
    private String host;
    private String db;
    private String user;
    private String password;
    private String ssl;

    /**
     * Beispiel Query für die Datenbank in DatenbankAdapterTest.java
     */
    public DatenbankAdapter() {

        loadConfig();
        ds = new BasicDataSource();

        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + db);
        if (!ssl.isEmpty())
            ds.setConnectionProperties("ssl=" + ssl + ";sslfactory=org.postgresql.ssl.NonValidatingFactory");
        ds.setPoolPreparedStatements(true);

        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
    }

    /**
     * Liefert eine Instanz der DatenbankAdapter Klasse
     *
     * @return
     */
    public static DatenbankAdapter getInstance() {
        if (datenbankAdapter == null) {
            datenbankAdapter = new DatenbankAdapter();
        }

        return datenbankAdapter;
    }

    /**
     * db.properties Datei muss im Home-Verzeichnis des Betriebssystemnutzers liegen
     */
    private void loadConfig() {

        Properties props = new Properties();
        InputStream input = null;

        try {
            //input = new FileInputStream("db.properties");
            input = new FileInputStream(new File(System.getProperty("user.home") + File.separator + "taxistatistik" + File.separator + "db.properties"));

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

    /**
     * Liefert die Verbindung zur Datenbank aus dem Connection-Pool
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


}