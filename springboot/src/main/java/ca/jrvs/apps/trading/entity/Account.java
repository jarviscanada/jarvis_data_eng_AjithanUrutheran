package ca.jrvs.apps.trading.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")

public class Account {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="trader_id")

    private int traderId;
    @Column(name="amount")
    private double amount;

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

    public int getTraderId(){
        return this.traderId;
    }
    public void setTraderId(int traderId){
        this.traderId = traderId;
    }

    public double getAmount(){
        return this.amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
}
