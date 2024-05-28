package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.entity.Quote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class TempTest {

    @Autowired
    QuoteDao quoteDao;

    @Test
    public void empty(){
        Quote savedQuote = new Quote();
        savedQuote.setAskPrice(10d);
        savedQuote.setAskSize(10);
        savedQuote.setBidPrice(10.2d);
        savedQuote.setBidSize(10);
        savedQuote.setTicker("AAPL");
        savedQuote.setLastPrice(10.1d);

        quoteDao.save(savedQuote);
        assertEquals(0, 0);
    }
}
