package ca.jrvs.apps.stockquote.dao;

import ca.jrvs.apps.stockquote.controller.StockQuoteController;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuoteHttpHelper {
    private String apiKey;
    private OkHttpClient client;
    private static final Logger logger = LoggerFactory.getLogger(StockQuoteController.class);
    /**
     * Fetch latest quote data from Alpha Vantage endpoint
     * @param symbol
     * @return Quote with latest data
     * @throws IllegalArgumentException - if no data was found for the given symbol
     */
    public QuoteHttpHelper(String apiKey){
        this.apiKey = apiKey;
    }
    public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://alpha-vantage.p.rapidapi.com/query?function=GLOBAL_QUOTE&symbol="+symbol+"&datatype=json"))
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.body());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            JsonNode quoteNode = rootNode.get("Global Quote");
            Quote quote = mapper.treeToValue(quoteNode, Quote.class);
            quote.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return quote;
            //System.out.println(quote.toString());
            //ignore property to not map w/ default value if not able to map

        } catch (InterruptedException e) {
            logger.error("ERROR: Interrupted Exception.",e);
        } catch (JsonMappingException e) {
            logger.error("ERROR: JSON mapping Exception.",e);
        } catch (JsonProcessingException e) {
            logger.error("ERROR: Json Processing Exception.",e);
        } catch (IOException e) {
            logger.error("ERROR: IO Exception.",e);

        }

        return null;
    }




}
