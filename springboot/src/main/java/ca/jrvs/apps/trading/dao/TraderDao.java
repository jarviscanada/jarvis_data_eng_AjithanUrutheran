package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraderDao extends JpaRepository<Trader, Integer> {
}
