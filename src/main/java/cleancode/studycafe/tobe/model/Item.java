package cleancode.studycafe.tobe.model;

public interface Item {
    public StudyCafePassType getPassType();
    public int getDuration();
    public int getPrice();
    public int getDiscount();
    public String display();
}
