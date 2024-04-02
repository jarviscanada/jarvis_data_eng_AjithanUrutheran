package ca.jrvs.apps.stockquote.service;

import ca.jrvs.apps.stockquote.controller.StockQuoteController;
import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dao.QuoteHttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(StockQuoteController.class);

    private QuoteDao dao;
    private QuoteHttpHelper httpHelper;

    public QuoteService(QuoteDao dao, QuoteHttpHelper httpHelper){
        this.dao = dao;
        this.httpHelper = httpHelper;
    }
    /**
     * Fetches latest quote data from endpoint
     * @param ticker
     * @return Latest quote information or empty optional if ticker symbol not found
     */
    public Optional<Quote> fetchQuoteDataFromAPI(String ticker) {
        Optional<Quote> apiQuote = Optional.ofNullable(httpHelper.fetchQuoteInfo(ticker));
        logger.info("Retrieved ticker from fetchQuote of type Optional<Quote>");
        return apiQuote;
    }



}
