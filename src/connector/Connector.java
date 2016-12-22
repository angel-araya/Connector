package connector;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.HashMap;

public class Connector {

    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public boolean testConnection(String dbURL, String user, String password, String className, String jarPath) {
        
        Connection connection;
        try {      
            File file = new File(jarPath);
            URLClassLoader classLoader = new URLClassLoader(new URL[] { file.toURI().toURL() });
          
            Driver driver = (Driver) Class.forName(className, true, classLoader).newInstance();
            
            DriverManager.registerDriver(new DriverShell(driver));
            
            connection = DriverManager.getConnection(dbURL, user, password);
            
            if (connection == null){
                System.out.println("Connection couldn't be created, aborting");
                return false;
            }
            
            System.out.println("Sucess!");
            connection.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Something went wrong, abort");
            ex.printStackTrace();
            return false;
        }
    }

    void testConnection(HashMap<String, String> props) {
        this.testConnection(props.get("url"), props.get("user"), props.get("password"), props.get("className"), props.get("jarPath"));
    }
}
