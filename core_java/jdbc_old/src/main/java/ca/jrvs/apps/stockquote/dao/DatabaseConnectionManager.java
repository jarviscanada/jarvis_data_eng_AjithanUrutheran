package ca.jrvs.apps.stockquote.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseConnectionManager {
    private final String url;
    private final Properties properties;
    private final Logger logger = LoggerFactory.getLogger(DatabaseConnectionManager.class);

    public DatabaseConnectionManager(String host, String databaseName, String username, String password){
        this.url = "jdbc:postgresql://"+host+"/"+databaseName;
        this.properties = new Properties();
        this.properties.setProperty("user",username);
        this.properties.setProperty("password",password);
    }

    public Connection getConnection() throws SQLException{
        logger.info("Connection Successful.");
        return DriverManager.getConnection(this.url, this.properties);
    }
}
