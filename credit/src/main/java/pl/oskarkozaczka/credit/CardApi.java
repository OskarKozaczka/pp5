package pl.oskarkozaczka.credit;

public class CardApi {
    CardDatabase cardDatabase;

    public CardApi(CardDatabase cardDatabase)
    {
        this.cardDatabase = new CardDatabase();
    }

    public void handleWithdraw(WithdrawRequest withdrawRequest) {
        CreditCard card = cardDatabase.loadByNumber(withdrawRequest.getNumber()).get();
        card.withdraw(withdrawRequest.getMoney());
        cardDatabase.save(card);
    }

}
