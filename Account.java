package budget;

import java.util.List;

public interface Account {

    void addIncome(float income);

    void addPurchase(String purchaseName, float purchaseAmount, int category);

    List<String> getPurchases();

    float getBalance();

    float getPurchaseTotal();

    List<String> getCategoryItems(int key);


    String getCategoryName(int integerInput);

    float getCategoryTotal(int key);

    int categorySize();
}
