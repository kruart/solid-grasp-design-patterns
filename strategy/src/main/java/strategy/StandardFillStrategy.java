package strategy;

public class StandardFillStrategy implements FillStrategy {
    @Override
    public void fill() {
        System.out.println("Just refueling petrol!");
    }
}
