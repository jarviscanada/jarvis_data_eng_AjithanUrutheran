package ca.jrvs.apps.stockquote.service;

import ca.jrvs.apps.stockquote.controller.StockQuoteController;
import ca.jrvs.apps.stockquote.dao.Position;
import ca.jrvs.apps.stockquote.dao.PositionDao;
import ca.jrvs.apps.stockquote.dao.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PositionService {
    private static final Logger logger = LoggerFactory.getLogger(StockQuoteController.class);

    private PositionDao dao;

    public PositionService(PositionDao dao){
        this.dao = dao;
    }

    /**
     * Processes a buy order and updates the database accordingly
     * @param ticker
     * @param numberOfShares
     * @param price
     * @return The position in our database after processing the buy
     */
    public Position buy(String ticker, int numberOfShares, double price) {
        Position userPosition = new Position();
        userPosition.setTicker(ticker);
        userPosition.setNumOfShares(numberOfShares);
        userPosition.setValuePaid(price);

        return dao.save(userPosition);
    }

    /**
     * Sells all shares of the given ticker symbol
     * @param ticker
     */
    public void sell(String ticker) {
        Optional<Position> ticketPosition = dao.findById(ticker);

        if (ticketPosition.isPresent()){
            dao.deleteById(ticketPosition.get().getTicker());
        }
        else{
            logger.error("Cannot sell ticket due to incorrect ticker/non-existent position");
        }
    }

}
