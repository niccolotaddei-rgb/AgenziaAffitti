package it.agenzia.model;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Singleton per il caricamento del driver JDBC e dell'URI di connessione,
 * letti dal file esterno db/db.properties (che viene copiato in WEB-INF/classes/db/
 * durante il deploy da Eclipse).
 *
 * Stesso pattern del progetto ProgettoWeb3_2 del prof. Benigni.
 */
public class DBDriver {

    private static DBDriver dbInstance = null;

    private String driverString  = null;
    private String connectionURI = null;

    private DBDriver() throws Exception {
        try {
            Properties properties = new Properties();
            InputStream input = DBDriver.class.getResourceAsStream("/db/db.properties");
            properties.load(input);
            input.close();

            driverString  = properties.getProperty("driverString");
            connectionURI = properties.getProperty("connectionURI");

            // Caricamento del driver JDBC
            Class.forName(driverString).getDeclaredConstructor().newInstance();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Driver non trovato o errore nel suo caricamento.");
            cnfe.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBDriver getInstance() throws Exception {
        if (dbInstance == null) dbInstance = new DBDriver();
        return dbInstance;
    }

    public String getConnectionURI() {
        return this.connectionURI;
    }
}
