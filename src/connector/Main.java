package connector;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();

        switch (args.length) {
            case 0:
                HashMap<String, String> props = new HashMap();
                props.put("url", "jdbc:postgresql://localhost:5432/SitescopeTest");
                props.put("user", "postgres");
                props.put("password", "admin");
                props.put("className", "org.postgresql.Driver");
                props.put("jarPath", "libs/postgresql-9.4-1203.jdbc4.jar");
                connector.testConnection(props);
                break;
            case 5:
                connector.testConnection(args[0], args[1], args[2], args[3], args[4]);
                break;
            default:
                System.out.println("Please provide the appropiate parameters");
        }
    }
}
