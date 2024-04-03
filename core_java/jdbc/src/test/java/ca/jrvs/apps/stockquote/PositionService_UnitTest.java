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

public class PositionService_UnitTest {
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
//            quoteDao.save(qService.fetchQuoteDataFromAPI("AAPL").get());
//            quoteDao.save(qService.fetchQuoteDataFromAPI("MSFT").get());
//            quoteDao.save(qService.fetchQuoteDataFromAPI("TSLA").get());
//            quoteDao.save(qService.fetchQuoteDataFromAPI("AMD").get());
//            quoteDao.save(qService.fetchQuoteDataFromAPI("GOOG").get());

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
    public void FindTest(){
        Position expected = new Position();
        expected.setTicker("AAPL");
        expected.setNumOfShares(60);
        expected.setValuePaid(148.20);

        pService.buy("AAPL",60,148.20);

        Position actual = positionDao.findById("AAPL").get();

        // Check if the actual position is not null
        assertNotNull(actual);

        // Check if the actual position matches the expected position
        assertEquals(expected.getNumOfShares(), actual.getNumOfShares());
    }

    @Test
    public void DeleteIdTest(){


        Position expected = new Position();
        expected.setTicker("AAPL");
        expected.setNumOfShares(75);
        expected.setValuePaid(120.21);

        pService.buy("AAPL", 75, 120.21);

        pService.sell("AAPL");


        boolean isDeleted = positionDao.findById("AAPL").isEmpty();
        assertTrue(isDeleted);
    }
    @Test
    public void NotDeletedIdTest(){


        Position expected = new Position();
        expected.setTicker("AAPL");
        expected.setNumOfShares(75);
        expected.setValuePaid(120.21);

        pService.buy("AAPL", 75, 120.21);

        //pService.sell("AAPL"); NOT DELETED


        boolean isDeleted = positionDao.findById("AAPL").isEmpty();
        assertFalse(isDeleted);
    }





}
