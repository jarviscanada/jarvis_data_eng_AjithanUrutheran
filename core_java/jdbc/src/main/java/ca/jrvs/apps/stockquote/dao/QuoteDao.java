package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteDao implements CrudDao<Quote, String> {

    private Connection c;
    private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);
    private static final String GET_ONE = "Select * FROM quote WHERE symbol=?";
    private static final String GET_ALL = "Select * from quote";
    private static final String DELETE_ONE = "DELETE FROM quote WHERE symbol=?";
    private static final String DELETE_ALL = "DELETE FROM quote";
    private static final String INSERT_ONE = "INSERT into quote (symbol, open, high, low, price, volume, latest_trading_day, previous_close, change, change_percent, timestamp) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ONE = "UPDATE quote SET open = ?, high = ?, low = ?, price = ?, volume = ?, latest_trading_day = ?, previous_close = ?, change = ?, change_percent = ?, timestamp = ? WHERE symbol = ?";
    public QuoteDao(Connection c){
        this.c = c;
    }
    @Override
    public Quote save(Quote entity) throws IllegalArgumentException {
        if(entity == null){
            logger.error("Null Quote");
        }

        if(this.findById(entity.getTicker()).isEmpty()){
            try(PreparedStatement statement = this.c.prepareStatement(INSERT_ONE);){
                statement.setString(1, entity.getTicker());
                statement.setDouble(2, entity.getOpen());
                statement.setDouble(3, entity.getHigh());
                statement.setDouble(4, entity.getLow());
                statement.setDouble(5, entity.getPrice());
                statement.setInt(6, entity.getVolume());
                statement.setDate(7, entity.getLatestTradingDay());
                statement.setDouble(8, entity.getPreviousClose());
                statement.setDouble(9, entity.getChange());
                statement.setString(10, entity.getChangePercent());
                statement.setTimestamp(11,entity.getTimestamp());


                statement.executeUpdate();
                return entity;
            }
            catch(SQLException e){
                //e.printStackTrace();
                logger.error("ERROR: Failed to insert quote",e);
            }
        }
        else{
            try(PreparedStatement statement = this.c.prepareStatement(UPDATE_ONE);){

                statement.setDouble(1, entity.getOpen());
                statement.setDouble(2, entity.getHigh());
                statement.setDouble(3, entity.getLow());
                statement.setDouble(4, entity.getPrice());
                statement.setInt(5, entity.getVolume());
                statement.setDate(6, entity.getLatestTradingDay());
                statement.setDouble(7, entity.getPreviousClose());
                statement.setDouble(8, entity.getChange());
                statement.setString(9, entity.getChangePercent());
                statement.setTimestamp(10,entity.getTimestamp());
                statement.setString(11, entity.getTicker());

                statement.executeUpdate();
                return entity;
            }
            catch(SQLException e){
                // e.printStackTrace();
                logger.error("Error: Failed to update specified ID",e);
            }
        }
        return entity;
    }

    @Override
    public Optional<Quote> findById(String s) throws IllegalArgumentException {
        if (s == null){
            throw new IllegalArgumentException("Null ID Detected");
        }
        try(PreparedStatement statement = this.c.prepareStatement(GET_ONE);){
            statement.setString(1,s);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return Optional.ofNullable(createQuote(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Quote> findAll() {
        ArrayList<Quote> quoteList = new ArrayList<>();

        try(PreparedStatement statement = this.c.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                quoteList.add(createQuote(rs));
            }
        }
        catch(SQLException e){
            logger.error("ERROR: Sql Error detected",e);
        }
        return quoteList;
    }

    @Override
    public void deleteById(String s) throws IllegalArgumentException {
        if(s == null){
            throw new IllegalArgumentException("Null ID Detected");
        }
        try(PreparedStatement statement = this.c.prepareStatement(DELETE_ONE);){
            statement.setString(1,s);
            statement.executeUpdate();
        }
        catch(SQLException e){
            logger.error("Failed to delete specified ID",e);
        }
    }


    @Override
    public void deleteAll() {
        try(PreparedStatement statement = this.c.prepareStatement(DELETE_ALL);){
            statement.executeUpdate();
        }
        catch(SQLException e){

            logger.error("ERROR: Failed to delete all IDs",e);
        }
    }

    private Quote createQuote(ResultSet rs) throws SQLException{
        Quote quote = new Quote();

        quote.setTicker(rs.getString("symbol"));
        quote.setOpen(rs.getDouble("open"));
        quote.setHigh(rs.getDouble("high"));
        quote.setLow(rs.getDouble("low"));
        quote.setPrice(rs.getDouble("price"));
        quote.setVolume(rs.getInt("volume"));
        quote.setLatestTradingDay(rs.getDate("latest_trading_day"));
        quote.setPreviousClose(rs.getDouble("previous_close"));
        quote.setChange(rs.getDouble("change"));
        quote.setChangePercent(rs.getString("change_percent"));
        quote.setTimestamp(rs.getTimestamp("timestamp"));

        return quote;
    }
}
