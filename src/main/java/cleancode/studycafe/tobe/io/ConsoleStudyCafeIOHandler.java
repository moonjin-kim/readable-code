package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Item;
import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class ConsoleStudyCafeIOHandler{
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();

    public StudyCafePassType getPassTypeSelectingUserAction() {
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public Item getSelectPass(List<Item> passes) {
        return inputHandler.getSelectPass(passes);
    }

    public boolean getLockerSelection() {
        return inputHandler.getLockerSelection();
    }

    public void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
    }

    public void showAnnouncement() {
        outputHandler.showAnnouncement();
    }

    public void askPassTypeSelection() {
        outputHandler.askPassTypeSelection();
    }

    public void showPassListForSelection(List<Item> passes) {
        outputHandler.showPassListForSelection(passes);
    }

    public boolean askLockerPass(Item lockerPass) {
        outputHandler.askLockerPass(lockerPass);
        return inputHandler.getLockerSelection();
    }

    public void showPassOrderSummary(Order order) {
        outputHandler.showPassOrderSummary(order);
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }

    public StudyCafePassType askPassTypeSelecting() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public Item askPassSelecting(List<Item> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }
}
