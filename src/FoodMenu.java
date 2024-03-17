import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

// Create FoodMenu class here
public class FoodMenu {
    private List<Food> menu = new ArrayList<>();
    public FoodMenu() {
        menu.add(new Food("Taco", "Yummy", 50));
        menu.add(new Food("Pizza", "Wow", 40));
        menu.add(new Food("Pho", "Oh Yeah!", 30));
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        int countItem = 1;
        for(Food item : this.menu) {
            temp.append(String.format("%d. %s%n", countItem++, item.toString()));
        }
        return temp.toString();
    }

    public Food getFood(int index) {
        if(index > this.menu.size()) {
            return null;
        }
        return this.menu.get(index - 1);
    }

    public Food getLowestCostFood() {
        Food minFood = this.getFood(1);
        int min = minFood.getPrice();
        for(int i = 2; i <= this.menu.size(); i++) {
            Food item = this.getFood(i);
            int price = item.getPrice();
            if(price < min) {
                min = price;
                minFood = item;
            }
        }
        return minFood;
    }
}