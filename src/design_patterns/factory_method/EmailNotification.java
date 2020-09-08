package design_patterns.factory_method;

public class EmailNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending an email notification: " + message);
    }
}
