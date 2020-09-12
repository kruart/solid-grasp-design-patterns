package app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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