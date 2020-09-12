package app;

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
