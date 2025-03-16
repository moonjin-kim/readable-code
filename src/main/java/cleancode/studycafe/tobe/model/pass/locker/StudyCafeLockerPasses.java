package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> lockerPasses;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
        this.lockerPasses = passes;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
        return new StudyCafeLockerPasses(passes);
    }

    public List<StudyCafeLockerPass> getLockerPasses() {
        return lockerPasses;
    }

    public Optional<StudyCafeLockerPass> findPassBy(StudyCafeSeatPass pass) {
        return lockerPasses.stream()
                .filter(lockerPass -> lockerPass.getPassType() == pass.getPassType())
                .filter(lockerPass -> lockerPass.getDuration() == pass.getDuration())
                .findFirst();
    }
}
