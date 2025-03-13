package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.StudyCafePasses;

public interface StudyCafeFileHandler {
    public StudyCafePasses readStudyCafePasses();

    public StudyCafeLockerPasses readLockerPasses() ;
}
