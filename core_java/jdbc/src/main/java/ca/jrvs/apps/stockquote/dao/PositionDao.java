package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PositionDao implements CrudDao<Position, String> {

    private Connection c;
    private static final String GET_ONE = "Select symbol, number_of_shares, value_paid FROM position WHERE symbol=?";
    public PositionDao(Connection c){
        this.c = c;
    }

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Optional<Position> findById(String s) throws IllegalArgumentException {
        Position position = new Position();
        try(PreparedStatement statement = this.c.prepareStatement(GET_ONE);){
            statement.setString(1,s);
            ResultSet rs = statement.executeQuery();
            
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<Position> findAll() {
        return null;
    }

    @Override
    public void deleteById(String s) throws IllegalArgumentException {

    }

    @Override
    public void deleteAll() {

    }
}
