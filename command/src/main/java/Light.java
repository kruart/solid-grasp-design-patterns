public class Light {
    private boolean on;

    public void switchOn() {
        System.out.println("Turning on the light");
        on = true;
    }

    public void switchOff() {
        System.out.println("Turning off the light");
        on = false;
    }
}