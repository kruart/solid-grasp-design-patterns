# Chain of Responsibility
`Lets you pass requests along a chain of handlers. 
Upon receiving a request, each handler decides either to process the request 
or to pass it to the next handler in the chain`

Example:

We have a `Logger` functional interface and  `LogLevel's` enum.
 
We can create 3 different types of loggers: 
- logger that writes to console `consoleLogger` 
- logger that writes to file `fileLogger` 
- logger that sends emails `emailLogger`  

For each of these methods (Loggers) we define log levels when it will be executed 
and create a chain by calling `appendNext` method.
Then all this chain will be called at each `message` method call. 
If `this` handler doesn't have a necessary log level we do nothing 
and call the next handler (Logger) in the chain.

If 
```java
@FunctionalInterface
public interface Logger {
    enum LogLevel {
        INFO, DEBUG, WARNING, ERROR, FUNCTIONAL_MESSAGE, FUNCTIONAL_ERROR;

        public static LogLevel[] all() {
            return values();
        }
    }

    void message(String msg, LogLevel severity);

    default Logger appendNext(Logger nextLogger) {
        return (msg, severity) -> {
            message(msg, severity);
            nextLogger.message(msg, severity);
        };
    }

    static Logger writeLogger(LogLevel[] levels, Consumer<String> stringConsumer) {
        EnumSet<LogLevel> set = EnumSet.copyOf(Arrays.asList(levels));
        return (msg, severity) -> {
            if (set.contains(severity)) {
                stringConsumer.accept(msg);
            }
        };
    }

    static Logger consoleLogger(LogLevel... levels) {
        return writeLogger(levels, msg -> System.err.println("Writing to console: " + msg));
    }

    static Logger emailLogger(LogLevel... levels) {
        return writeLogger(levels, msg -> System.err.println("Sending via email: " + msg));
    }

    static Logger fileLogger(LogLevel... levels) {
        return writeLogger(levels, msg -> System.err.println("Writing to Log File: " + msg));
    }

    public static void main(String[] args) {
        // Build an immutable chain of responsibility
        Logger logger = consoleLogger(LogLevel.all())
                .appendNext(emailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR))
                .appendNext(fileLogger(LogLevel.WARNING, LogLevel.ERROR));

        // Handled by consoleLogger since the console has a LogLevel of all
        logger.message("Entering function ProcessOrder().", LogLevel.DEBUG);
        logger.message("Order record retrieved.", LogLevel.INFO);

        // Handled by consoleLogger and emailLogger since emailLogger implements Functional_Error & Functional_Message
        logger.message("Unable to Process Order ORD1 Dated D1 For Customer C1.", LogLevel.FUNCTIONAL_ERROR);
        logger.message("Order Dispatched.", LogLevel.FUNCTIONAL_MESSAGE);

        // Handled by consoleLogger and fileLogger since fileLogger implements Warning & Error
        logger.message("Customer Address details missing in Branch DataBase.", LogLevel.WARNING);
        logger.message("Customer Address details missing in Organization DataBase.", LogLevel.ERROR);
    }
}
```