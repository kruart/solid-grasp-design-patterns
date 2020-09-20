import java.util.HashSet;
import java.util.Set;

public class ProxyWebServer implements WebServer {
    private final RealWebServer realServer;
    private final Set<String> blockedSites = new HashSet<>();

    public ProxyWebServer() { this.realServer = new RealWebServer(); }

    public void blockWebsite(String url) {
        System.out.println("Add website to black list: " + url);
        this.blockedSites.add(url);
    }

    @Override
    public void makeRequest(String url) {
        if(blockedSites.contains(url)) {
            System.out.println("This website is blocked. Remove website from black list or contact your administrator");
            return;
        }

        realServer.makeRequest(url);
    }
}