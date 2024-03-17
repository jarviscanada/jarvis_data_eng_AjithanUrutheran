package ca.jrvs.apps.stockquote.dao;

import okhttp3.OkHttpClient;
public class QuoteHttpHelper {
    private String apiKey;
    private OkHttpClient client;

    /**
     * Fetch latest quote data from Alpha Vantage endpoint
     * @param symbol
     * @return Quote with latest data
     * @throws IllegalArgumentException - if no data was found for the given symbol
     */
    public Quote fetchQuoteInfo(String symbol) throws IllegalArgumentException {
        //TO DO
        return null;
    }


}
