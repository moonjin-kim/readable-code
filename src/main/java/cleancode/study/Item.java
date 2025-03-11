package cleancode.study;

public class Item {
    private final String name;
    private final Integer price;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
