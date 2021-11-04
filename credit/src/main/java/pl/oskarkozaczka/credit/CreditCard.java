package pl.oskarkozaczka.credit;

import java.math.BigDecimal;

public class CreditCard {
    public static final int CREDIT_LIMIT = 100;
    private final String cardNumber;
    private BigDecimal limit;
    private BigDecimal balance;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void assignLimit(BigDecimal limit) {
        if (isCreditLimitHighEnough(limit)){
            throw new CreditLimitBelowMinimumValueException();
        }
        this.limit = limit;
        this.balance = limit;
    }

    private boolean isCreditLimitHighEnough(BigDecimal limit) {
        return limit.compareTo(BigDecimal.valueOf(CREDIT_LIMIT)) < 0;
    }

    public BigDecimal getCurrentLimit() {
        return limit;
    }

    public void withdraw(BigDecimal money) {
        if (balance.compareTo(money) < 0){
            throw new NotEnoughMoneyException();
        }
        this.balance = balance.subtract(money);
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public String getNumber() {
        return null;
    }
}
