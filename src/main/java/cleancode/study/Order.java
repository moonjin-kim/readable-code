package cleancode.study;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Order {
    private final Logger log = Logger.getLogger(getClass().getName());
    private final ArrayList<Item> items;
    private final String user;

    public Order(ArrayList<Item> items, String user) {
        this.items = items;
        this.user = user;
    }

    public boolean validateOrder() {
        if (doesNotHaveItem()) {
            log.info("주문 항목이 없습니다.");
            return false;
        }
        if (!hasTotalPrice()) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }
        if (hasNotUser()) {
            log.info("사용자 정보가 없습니다.");
            return false;
        }

        return true;
    }

    private boolean hasNotUser() {
        return user.isEmpty();
    }

    private boolean hasTotalPrice() {
        return calculateTotalPrice() > 0;
    }

    private Integer calculateTotalPrice() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    private boolean doesNotHaveItem() {
        return items.isEmpty();
    }
}
