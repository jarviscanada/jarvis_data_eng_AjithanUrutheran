package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.entity.IexQuote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.*;

public class MarketDataDao {
    private HttpClient httpClient;
    private MarketDataConfig marketDataConfig;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(MarketDataDao.class);


    public MarketDataDao(HttpClient httpClient, MarketDataConfig marketDataConfig){
        this.httpClient = httpClient;
        this.marketDataConfig = marketDataConfig;
    }
    /**
     * Get an IexQuote
     *
     * @param ticker
     * @throws IllegalArgumentException if a given ticker is invalid
     * @throws DataRetrievalFailureException if HTTP request failed
     */
//    public Optional<IexQuote> findById(String ticker) {
//        logger.info("Finding Iex Quote by ticker...");
//        String url = marketDataConfig.getHost() + ticker + "?token=" + marketDataConfig.getToken();
//
//        try{
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            logger.info(response.body());
//            logger.info("Response status: "+response.statusCode());
//            if(response.statusCode() == 200){
//                IexQuote[] iexQuotes = objectMapper.readValue(response.body(), IexQuote[].class);
//
//                if (iexQuotes.length > 0){
//                    return Optional.of(iexQuotes[0]);
//                }
//                else{
//                    return Optional.empty();
//                }
//            }
//            else if (response.statusCode() == 404){
//                logger.info("Invalid ticker has been entered.");
//                throw new IllegalArgumentException("Invalid ticker: "+ticker);
//            }
//            else{
//                logger.error("HTTP Request Failed. Status code: "+response.statusCode());
//                return Optional.empty();
//            }
//        }
//        catch(Exception e){
//            throw new DataRetrievalFailureException("Http Request has failed.");
//        }
//    }
    public Optional<IexQuote> findById(String ticker) {
        logger.info("Finding Iex Quote by ticker...");
        String url = marketDataConfig.getHost() + ticker + "?token=" + marketDataConfig.getToken();

        try{
            Optional<String> responseBody = executeHttpGet(url);

            logger.info("Response body exists?: " + responseBody.isPresent());
            if (responseBody.isEmpty()){
                logger.error("No Response Body.");
                throw new IllegalArgumentException("Ticker is invalid.");
            }
            else if (responseBody.get().equals("[null]") || responseBody.isEmpty()){
                logger.error("Null Body from invalid ticker.");
                throw new IllegalArgumentException("Ticker is invalid.");
            }

            IexQuote[] iexQuote = objectMapper.readValue(responseBody.get(), IexQuote[].class);
            logger.info("Mapped response body to IexQuote object.");
            if(iexQuote.length > 1){
                logger.error("Multiple tickers were sent instead of single ticker for findById. Use findAllById");
                throw new IllegalArgumentException("Multiple tickers were sent.");
            }
            logger.info("SUCCESS: received object and mapped the " + iexQuote[0].getSymbol() + " ticker.");
            return Optional.of(iexQuote[0]);

        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Argument isn't allowed",e);
        }

        catch(DataRetrievalFailureException e){
            throw new DataRetrievalFailureException("Http Request has failed",e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get quotes from IEX
     * @param tickers is a list of tickers
     * @return a list of IexQuote objects
     * @throws IllegalArgumentException if a given ticker is invalid
     * @throws DataRetrievalFailureException if HTTP request failed
     */
//    public List<IexQuote> findAllById(Iterable<String> tickers) {
//        //TODO
//        StringBuilder sb = new StringBuilder(marketDataConfig.getHost());
//
//        for (String ticker : tickers){
//            sb.append(ticker).append(",");
//        }
//        sb.deleteCharAt(sb.length() - 1);  //Delete last comma
//
//        String url = sb.toString() + "?token=" + marketDataConfig.getToken();
//
//        try{
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
//            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            logger.info(response.body());
//            logger.info("Response status: "+response.statusCode());
//            if(response.statusCode() == 200){
//                List<IexQuote> iexQuotes = objectMapper.readValue(response.body(),new TypeReference<List<IexQuote>>(){});
//                logger.info("Received object list of iexQuotes");
//                return iexQuotes;
//            }
//            else if (response.statusCode() == 404){
//                logger.error("One of the tickers cannot be found, error.");
//                throw new IllegalArgumentException("Ticker cannot be found");
//            }
//            else{
//                logger.error("Http Request failed.");
//                throw new DataRetrievalFailureException("Http Request has failed");
//            }
//        }
//        catch(Exception e){
//            throw new DataRetrievalFailureException("Http Request has failed.");
//        }
//
//    }

    public List<IexQuote> findAllById(Iterable<String> tickers) {
        //TODO
        StringBuilder sb = new StringBuilder(marketDataConfig.getHost());
        int tickerSize = 0;
        for (String ticker : tickers){
            sb.append(ticker).append(",");
            tickerSize++;
        }
        sb.deleteCharAt(sb.length() - 1);  //Delete last comma

        String url = sb.toString() + "?token=" + marketDataConfig.getToken();

        try{
            Optional<String> responseBody = executeHttpGet(url);

            logger.info("Response body exists?: " + responseBody.isPresent());
            if (responseBody.isEmpty()){
                logger.error("No Response Body.");
                throw new IllegalArgumentException("Ticker is invalid.");
            }
            else if (responseBody.get().equals("[null]") || responseBody.isEmpty()){
                logger.error("Null Body from invalid ticker.");
                throw new IllegalArgumentException("Ticker is invalid.");
            }



            IexQuote[] iexQuote = objectMapper.readValue(responseBody.get(), IexQuote[].class);

            if(tickerSize != iexQuote.length){
                logger.error("Multiple tickers were passed, but we have less objects as output. Some tickers are incorrect.");
                throw new IllegalArgumentException("Error in 1+ ticker values. Re-check tickers.");
            }
            logger.info("Mapped response body to IexQuote object.");

            logger.info("SUCCESS: received object and mapped multiple objects.");
            return Arrays.asList(iexQuote);

        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Argument isn't allowed",e);
        }

        catch(DataRetrievalFailureException e){
            throw new DataRetrievalFailureException("Http Request has failed",e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Execute a GET request and return http entity/body as a string
     * Tip: use EntitiyUtils.toString to process HTTP entity
     *
     * @param url resource URL
     * @return http response body or Optional.empty for 404 response
     * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
     */
    private Optional<String> executeHttpGet(String url) {
        try{
            logger.info("Executing Http Get");
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                logger.info("Success, received response.");
                return Optional.of(response.body());
            }
            else if(response.statusCode() == 404){
                logger.error("Not successful. No response received. Check ticker.");
                return Optional.empty();
            }
            else{
                logger.error("Server error. Check API URL");
                throw new InterruptedException("Server error. Check API URL");
            }
        }
        catch(InterruptedException e){
            logger.error("Data Retrieval Failure Exception");
            throw new DataRetrievalFailureException(e.getMessage());
        }
        catch(IOException e){
            return Optional.empty();
        }
    }

    /**
     * Borrow a HTTP client from the HttpClientConnectionManager
     * @return a HttpClient
     */
    private HttpClient getHttpClient() {
        return httpClient;
    }

    public static void main(String[] args) {
        //List<String> tickers;
        //tickers = Arrays.asList("aapl","tsla");

        //MarketDataConfig mData = new MarketDataConfig();
        //mData.setHost("https://api.iex.cloud/v1/data/core/quote/");
        //mData.setToken("INSERTTOKEN");

        //MarketDataDao mDao = new MarketDataDao(HttpClient.newHttpClient(),mData);

        //Test for findById
        //IexQuote tester = mDao.findById("aapl").orElse(null);

        //Test for FindByListofID
        //List<IexQuote> tester = mDao.findAllById(tickers);
        //logger.info(tester.get(0).getSymbol());
        //logger.info(tester.get(1).getSymbol());

        //Test for executeHttpGet
        //String responseBody = mDao.executeHttpGet("https://api.iex.cloud/v1/data/core/quote/aapl,tssla?token=INSERTTOKEN").get();
        //logger.info(responseBody);
    }

}