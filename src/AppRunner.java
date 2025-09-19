import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private final PaymentMethod paymentMethod;

    private static boolean isExit = false;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        paymentMethod = selectPaymentMethod();
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    public PaymentMethod selectPaymentMethod() {
        Scanner sc = new Scanner(System.in);
        print("Выберите способ оплаты:");
        print("1 - Монеты");
        print("2 - Наличные");
        while (true) {
            String inputStr = sc.nextLine().trim();
            if (inputStr.equals("1")) {
                return new CoinAcceptor(100);
            } else if (inputStr.equals("2")) {
                return new CashAcceptor(0);
            } else {
                print("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Баланс на сумму: " + paymentMethod.getAmount());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (paymentMethod.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        showActions(products);
        String inputStr = fromConsole();

        if (inputStr.isEmpty()) {
            print("Пустой ввод. Попробуйте еще раз.");
            chooseAction(products);
            return;
        }

        String action = inputStr.substring(0, 1);
        if ("a".equalsIgnoreCase(action)) {
            paymentMethod.increaseBalance();
            print("Баланс пополнен.Новый баланс: " + paymentMethod.getAmount());
            startSimulation();
            return;
        }
        if ("h".equalsIgnoreCase(action)) {
            isExit = true;
            return;
        }
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    paymentMethod.setAmount(paymentMethod.getAmount() - products.get(i).getPrice());
                    print("Вы купили " + products.get(i).getName());
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попробуйте еще раз.");
            chooseAction(products);
        }
    }

    private void showActions(UniversalArray<Product> products) {
        if (getAllowedProducts().size() == 0) {
            print(" a - Пополнить баланс");
        }
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
        print(" h - Выйти");
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine().trim();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
