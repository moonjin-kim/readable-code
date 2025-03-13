package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.model.*;

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

            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> ioHandler.showPassOrderSummary(Order.of(selectedPass, lockerPass)),
                    () -> ioHandler.showPassOrderSummary(Order.of(selectedPass))
            );
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass() {
        StudyCafePassType studyCafePassType = ioHandler.askPassTypeSelecting();
        List<StudyCafePass> passCandidates = findPassCandidatesBy(studyCafePassType);

        return ioHandler.askPassSelecting(passCandidates);
    }


    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        StudyCafePasses allPass = studyCafeFileHandler.readStudyCafePasses();
        return allPass.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        if(cannotUseLocker(selectedPass)) {
            return Optional.empty();
        }
        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();

            boolean lockerSelection = ioHandler.askLockerPass(lockerPass);
            if (lockerSelection) {
                return Optional.of(lockerPass);
            }
        }
        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafePass pass) {
        StudyCafeLockerPasses allLockerPasses = studyCafeFileHandler.readLockerPasses();

        return allLockerPasses.findPassBy(pass);
    }

    private static boolean cannotUseLocker(StudyCafePass selectedPass) {
        return selectedPass.getPassType().isNotLockerType();
    }
}
