package ca.jrvs.apps.trading.repository;

import ca.jrvs.apps.trading.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, Integer> {

    Account getAccountByTraderId(Integer traderId);
}