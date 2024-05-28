package ca.jrvs.apps.trading.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="position")
@Immutable
public class Position {
    @Id
    @Column(name="account_id")
    private int accountId;
    @Column(name="ticker")
    private String ticker;
    @Column(name="position")
    private long position;
    public int getAccount_id() {
        return accountId;
    }

    public String getTicker() {
        return ticker;
    }
    public long getPosition() {
        return position;
    }

}
