package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;

public interface PassReader {
    public StudyCafeSeatPasses readStudyCafePasses();

    public StudyCafeLockerPasses readLockerPasses() ;
}
