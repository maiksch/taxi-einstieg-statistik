package edu.tuc.taxieinstiegstatistik.datenbank;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.DelegatingConnection;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class DatenbankAdapter {

    /**
     * Singleton Instanz, die nur einmal vorhanden ist
     */
    private static final DatenbankAdapter INSTANCE = new DatenbankAdapter();

    private boolean isConfigLoaded;
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
    private DatenbankAdapter() {

        isConfigLoaded = loadConfig();

        if (isConfigLoaded) {
            ds = new BasicDataSource();

            ds.setDriverClassName("org.postgresql.Driver");
            ds.setUsername(user);
            ds.setPassword(password);
            ds.setUrl("jdbc:postgresql://" + host + ":" + port + "/" + db);
            if (!ssl.isEmpty())
                ds.setConnectionProperties("ssl=" + ssl + ";sslfactory=org.postgresql.ssl.NonValidatingFactory");
            ds.setPoolPreparedStatements(true);
            ds.setAccessToUnderlyingConnectionAllowed(true);

            ds.setMinIdle(5);
            ds.setMaxIdle(20);
            ds.setMaxOpenPreparedStatements(180);
        }
    }

    /**
     * Liefert eine Instanz der DatenbankAdapter Klasse
     *
     * @return
     */
    public static DatenbankAdapter getInstance() {
        return INSTANCE;
    }

    /**
     * @return
     */
    public ArrayList<Point> getStartingPointCoordinates(String ab, String bis) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Point> result = new ArrayList<>();

        // Query der direkt die konvertierung von UTM zu LatLong vornimmt, Zeitraum von 12:00:00 bis 13:00:00
        String query = "select ST_Transform(target_cand_geom, 4326) as geom from fcd_osm_1day WHERE source_candidate_nr = ? and source_time between ? and ? ";

        try {
            // Statement vorbereiten
            connection = DatenbankAdapter.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1); // source_candiddate_nr = 1, weil dies immer dem Einstieg der Taxifahrt entspricht
            statement.setTimestamp(2, Timestamp.valueOf("2014-10-07 " + ab)); // hole alle daten von
            statement.setTimestamp(3, Timestamp.valueOf("2014-10-07 " + bis)); // hole alle daten bis


            // Statement abschicken
            resultSet = statement.executeQuery();

            /*
            * Add the geometry types to the connection. Note that you
            * must cast the connection to the pgsql-specific connection
            * implementation before calling the addDataType() method.
            */
            connection = ((DelegatingConnection) connection).getInnermostDelegate();
            ((org.postgresql.PGConnection) connection).addDataType("geometry", Class.forName("org.postgis.PGgeometry"));

            while (resultSet.next()) {
                Point geom = (Point) ((PGgeometry) resultSet.getObject(1)).getGeometry();
                result.add(geom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * db.properties Datei muss im Home-Verzeichnis des Betriebssystemnutzers liegen
     */
    private boolean loadConfig() {

        final Properties props = new Properties();

        try (
                // property file needs to be located in the home directory of the system user under the "taxistatistik" folder
                final FileInputStream input = new FileInputStream(new File(System.getProperty("user.home") + File.separator + "taxistatistik" + File.separator + "db.properties"))) {

            // load a properties file
            props.load(input);

            // get the local property values
            port = props.getProperty("port");
            host = props.getProperty("host");
            db = props.getProperty("db");
            user = props.getProperty("usr");
            password = props.getProperty("pw");
            ssl = props.getProperty("ssl");
          
             // findet die db.properties nicht, muss im classpath sein?
//            ResourceBundle myResources = ResourceBundle.getBundle("user.home.taxistatistik.db");
//            Enumeration <String> keys = myResources.getKeys();
//            while(keys.hasMoreElements()) {
//         	   String key = keys.nextElement();
//         	   String value = myResources.getString(key);
//         	   System.out.println(key + ":" + value);
//            }

        } catch (IOException ex) {

            ex.printStackTrace();
            return false;

        }

        return true;
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