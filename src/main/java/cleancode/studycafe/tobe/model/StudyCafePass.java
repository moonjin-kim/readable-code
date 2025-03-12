package cleancode.studycafe.tobe.model;

public class StudyCafePass implements Item{
    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }
    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }
    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getDiscount() {
        return (int) (price * discountRate);
    }

    @Override
    public String display() {
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("이용권: %s시간권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("이용권: %s주권 - %d원", duration, price);
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("이용권: %s주권 - %d원", duration, price);
        }
        return "";
    }
}
