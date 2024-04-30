package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.entity.IexQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuoteService {

    /**
     * Find an IexQuote
     * @param ticker
     * @return IexQuote object
     * @throws IllegalArgumentException if ticker is invalid
     */
    private MarketDataDao marketDataDao;

    public QuoteService(MarketDataDao marketDataDao){
        this.marketDataDao = marketDataDao;
    }
    public IexQuote findIexQuoteByTicker(String ticker) {
        //TODO
        Optional<IexQuote> iexQuote = marketDataDao.findById(ticker);
        return iexQuote.get();
    }

}