package cleancode.studycafe.tobe.model;

import java.util.List;

public class Order {
    private List<Item> items;

    public Order(List<Item> items) {
        this.items = items;
    }

    public static Order of(List<Item> item) {
        return new Order(item);
    }

    public List<String> displayItems() {
        return this.items.stream().map(
                Item::display
        ).toList();
    }

    public int calculateTotalDiscount() {
        return this.items.stream().mapToInt(
                item -> item.getDiscount()
        ).sum();
    }

    public int calculateTotalPrice() {
        return this.items.stream().mapToInt(
                item -> item.getPrice()
        ).sum();
    }

    public int calculateTotalAmount() {
        return calculateTotalPrice() - calculateTotalDiscount();
    }

}
