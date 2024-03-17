import java.util.HashMap;
import java.util.Map;

// Create ShoppingBag class here
public class ShoppingBag<T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag = new HashMap<>();

    public void addItem(T item) {
        this.shoppingBag.put(item, !this.shoppingBag.containsKey(item) ? 1 : this.shoppingBag.get(item) + 1);
    }

    public Integer getTotalPrice() {
        Integer grandTotal = 0;
        for(Map.Entry<T, Integer> entry : this.shoppingBag.entrySet()) {
            grandTotal += entry.getKey().getPrice() * entry.getValue();
        }
        return grandTotal;
    }
}