package ca.jrvs.apps.trading.dao;


import ca.jrvs.apps.trading.entity.SecurityOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityOrderDao extends JpaRepository<SecurityOrder,Integer> {
    long countByAccountId(Integer accountId);

    void deleteAllByAccountId(Integer accountId);
}
