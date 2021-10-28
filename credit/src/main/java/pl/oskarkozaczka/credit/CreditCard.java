package pl.oskarkozaczka.credit;

import java.math.BigDecimal;

public class CreditCard{
    public static final int CREDIT_LIMIT = 100;
    private final String cardNumber;
    private BigDecimal limit;
    private BigDecimal balance;

public CreditCard(String cardNumber){
        this.cardNumber=cardNumber;
        }

    public void assignLimit(BigDecimal l){
            this.limit = l;
            this.balance = l;
        }

    public boolean isBelowCreditLimit(BigDecimal limit){
        return limit.compareTo(BigDecimal.valueOf(CREDIT_LIMIT)) <0;
    }

    public BigDecimal getCurrentLimit(){
            return limit;
        }
    public BigDecimal getBalance(){
        return balance;
    }

    public void withdraw(BigDecimal money) {
        if (balance.compareTo(money)<0){
            throw new NoEnoughMoneyException();
        }
        this.balance = balance.subtract(money);
    }
}