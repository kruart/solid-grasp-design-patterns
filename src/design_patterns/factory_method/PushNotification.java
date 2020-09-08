package design_patterns.factory_method;

public class PushNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending a push notification: " + message);
    }
}
