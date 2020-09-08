# Factory Method
`Factory method is a creational design pattern which solves the problem of creating product objects without specifying their concrete classes`

When we need to create an instance of a class depends on some logic (User Configuration, a value from ComboBox, some config, etc.) 
to choose the right implementation we often use `if/else` or `switch` statements. 
If we use such constructions in many places it's getting hard to maintain such code.
In such a situation `Factory Method` could help us since we move creation logic to one place 
and reuse it in all classes where we need instance of this class.  

Example:   
We have `Notification` interface with static factory method `createInstance` and three implementations of this interface.

```java
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
```

Email Notification
```java
public class EmailNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending an email notification: " + message);
    }
}
```

Push Notification
```java
public class PushNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending a push notification: " + message);
    }
}
```

SMS Notification
```java
public class SMSNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Sending a SMS notification: " + message);
    }
}
```

Then inside `MessageService` class we use our factory method to create object and send a message:
```java
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
```
OUTPUT:
```
Sending an email notification: Hi! How are you?
Sending a push notification: Hi! How are you?
Sending a SMS notification: Hi! How are you?
Exception in thread "main" java.lang.RuntimeException: Please choose the way how to send a message
	at design_patterns.factory_method.Notification.createInstance(Notification.java:15)
	at design_patterns.factory_method.MessageService.sendMessage(MessageService.java:13)
	at design_patterns.factory_method.MessageService.main(MessageService.java:9)
``` 

We easily could reuse our factory method in many places without duplication code. 

#### Factory Method vs Contructor
- Constructors don't have meaningful names, so they are always restricted to the standard naming convention imposed by the language. 
- Static factory methods can have meaningful names, hence explicitly conveying what they do
- Static factory methods can return the same type that implements the method(s), a subtype, and also primitives, so they offer a more flexible range of returning types
- Static factory methods can encapsulate all the logic required for pre-constructing fully initialized instances, so they can be used for moving this additional logic out of constructors. This prevents constructors from performing further tasks, others than just initializing fields
- Static factory methods can be controlled-instanced methods, with the Singleton pattern being the most glaring example of this feature

Examples of `Factory Method` in JDK:
```java
String str1 = String.valueOf('abcdef');
String str2 = String.valueOf(12345);

Calendar.getInstance()

List<Integer> numbers = List.of(1, 2, 3);
Set<Person> persons = Collections.unmodifiableSet(originalPersonSet);
```