package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.model.Item;
import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafeFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(
            InputHandler inputHandler,
            OutputHandler outputHandler,
            StudyCafeFileHandler studyCafeFileHandler
    ) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;

    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            List<Item> items = new ArrayList<>();
            Item selectedPass = selectPass();
            items.add(selectedPass);
            Optional<Item> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresent(items::add);
            outputHandler.showPassOrderSummary(Order.of(items));
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private Item selectPass() {
        StudyCafePassType studyCafePassType = getPassTypeSelectingUserAction();

        List<Item> passCandidates = findPassCandidatesBy(studyCafePassType);

        return getPassBy(passCandidates);
    }

    private Item getPassBy(List<Item> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    private List<Item> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        List<Item> allPass = studyCafeFileHandler.readStudyCafePasses();
        return allPass.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }

    private StudyCafePassType getPassTypeSelectingUserAction() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    private Optional<Item> selectLockerPass(Item selectedPass) {
        if(isNotFixedAt(selectedPass)) {
            return Optional.empty();
        }
        Item lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate != null) {
            boolean lockerSelection = selectLockerSelectionBy(lockerPassCandidate);
            if (lockerSelection) {
                return Optional.of(lockerPassCandidate);
            }
        }
        return Optional.empty();
    }

    private boolean selectLockerSelectionBy(Item lockerPassCandidate) {
        outputHandler.askLockerPass(lockerPassCandidate);
        return inputHandler.getLockerSelection();
    }

    private Item findLockerPassCandidateBy(Item pass) {
        List<Item> allLockerPasses = studyCafeFileHandler.readLockerPasses();

        return allLockerPasses.stream()
            .filter(lockerPass -> lockerPass.getPassType() == pass.getPassType())
            .filter(lockerPass -> lockerPass.getDuration() == pass.getDuration())
            .findFirst()
            .orElse(null);
    }

    private static boolean isNotFixedAt(Item selectedPass) {
        return StudyCafePassType.FIXED != selectedPass.getPassType();
    }
}
