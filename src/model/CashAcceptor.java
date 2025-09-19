package model;

public class CashAcceptor implements PaymentMethod {

    private int balance;

    public CashAcceptor(int balance) {
        this.balance = balance;
    }

    @Override
    public int getAmount() {
        return balance;
    }

    @Override
    public void setAmount(int amount) {
        this.balance = amount;
    }

    @Override
    public void increaseBalance() {
     this.balance += balance;
    }

    @Override
    public boolean tryPay(int price) {
        if (balance >= price) {
            balance -= price;
            return true;
        }
        return false;
    }
}
