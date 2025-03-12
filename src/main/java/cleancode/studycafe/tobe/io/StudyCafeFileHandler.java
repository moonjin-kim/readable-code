package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Item;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;

import java.util.List;

public interface StudyCafeFileHandler {
    public List<Item> readStudyCafePasses();

    public List<Item> readLockerPasses() ;
}
