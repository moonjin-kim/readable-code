package cleancode.studycafe.tobe.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private StudyCafePass studyCafePass;
    private StudyCafeLockerPass studyCafeLockerPass;

    public Order(StudyCafePass items, StudyCafeLockerPass lockerPasses) {
        this.studyCafePass = items;
        this.studyCafeLockerPass = lockerPasses;
    }

    public static Order of(StudyCafePass item) {
        return new Order(item, null);
    }

    public static Order of(StudyCafePass item, StudyCafeLockerPass lockerPasses) {
        return new Order(item, lockerPasses);
    }

    public List<String> displayItems() {
        List<String> displayedItems = new ArrayList<>();
        displayedItems.add(this.studyCafePass.display());
        if (this.studyCafeLockerPass != null) {
            displayedItems.add(this.studyCafeLockerPass.display());
        }
        return displayedItems;
    }

    public int calculateTotalDiscount() {
        int totalDiscount = 0;
        totalDiscount += studyCafePass.getDiscount();
        if (this.studyCafeLockerPass != null) {
            totalDiscount += studyCafeLockerPass.getDiscount();
        }
        return totalDiscount;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        totalPrice += studyCafePass.getPrice();
        if (this.studyCafeLockerPass != null) {
            totalPrice += studyCafeLockerPass.getPrice();
        }
        return totalPrice;
    }

    public int calculateTotalAmount() {
        return calculateTotalPrice() - calculateTotalDiscount();
    }

}
