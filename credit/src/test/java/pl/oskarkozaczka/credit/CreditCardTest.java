package pl.oskarkozaczka.credit;

import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {
    @Test
    public void itAllowsAssignLimitToCC(){
        CreditCard card = thereIsCreditCard();
        card.assignLimit(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(200), card.getCurrentLimit());
    }

    private CreditCard thereIsCreditCard() {
        return new CreditCard("1234-5678");
    }

    @Test
    public void itIsNotPossibleToAssignLimitBelowTreshold(){
        CreditCard card = thereIsCreditCard();
        try {
            card.assignLimit(BigDecimal.valueOf(50));
        } catch (CreditLimitBelowMinimumValueException e){
            assertTrue(true);
        }
    }

    @Test
    public void itAllowsToCheckCurrentBalance(){
        CreditCard card = thereIsCreditCard();
        card.assignLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(500));

        assertEquals(BigDecimal.valueOf(500), card.getBalance());
        }


    @Test
    public void cantWithdrawBelowBalance(){
        CreditCard card = thereIsCreditCard();
        card.assignLimit(BigDecimal.valueOf(1000));

        card.withdraw(BigDecimal.valueOf(500));
        card.withdraw(BigDecimal.valueOf(500));

        assertThrows(NotEnoughMoneyException.class, () -> {
            card.withdraw(BigDecimal.valueOf(500));
        });
    }
}
