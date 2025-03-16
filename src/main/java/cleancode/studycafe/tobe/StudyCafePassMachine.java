package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.model.*;
import cleancode.studycafe.tobe.model.pass.*;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final ConsoleStudyCafeIOHandler ioHandler = new ConsoleStudyCafeIOHandler();
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(
            SeatPassProvider seatPassProvider,
            LockerPassProvider lockerPassProvider
    ) {
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;

    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            StudyCafeSeatPass selectedPass = selectPass();
            studyCafePasses.add(selectedPass);
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresent(studyCafePasses::add);
            ioHandler.showPassOrderSummary(Order.of(studyCafePasses));
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectPass() {
        StudyCafePassType studyCafePassType = ioHandler.askPassTypeSelecting();
        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(studyCafePassType);

        return ioHandler.askPassSelecting(passCandidates);
    }


    private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        StudyCafeSeatPasses allPass = seatPassProvider.getSeatPasses();
        return allPass.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
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

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getStudyCafeLockerPasses();

        return allLockerPasses.findPassBy(pass);
    }

    private static boolean cannotUseLocker(StudyCafeSeatPass selectedPass) {
        return selectedPass.getPassType().isNotLockerType();
    }
}
