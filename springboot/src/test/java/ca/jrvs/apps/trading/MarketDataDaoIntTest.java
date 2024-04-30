package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.entity.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoIntTest {
    private MarketDataDao dao;

    @Before
    public void init(){

        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setToken("INSERTTOKEN");
        marketDataConfig.setHost("https://api.iex.cloud/v1/data/core/quote/");

        dao = new MarketDataDao(HttpClient.newHttpClient(),marketDataConfig);
    }

    @Test
    public void findIexQuotesByTickers() throws IOException{
        //happy path
        List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL","FB"));
        assertEquals(2,quoteList.size());
        assertEquals("AAPL",quoteList.get(0).getSymbol());

        //sad path
        try{
            dao.findAllById(Arrays.asList("AAPL","FB2"));
            fail();
        }
        catch(IllegalArgumentException e){
            assertTrue(true);
        }
        catch(Exception e){
            fail();
        }
    }


}
