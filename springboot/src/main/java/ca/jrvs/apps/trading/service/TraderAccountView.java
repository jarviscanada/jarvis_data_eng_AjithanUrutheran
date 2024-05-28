package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.entity.Account;
import ca.jrvs.apps.trading.entity.Trader;

public class TraderAccountView {


    Account account;
    Trader trader;

    public TraderAccountView(Account account, Trader trader){
        this.account = account;
        this.trader = trader;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }
}
