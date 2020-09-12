# Singleton
`Ensures that only one object of its kind exists
 and provides a single point of access to it for any other code.`
 
 Example:  
 
 We have `SyncTime` class that is `Singleton`, 
 it has private constructor (we must deny to create instances using constructors),
  and `getInstance` method that returns the same object on each call. 
  
 The purpose of `SyncTime` class is to get sync time from NTP (Network Time Protocol) server.
 So it would be right to implement it as Singleton, 
 since we don't want to create a new instance every time we want to get a synchronized time,
  because sometimes creating a new instance takes a long time. 
 
 `Examples, where do we need synchronized time? Online games, online stock trading, etc.`
 
  
 ```java
import org.apache.commons.net.ntp.NTPUDPClient;

import java.net.InetAddress;

public class SyncTime {
    private static SyncTime instance;
    private static final String SERVER_NAME = "pool.ntp.org";
    private final NTPUDPClient client;
    private InetAddress inetAddress;

    private SyncTime() {
        this.client = new NTPUDPClient();
    }

    public static SyncTime getInstance() {
        if (instance == null) {
            instance = new SyncTime();
        }
        return instance;
    }

    public long getSyncTime() {
        try {
            if (inetAddress == null) inetAddress = InetAddress.getByName(SERVER_NAME);

            return client.getTime(inetAddress).getReturnTime();
        } catch (Exception e) {
            System.out.println("Failed to fetch time from ntp server");
            return System.currentTimeMillis(); // if got error, return local computer time
        }
    }
}
```  

Let's test our `Singleton`:
```java
public class SyncTimeTest {

    @Test
    public void testGetInstance() {
        SyncTime syncTime1 = SyncTime.getInstance();
        SyncTime syncTime2 = SyncTime.getInstance();

        assertSame("References must be equal", syncTime1, syncTime2);
    }

    @Test
    public void testCheckIfYourLocalTimeIsSynchronized() {
        long syncTime = SyncTime.getInstance().getSyncTime();
        long localTime = System.currentTimeMillis();

        assertEquals(syncTime, localTime, 5); // offset 5 milliseconds
        System.out.println("Offset: " + (localTime - syncTime));
    }
}
```

Note: that our `Singleton` is not thread-safe. 
If you are going to use the `Singleton` pattern in your application, 
you need to ask yourself if it will be used in a multithreading environment.
If yes, use `thread synchronization` to prevent the creation of multiple objects.

 