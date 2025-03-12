package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Item;
import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;

import java.util.List;

public interface OutputHandler {
    public void showWelcomeMessage();

    public void showAnnouncement();

    public void askPassTypeSelection();

    public void showPassListForSelection(List<Item> passes);

    public void askLockerPass(Item lockerPass);

    public void showPassOrderSummary(Order order);

    public void showSimpleMessage(String message);
}
