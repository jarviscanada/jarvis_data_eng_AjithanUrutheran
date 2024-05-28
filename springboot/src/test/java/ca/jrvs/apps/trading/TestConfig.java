package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.net.http.HttpClient;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = {"ca.jrvs.apps.trading.dao"})
public class TestConfig {

    @Bean
    public MarketDataConfig marketDataConfig() {
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://api.iex.cloud/v1/data/core/quote/");
        marketDataConfig.setToken("sk_7569f60aa8ea461f96328d32c55c30c2");
        return marketDataConfig;
    }

    @Bean
    public DataSource dataSource(){
        System.out.println("Creating apache Data Source...");
        String url = "jdbc:postgresql://localhost:5432/jrvstrading_test";
        String user = "postgres";
        String password = "password";
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    @Bean
    public HttpClient httpClientConfig(){
        return HttpClient.newHttpClient();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("ca.jrvs.apps.trading.entity"); // Package containing your JPA entities
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
