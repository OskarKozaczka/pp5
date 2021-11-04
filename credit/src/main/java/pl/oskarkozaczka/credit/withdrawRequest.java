package pl.oskarkozaczka.credit;

import java.math.BigDecimal;

class WithdrawRequest {
    private final String number;
    private final BigDecimal money;

    public WithdrawRequest(String card1, BigDecimal money) {
        this.number = card1;
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getMoney() {
        return money;
    }
}
