package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class ConsoleStudyCafeIOHandler{
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();

    public StudyCafePassType getPassTypeSelectingUserAction() {
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
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

    public void showPassListForSelection(List<StudyCafePass> passes) {
        outputHandler.showPassListForSelection(passes);
    }

    public boolean askLockerPass(StudyCafeLockerPass lockerPass) {
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

    public StudyCafePass askPassSelecting(List<StudyCafePass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }
}
