package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionDao extends JpaRepository<Position, Integer> {
    long countByAccountId(Integer accountId);
}
