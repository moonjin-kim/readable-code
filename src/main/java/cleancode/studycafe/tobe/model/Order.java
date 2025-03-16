package cleancode.studycafe.tobe.model;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.StudyCafePass;

import java.util.List;
import java.util.Optional;

public class Order {
    private List<StudyCafePass> studyCafePasses;
    private StudyCafeLockerPass studyCafeLockerPass;

    public Order(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static Order of(List<StudyCafePass> studyCafePasses) {
        return new Order(studyCafePasses);
    }

    public int getDiscount() {
        return studyCafePasses.stream().mapToInt(StudyCafePass::getDiscountPrice).sum();
    }

    public int calculateTotalPrice() {
        return studyCafePasses.stream().mapToInt(StudyCafePass::getPrice).sum();
    }

    public int calculateTotalAmount() {
        return calculateTotalPrice() - getDiscount();
    }

    public List<StudyCafePass> getStudyCafePasses() {
        return studyCafePasses;
    }

    public Optional<StudyCafeLockerPass> getStudyCafeLockerPass() {
        return Optional.ofNullable(studyCafeLockerPass);
    }

}
