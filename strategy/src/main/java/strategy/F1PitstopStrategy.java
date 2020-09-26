package strategy;

public class F1PitstopStrategy implements FillStrategy {

    @Override
    public void fill() {
        System.out.println("Refuel only after all other pit stop procedures!");
    }
}
