package ca.jrvs.apps.stockquote;

import ca.jrvs.apps.stockquote.dao.DatabaseConnectionManager;
import ca.jrvs.apps.stockquote.dao.Position;
import ca.jrvs.apps.stockquote.dao.PositionDao;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.service.PositionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class PositionService_IntTest {

    @Mock
    private PositionDao mockPositionDao;

    @InjectMocks
    private PositionService pService;

    @Before
    public void setUp(){
//        try{
//            DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost","stock_quote","postgres","password");
//            Connection connection = dcm.getConnection();
//            QuoteDao quotedao = new QuoteDao(connection);
//            PositionDao positiondao = new PositionDao(connection);
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
        mockPositionDao = mock(PositionDao.class);
        pService = new PositionService(mockPositionDao);

    }

    @Test
    public void BuyTest(){
        Position expected = new Position();
        expected.setTicker("AAPL");
        expected.setValuePaid(272.18);
        expected.setNumOfShares(67);

        when(mockPositionDao.save(anyObject())).thenReturn(expected);

        Position actual = pService.buy("AAPL",67,272.18);
        assertEquals(expected.getValuePaid(),actual.getValuePaid(),0.02);
        assertEquals(expected.getTicker(),actual.getTicker());
        assertEquals(expected.getNumOfShares(), actual.getNumOfShares());
    }
    @Test
    public void SellTest(){
        String ticker = "AAPL";

        Position temp = new Position();
        temp.setTicker("AAPL");
        when(mockPositionDao.findById(ticker)).thenReturn(Optional.of(temp));

        pService.sell(ticker);

        verify(mockPositionDao,times(1)).findById(ticker);
        verify(mockPositionDao,times(1)).deleteById(ticker);

    }
    
}
