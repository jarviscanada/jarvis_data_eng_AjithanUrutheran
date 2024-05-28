package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.entity.IexQuote;
import ca.jrvs.apps.trading.entity.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ca.jrvs.apps.trading.TestConfig;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
//@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {
    @Autowired
    private QuoteDao quoteDao;
    //private Quote savedQuote;

//    @Before
//    public void init() {
//        quoteDao.deleteAll();
//    }
    @Test
    public void saveQuote(){
        Quote savedQuote = new Quote();
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setTicker("AAPL");
        savedQuote.setLastPrice(10.1d);
        quoteDao.save(savedQuote);
    }

}
