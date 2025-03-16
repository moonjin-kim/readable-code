package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

import java.util.List;

public class ConsoleStudyCafeIOHandler{
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();
    private final ConsoleOutputHandler outputHandler = new ConsoleOutputHandler();

    public StudyCafePassType getPassTypeSelectingUserAction() {
        return inputHandler.getPassTypeSelectingUserAction();
    }

    public StudyCafeSeatPass getSelectPass(List<StudyCafeSeatPass> passes) {
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

    public void showPassListForSelection(List<StudyCafeSeatPass> passes) {
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

    public StudyCafeSeatPass askPassSelecting(List<StudyCafeSeatPass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }
}
