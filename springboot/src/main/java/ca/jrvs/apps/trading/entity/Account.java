package ca.jrvs.apps.trading.entity;

public class Account {
    private int id;
    private int traderId;
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
