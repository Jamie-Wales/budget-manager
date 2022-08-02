package budget;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetAccount implements Account, Serializable {
    private final List<String> purchases = new ArrayList<>();
    private float balance = 0;
    private float purchaseTotal = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private final Map<Integer, Purchases> categories = new HashMap<>();

    BudgetAccount() {
        categories.put(1, new Purchases("Food"));
        categories.put(2,  new Purchases("Clothes"));
        categories.put(3, new Purchases("Entertainment"));
        categories.put(4, new Purchases("Other"));
    }



    public void addIncome(float income) {
        balance += income;
    }


    public void addPurchase(String purchaseName, float purchaseAmount, int category) {
        if (category < 5) {
            Purchases currentCategory = categories.get(category);
            currentCategory.items.add(purchaseName + " $" + df.format((double) purchaseAmount));
            purchases.add(purchaseName + " $" + df.format((double) purchaseAmount));
            currentCategory.addToTotal(purchaseAmount);
            balance -= purchaseAmount;
            purchaseTotal += purchaseAmount;
        }
    }



    public List<String> getPurchases() {
        return purchases;
    }


    public float getBalance() {
        return balance;
    }

    public float getPurchaseTotal() {
        return purchaseTotal;
    }


    public List<String> getCategoryItems(int key) {
        return categories.get(key).getItems();
    }


    public String getCategoryName(int key) {
        return categories.get(key).getName();
    }


    public float getCategoryTotal(int key) {
        return categories.get(key).getTotal();
    }

    public int categorySize() {
        return categories.size();
    }



    private class Purchases implements Serializable {
        private final String name;

        private float total;
        public List<String> items = new ArrayList<>();

        Purchases(String name) {
            this.name = name;
            this.total = 0;
        }


        public String getName() {
            return name;
        }

        public List<String> getItems() {
            return items;
        }

        public void addToTotal(float number) {
            total += number;
        }

        public float getTotal() {
            return total;
        }
    }

}
