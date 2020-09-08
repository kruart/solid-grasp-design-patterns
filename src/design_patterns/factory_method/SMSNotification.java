package design_patterns.factory_method;

public class SMSNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending a SMS notification: " + message);
    }
}
