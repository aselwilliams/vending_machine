package model;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номинал купюры (10, 50, 100): ");
        try {
            int input = Integer.parseInt(scanner.nextLine().trim());
            if (input == 10 || input == 50 || input == 100) {
                balance += input;
                System.out.println("Баланс пополнен на " + input + " единиц.");
            } else {
                System.out.println("Неверный номинал купюры.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода. Введите число.");
        }
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
