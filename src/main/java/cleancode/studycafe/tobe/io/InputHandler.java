package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Item;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {
    public StudyCafePassType getPassTypeSelectingUserAction();

    public Item getSelectPass(List<Item> passes);

    public boolean getLockerSelection();
}
