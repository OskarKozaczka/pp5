package pl.oskarkozaczka.credit;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    public CreditCard thereIsCreditCard() {
        return new CreditCard("1234-5678");
    }
    @Test
    public void itAllowesAsignLimitToCC(){
        CreditCard card= thereIsCreditCard();
        card.assignLimit(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(200),card.getCurrentLimit());
    }
    @Test
    public void itIsNotPossibleToAssignLimitBelowThreshhold(){
        CreditCard card= thereIsCreditCard();
        try {card.assignLimit(BigDecimal.valueOf(50));}
        catch (CreditLimitBelowMinumumValueException e){ assertTrue(true);}
    }

    @Test
    public void itAllowsToCheckCurrentBalance(){
        CreditCard card= thereIsCreditCard();
        card.assignLimit(BigDecimal.valueOf(1000));
        card.withdraw(BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(500),card.getBalance());
    }
    @Test
    public void cantWithdrawBelowBalance(){
        CreditCard card= thereIsCreditCard();
        card.assignLimit(BigDecimal.valueOf(1000));
        card.withdraw(BigDecimal.valueOf(500));
        card.withdraw(BigDecimal.valueOf(500));
        assertThrows(NoEnoughMoneyException.class ,() -> {
            card.withdraw(BigDecimal.valueOf(500));
        });
    }
}
