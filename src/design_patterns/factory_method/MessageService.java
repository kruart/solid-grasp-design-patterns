package design_patterns.factory_method;

public class MessageService {
    public static void main(String[] args) {
        MessageService service = new MessageService();
        service.sendMessage("Hi! How are you?", "EMAIL");
        service.sendMessage("Hi! How are you?", "PUSH");
        service.sendMessage("Hi! How are you?", "SMS");
        service.sendMessage("Hi! How are you?", "LETTER");
    }

    private void sendMessage(String message, String channel) {
        Notification notification = Notification.createInstance(channel);
        notification.notifyUser(message);
    }
}
