package ca.jrvs.apps.stockquote;

import ca.jrvs.apps.stockquote.dao.*;
import ca.jrvs.apps.stockquote.service.PositionService;
import ca.jrvs.apps.stockquote.service.QuoteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

public class PositionDaoTest {
    private QuoteService qService;
    private PositionService pService;

    private PositionDao positionDao;
    private QuoteDao quoteDao;


    private Connection connection;
    private PreparedStatement statement;
    private DatabaseConnectionManager dcm;
    private QuoteHttpHelper qhelper;

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
            quoteDao = new QuoteDao(connection);
            qhelper = new QuoteHttpHelper("78aaf7f7c4mshc87167b1e7f9539p14d55ejsn59e734b9a63c");

            qService = new QuoteService(quoteDao,qhelper);
            pService = new PositionService(positionDao);

            //Populate tables to prevent foreign key interference
            quoteDao.save(qService.fetchQuoteDataFromAPI("AAPL").get());
            quoteDao.save(qService.fetchQuoteDataFromAPI("MSFT").get());
            quoteDao.save(qService.fetchQuoteDataFromAPI("TSLA").get());
            quoteDao.save(qService.fetchQuoteDataFromAPI("AMD").get());
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
    public void SaveFindTest(){
        Position expected = new Position();
        expected.setTicker("AAPL");
        expected.setNumOfShares(60);
        expected.setValuePaid(148.20);

        pService.buy("AAPL",60,148.20);

        Position actual = positionDao.findById("AAPL").orElse(null);

        // Check if the actual position is not null
        assertNotNull(actual);

        // Check if the actual position matches the expected position
        assertEquals(expected.getNumOfShares(), actual.getNumOfShares());

    }

    @Test
    public void DeleteIdTest(){


        pService.buy("AAPL",90,168.20);
        positionDao.deleteById("AAPL");
        Optional<Position> ticketPosition = positionDao.findById("AAPL");
        if (ticketPosition.isPresent()){
            Position testPosition = ticketPosition.get();
            assertNull(testPosition.getTicker());
        }
        else{
            assertTrue("No Quote found. Optional is empty", true);
        }
    }





}
