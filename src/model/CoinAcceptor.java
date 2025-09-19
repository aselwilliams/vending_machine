package model;

public class CoinAcceptor implements PaymentMethod {
    private int amount;

    public CoinAcceptor(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void increaseBalance() {
        this.amount += amount;
    }

    @Override
    public boolean tryPay(int price) {
        if (amount >= price) {
            amount -= price;
            return true;
        }
        return false;
    }
}
