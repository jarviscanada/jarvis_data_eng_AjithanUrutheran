package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.entity.IexQuote;
import ca.jrvs.apps.trading.entity.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {

    /**
     * Find an IexQuote
     * @param ticker
     * @return IexQuote object
     * @throws IllegalArgumentException if ticker is invalid
     */
    @Autowired
    private MarketDataDao marketDataDao;

    @Autowired
    private QuoteDao quoteDao;

    public QuoteService(MarketDataDao marketDataDao){
        this.marketDataDao = marketDataDao;
    }
    public IexQuote findIexQuoteByTicker(String ticker) {
        //TODO
        Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
        return iexQuote.get();
    }

    /**
     * Update quote table against IEX source
     *
     * - get all quotes from the db
     * - for each ticker get IexQuote
     * - convert IexQuote to Quote entity
     * - persist quote to db
     *
     * @throws ResourceNotFoundException if ticker is not found from IEX
     * @throws DataAccessException if unable to retrieve data
     * @throws IllegalArgumentException for invalid input
     */
    public void updateMarketData() {
        List <Quote> quoteToUpdate = findAllQuotes();
        List <String> tickersToUpdate = new ArrayList<>();

        for (Quote x : quoteToUpdate){
            IexQuote temp = findIexQuoteByTicker(x.getTicker());
            //quoteDao.save(buildQuoteFromIexQuote(temp));
            saveQuote(buildQuoteFromIexQuote(temp));
        }

        if(quoteToUpdate.size() > tickersToUpdate.size()){
            throw new IllegalArgumentException("Tickers in quote list had some tickets not updated.");
        }
    }

    /**
     * Validate (against IEX) and save given tickers to quote table
     *
     * - get IexQuote(s)
     * - convert each IexQuote to Quote entity
     * - persist the quote to db
     *
     * @param tickers
     * @return list of converted quote entities
     * @throws IllegalArgumentException if ticker is not found from IEX
     */
    public List<Quote> saveQuotes(List<String> tickers) {
        List<Quote> quotes = new ArrayList<>();
        for (String x : tickers){
            quotes.add(saveQuote(x));
        }
        return quotes;
    }


    /**
     * Update a given quote to the quote table without validation
     *
     * @param quote entity to save
     * @return the saved quote entity
     */
    public Quote saveQuote(Quote quote) {
        //TODO
        return quoteDao.save(quote);
    }

    /**
     * Find all quotes from the quote table
     *
     * @return a list of quotes
     */
    public List<Quote> findAllQuotes() {
        return quoteDao.findAll();
    }

    /**
     * Helper method to map an IexQuote to a Quote entity
     * Note: 'iexQuote.getLatestPrice() == null' if the stock market is closed
     * Make sure to set a default value for number field(s)
     */
    protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {

        Quote quote = new Quote();

        if (iexQuote.getLatestPrice() == null){
            quote.setTicker(iexQuote.getSymbol());
            quote.setLastPrice(Double.MIN_VALUE); //double
            quote.setBidPrice(Double.MIN_VALUE); //double
            quote.setBidSize(Integer.MIN_VALUE); //integer
            quote.setAskPrice(Double.MIN_VALUE); //double
            quote.setAskSize(Integer.MIN_VALUE); //integer
        }
        else{
            quote.setTicker(iexQuote.getSymbol());
            quote.setLastPrice(iexQuote.getLatestPrice()); //double
            quote.setBidPrice(Double.valueOf(iexQuote.getIexBidPrice())); //double
            quote.setBidSize(iexQuote.getIexBidSize()); //integer
            quote.setAskPrice(Double.valueOf(iexQuote.getIexAskPrice())); //double
            quote.setAskSize(iexQuote.getIexAskSize()); //integer
        }
        return quote;
    }

    /**
     * Helper method to validate and save a single ticker
     * Not to be confused with saveQuote(Quote quote)
     */
    public Quote saveQuote(String ticker) {
        IexQuote iexQuote = findIexQuoteByTicker(ticker);
        return buildQuoteFromIexQuote(iexQuote);
    }

}