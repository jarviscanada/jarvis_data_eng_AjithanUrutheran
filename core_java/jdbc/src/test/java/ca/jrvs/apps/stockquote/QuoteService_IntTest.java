package ca.jrvs.apps.stockquote;

import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dao.QuoteHttpHelper;
import ca.jrvs.apps.stockquote.service.QuoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class QuoteService_IntTest {
    @Mock
    private QuoteDao mockQuoteDao;

    @Mock
    private QuoteHttpHelper qHelper;

    @InjectMocks
    private QuoteService qService;

    @Before
    public void setUp(){

        mockQuoteDao = mock(QuoteDao.class);
        qService = new QuoteService(mockQuoteDao, qHelper);

    }

    @Test
    public void FetchDataTest(){
        String ticker = "AAPL";

        Optional<Quote> expected = Optional.of(new Quote());
        expected.get().setTicker("AAPL");
        expected.get().setVolume(3000);

        when(qHelper.fetchQuoteInfo(ticker)).thenReturn(expected.get());

        //when(qService.fetchQuoteDataFromAPI(ticker)).thenReturn(expected);

        Optional<Quote> actual = qService.fetchQuoteDataFromAPI(ticker);

        assertEquals(expected.get().getTicker(),actual.get().getTicker());
        assertEquals(expected.get().getVolume(),actual.get().getVolume());
    }

    @Test
    public void FetchDataFalseTicker(){
        String ticker = "AAPL";

        // Stubbing the fetchQuoteInfo method to return a Quote object
        when(qHelper.fetchQuoteInfo(ticker)).thenReturn(new Quote());

        // Invoking the method under test
        Optional<Quote> actual = qService.fetchQuoteDataFromAPI(ticker);

        // Asserting that the returned Optional is empty
        assertNull(actual.get().getTicker());
    }
}
