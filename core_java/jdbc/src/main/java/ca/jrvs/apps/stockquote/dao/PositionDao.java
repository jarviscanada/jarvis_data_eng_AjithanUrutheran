package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class PositionDao implements CrudDao<Position, String> {

    private Connection c;
    private static final String GET_ONE = "Select symbol, number_of_shares, value_paid FROM position WHERE symbol=?";
    private static final String GET_ALL = "SELECT symbol, number_of_shares, value_paid FROM position";
    private static final String DELETE_ONE = "DELETE FROM position WHERE symbol=?";
    private static final String DELETE_ALL = "DELETE FROM position";
    private static final String INSERT_ONE = "INSERT into position (symbol, number_of_shares, value_paid) VALUES (?,?,?)";
    private static final String UPDATE_ONE = "UPDATE position SET position number_of_shares = ?, value_paid = ? WHERE symbol = ?";
    public PositionDao(Connection c){
        this.c = c;
    }

    @Override
    public Position save(Position entity) throws IllegalArgumentException {
        if(entity == null){
            throw new IllegalArgumentException("Null position");
        }

        if(this.findById(entity.getTicker()).isEmpty()){
            try(PreparedStatement statement = this.c.prepareStatement(INSERT_ONE);){
                statement.setString(1,entity.getTicker());
                statement.setInt(2,entity.getNumOfShares());
                statement.setDouble(3,entity.getValuePaid());

                statement.executeUpdate();
                return entity;
            }
            catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("Failed to insert position");
            }
        }
        else{
            try(PreparedStatement statement = this.c.prepareStatement(UPDATE_ONE);){
                statement.setInt(1,entity.getNumOfShares());
                statement.setString(3,entity.getTicker());
                statement.setDouble(2,entity.getValuePaid());

                statement.executeUpdate();
                return entity;
            }
            catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException("Failed to update specified ID");
            }
        }

    }

    @Override
    public Optional<Position> findById(String s) throws IllegalArgumentException {

        if (s == null){
            throw new IllegalArgumentException("Null ID Detected");
        }
        try(PreparedStatement statement = this.c.prepareStatement(GET_ONE);){
            statement.setString(1,s);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return Optional.ofNullable(createPosition(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Iterable<Position> findAll() {
        ArrayList<Position> positionList = new ArrayList<>();

        try(PreparedStatement statement = this.c.prepareStatement(GET_ALL);){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                positionList.add(createPosition(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return positionList;
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
            e.printStackTrace();
            throw new RuntimeException("Failed to delete specified ID");
        }
    }

    @Override
    public void deleteAll() {
        try(PreparedStatement statement = this.c.prepareStatement(DELETE_ALL);){
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to delete all IDs");
        }
    }

    /**
     *
     * @param rs
     * @return position - Can be optional
     * @throws SQLException
     */
    private Position createPosition(ResultSet rs) throws SQLException{
        Position position = new Position();

        position.setTicker(rs.getString("symbol"));
        position.setValuePaid(rs.getDouble("value_paid"));
        position.setNumOfShares(rs.getInt("number_of_shares"));

        return position;
    }
}
