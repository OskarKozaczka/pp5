package pl.oskarkozaczka.credit;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlaTest {

    private  CardDatabase cardDatabase;

    @Test
    public void itHandleMultipleCardWithdraws() {
        //A
        String card1Number = thereIsCardWithLimit(1000);
        String card2Number = thereIsCardWithLimit(2000);

        CardApi cardApi = thereIsCardApi();
        //Act
        cardApi.handleWithdraw(new WithdrawRequest(card1Number, BigDecimal.valueOf(500)));
        cardApi.handleWithdraw(new WithdrawRequest(card2Number, BigDecimal.valueOf(1000)));        //A

        cardBalanceEquals(card1Number, BigDecimal.valueOf(500));
        cardBalanceEquals(card2Number, BigDecimal.valueOf(1000));
    }

    private void cardBalanceEquals(String number, BigDecimal expectedBalance) {
        CreditCard  loadedCard = cardDatabase.loadByNumber(number)
                .orElseGet(() -> {
                    CreditCard defaultCard = new CreditCard("not-exists");
                    return defaultCard;
        });
        assertEquals(expectedBalance, loadedCard.getBalance());
    }

    private String thereIsCardWithLimit(int i) {
        CardDatabase cardDatabase = thereIsCreditCardDatabase();
        CreditCard creditCard = new CreditCard(UUID.randomUUID().toString());
        cardDatabase.save(creditCard);
        return creditCard.getNumber();
    }

    private CardDatabase thereIsCreditCardDatabase() {
        this.cardDatabase = new CardDatabase();
        return this.cardDatabase;
    }


    private CardApi thereIsCardApi() {
        return new CardApi(cardDatabase);
    }


}