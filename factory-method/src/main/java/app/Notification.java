package app;

public interface Notification {
    void notifyUser(String message);

    // factory method can also be moved to a separate class(e.g. NotificationFactory)
    static Notification createInstance(String type) {
        if (type == null || type.isEmpty())
            throw new RuntimeException("Please choose the way how to send a message");

        return switch (type) {
            case "SMS" -> new SMSNotification();
            case "EMAIL" -> new EmailNotification();
            case "PUSH" -> new PushNotification();
            default -> throw new RuntimeException("Please choose the way how to send a message");
        };
    }
}
