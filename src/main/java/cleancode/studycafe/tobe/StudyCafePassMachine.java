package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.model.Item;
import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeFileHandler studyCafeFileHandler;
    private final ConsoleStudyCafeIOHandler ioHandler = new ConsoleStudyCafeIOHandler();

    public StudyCafePassMachine(
            StudyCafeFileHandler studyCafeFileHandler
    ) {
        this.studyCafeFileHandler = studyCafeFileHandler;

    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            List<Item> items = new ArrayList<>();
            Item selectedPass = selectPass();
            items.add(selectedPass);
            Optional<Item> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresent(items::add);
            ioHandler.showPassOrderSummary(Order.of(items));
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private Item selectPass() {
        StudyCafePassType studyCafePassType = ioHandler.askPassTypeSelecting();
        List<Item> passCandidates = findPassCandidatesBy(studyCafePassType);

        return ioHandler.askPassSelecting(passCandidates);
    }


    private List<Item> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        List<Item> allPass = studyCafeFileHandler.readStudyCafePasses();
        return allPass.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }

    private Optional<Item> selectLockerPass(Item selectedPass) {
        if(cannotUseLocker(selectedPass)) {
            return Optional.empty();
        }
        Item lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate != null) {
            boolean lockerSelection = ioHandler.askLockerPass(lockerPassCandidate);;
            if (lockerSelection) {
                return Optional.of(lockerPassCandidate);
            }
        }
        return Optional.empty();
    }

    private Item findLockerPassCandidateBy(Item pass) {
        List<Item> allLockerPasses = studyCafeFileHandler.readLockerPasses();

        return allLockerPasses.stream()
            .filter(lockerPass -> lockerPass.getPassType() == pass.getPassType())
            .filter(lockerPass -> lockerPass.getDuration() == pass.getDuration())
            .findFirst()
            .orElse(null);
    }

    private static boolean cannotUseLocker(Item selectedPass) {
        return selectedPass.getPassType().isNotLockerType();
    }
}
