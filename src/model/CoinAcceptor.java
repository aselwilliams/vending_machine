package model;

import java.util.Random;

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
        Random random = new Random();
        int randomAmount = 10 + random.nextInt(41);
        this.amount += randomAmount;
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
