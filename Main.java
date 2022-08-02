package budget;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) throws IOException {


        UserInput engine = new UserInput();
        Account main = new BudgetAccount();

        String menu = "Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort) \n" +
                "0) Exit";

        String subMenu = "Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back";

        String subMenuShowPurchases = "Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All \n" +
                "6) Back";

        String sortMenu = "How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back";

        String sortSubMenu = "Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other";

        while (!engine.isExit()) {
            System.out.println(menu);
            engine.setIntegerInput();
            System.out.println("");
            switch (engine.getIntegerInput()) {
                case 1:
                    System.out.println("Enter income");
                    engine.setFloatInput();
                    main.addIncome(engine.getFloatInput());
                    System.out.println("Income was added!");
                    System.out.println("");
                    break;
                case 2:
                    while(engine.getIntegerInput() != 5) {
                        System.out.print(subMenu);
                        System.out.println("");
                        engine.setIntegerInput();
                        switch (engine.getIntegerInput()) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                engine.setStrInput();
                                System.out.println("");
                                System.out.println("Enter purchase name:");
                                engine.setStrInput();
                                System.out.println("Enter its price:");
                                engine.setFloatInput();
                                main.addPurchase(engine.getStrInput(), engine.getFloatInput(), engine.getIntegerInput());
                                System.out.println("Purchase was added!");
                                System.out.println("");
                                break;
                            case 5:
                                break;
                        }
                    }
                    break;

                case 3:
                    while(engine.getIntegerInput() != 6) {
                        System.out.print(subMenuShowPurchases);
                        System.out.println("");
                        engine.setIntegerInput();
                        switch (engine.getIntegerInput()) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                List<String> items = main.getCategoryItems(engine.getIntegerInput());

                                if (Objects.equals(0, items.size())) {
                                    System.out.println("");
                                    System.out.println("The purchase list is empty!");
                                    System.out.println("");
                                    continue;
                                }
                                System.out.println("");
                                System.out.println(main.getCategoryName(engine.getIntegerInput()) + ":");
                                for (String item : items) {
                                    System.out.println(item);
                                }
                                System.out.println("Total sum: $" + df.format(main.getCategoryTotal(engine.getIntegerInput())));
                                System.out.println("");
                                continue;
                            case 5:
                                System.out.println("");
                                System.out.println("All:");
                                List<String> allPurchases = main.getPurchases();
                                for (String purchase : allPurchases) {
                                    System.out.println(purchase);
                                }
                                System.out.println("Total sum: $" + df.format(main.getPurchaseTotal()));
                                System.out.println("");
                                continue;
                            case 6:
                                break;
                        }
                        break;
                    }
                case 4:
                    System.out.println("Balance: $" + df.format(main.getBalance()));
                    System.out.println("");
                    break;

                case 5:
                    FileHandler<Account> fileHandler = new FileHandler<>(main);
                    fileHandler.output();
                    System.out.println("Purchases were saved!");
                    System.out.println("");
                    break;
                case 6:
                    FileHandler<Account> fileHandlerInput = new FileHandler<>(main);
                    fileHandlerInput.input();
                    main = fileHandlerInput.getObject();
                    System.out.println("Purchases were loaded!");
                    System.out.println("");
                    break;
                case 7:
                    while (engine.getIntegerInput() != 4) {
                      System.out.println(sortMenu);
                      engine.setIntegerInput();
                        System.out.println("");
                      switch (engine.getIntegerInput()) {
                          case 1:
                              if (main.getPurchases().size() == 0) {
                                  System.out.println("");
                                  System.out.println("The purchase list is empty!");
                                  System.out.println("");
                              } else {
                                  System.out.println("");
                                  System.out.println("All:");
                                  List<String> allPurchases = Sort.bubbleSort(main.getPurchases());
                                  for (String purchase : allPurchases) {
                                      System.out.println(purchase);
                                  }
                                  System.out.println("Total sum: $" + df.format(main.getPurchaseTotal()));
                              }
                              System.out.println("");
                              break;
                          case 2:

                              List<String> categories = new ArrayList<>();
                              System.out.println("");
                              System.out.println("Types:");
                              for(int i = 1; i < main.categorySize() + 1; i++) {
                                  categories.add(main.getCategoryName(i) + " - $" +
                                          df.format(main.getCategoryTotal(i)));
                              }

                              categories = Sort.bubbleSort(categories);

                              for (String category: categories) {
                                  System.out.println(category);

                              }

                              System.out.println("Total sum: $" + df.format(main.getPurchaseTotal()));
                              System.out.println("");
                              break;
                          case 3:
                                System.out.println("");
                              System.out.println(sortSubMenu);
                              engine.setIntegerInput();
                              List<String> output = main.getCategoryItems(engine.getIntegerInput());
                              if (output.size() == 0)
                              {
                                  System.out.println("");
                                  System.out.println("The purchase list is empty!");


                              } else {
                                  System.out.println("");
                                  System.out.println(main.getCategoryName(engine.getIntegerInput()) + ":");
                                  output = Sort.bubbleSort(output);
                                  for (String str : output) {
                                      System.out.println(str);
                                  }
                                  System.out.println("Total sum: $"
                                          + df.format(main.getCategoryTotal(engine.getIntegerInput())));
                                  System.out.println("");
                              }
                              break;

                      }
                    }
                break;
            }
        }

        System.out.print("Bye!");

    }
}