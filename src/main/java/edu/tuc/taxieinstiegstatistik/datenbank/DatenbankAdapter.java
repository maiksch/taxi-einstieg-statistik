package edu.tuc.taxieinstiegstatistik.datenbank;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.DelegatingConnection;
import org.postgis.PGgeometry;
import org.postgis.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private DatenbankAdapter() {

        loadConfig();
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
     * @return
     */
    public ArrayList<Point> getStartingPointCoordinatesFor1Day() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Point> result = new ArrayList<>();

        // Query
        String query = "select target_cand_geom from fcd_osm_1day WHERE source_candidate_nr = ?";

        try {
            // Statement vorbereiten
            connection = DatenbankAdapter.getInstance().getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1);
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
    private void loadConfig() {

        Properties props = new Properties();
        InputStream input = null;

        try {
            // property file needs to be located in the home directory of the system user under the "taxistatistik" folder
            input = new FileInputStream(new File(System.getProperty("user.home") + File.separator + "taxistatistik" + File.separator + "db.properties"));

            // load a properties file
            props.load(input);

            // get the property value
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