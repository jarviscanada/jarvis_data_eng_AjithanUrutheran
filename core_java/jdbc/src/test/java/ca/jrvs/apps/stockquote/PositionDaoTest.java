package ca.jrvs.apps.stockquote;

import ca.jrvs.apps.stockquote.dao.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

public class PositionDaoTest {
    private PositionDao positionDao;
    private Position tempPosition;

    private Connection connection;
    private PreparedStatement statement;
    private DatabaseConnectionManager dcm;



    @Before
    public void setUp(){
        try{
            /*
                @Args
                Hostname, databasename, username, password
            */
            dcm = new DatabaseConnectionManager("localhost","stock_quote","postgres","password");
            connection = dcm.getConnection();
            positionDao = new PositionDao(connection);

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @After
    public void destroy(){
        try{
            if (connection != null){
                connection.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void SaveTest(){

    }

    @Test
    public void FindPositionTest(){

    }

    @Test
    public void DeleteIdTest(){

    }





}
