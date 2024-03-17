// Create PricedItem interface here
public interface PricedItem <T extends Number> {
    public T getPrice();
    public void setPrice(T price);
}