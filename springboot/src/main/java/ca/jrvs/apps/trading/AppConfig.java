package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.entity.Quote;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class AppConfig {
    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public MarketDataConfig marketDataConfig(){
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://api.iex.cloud/v1/data/core/quote/");
        marketDataConfig.setToken("sk_07ca1b99a65544718c1dcb9448d385be");
        return marketDataConfig;
    }

    @Bean
    public MarketDataDao marketDataDaoConfig(HttpClient httpClient, MarketDataConfig marketDataConfig){
        return new MarketDataDao(httpClient,marketDataConfig);
    }

    @Bean
    public HttpClient httpClientConfig(){
        return HttpClient.newHttpClient();
    }

    @Bean
    public QuoteService serviceConfig(){
        return new QuoteService(new MarketDataDao(httpClientConfig(),marketDataConfig()));
    }
}