package ca.jrvs.apps.stockquote;

import ca.jrvs.apps.stockquote.dao.DatabaseConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dao.QuoteHttpHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;

public class QuoteService_UnitTest {
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
            quoteDao = new QuoteDao(connection);
            qhelper = new QuoteHttpHelper("78aaf7f7c4mshc87167b1e7f9539p14d55ejsn59e734b9a63c");

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
    public void saveTest(){
        Quote newQuote = qhelper.fetchQuoteInfo("AAPL");
        quoteDao.save(newQuote);

        Optional<Quote> ticketQuote = quoteDao.findById("AAPL");
        Quote testQuote = ticketQuote.orElse(null);

        assertEquals(newQuote.getChange(), testQuote.getChange(), 0.02);
    }

    @Test
    public void viewTest(){
        Optional<Quote> ticketQuote = quoteDao.findById("TSLA");
        Quote testQuote = ticketQuote.orElse(null);
        assertEquals("TSLA",testQuote.getTicker());
    }

    @Test
    public void deleteTest(){
        Quote newQuote = qhelper.fetchQuoteInfo("GOOG");
        quoteDao.save(newQuote);
        quoteDao.deleteById("GOOG");
        Optional<Quote> ticketQuote = quoteDao.findById("GOOG");

        if (ticketQuote.isPresent()){
            Quote testQuote = ticketQuote.get();
            assertNull(testQuote.getTicker());
        }
        else{
            assertTrue("No Quote found. Optional is empty", true);
        }
    }


}
