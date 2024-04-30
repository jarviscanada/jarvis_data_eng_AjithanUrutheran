package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteDao extends JpaRepository<Quote, String> {
    Quote save(Quote quote);
    List<Quote> saveAll(List<Quote> quotes);
    List<Quote> findAll();
    Optional<Quote> findById(String ticker);
    boolean existsById(String ticker);
    void deleteById(String ticker);
    long count();
    void deleteAll();
}
