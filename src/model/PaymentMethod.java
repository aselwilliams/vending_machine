package model;

public interface PaymentMethod {

    int getAmount();
    void setAmount(int amount);
    void increaseBalance();
    boolean tryPay(int price);
}
