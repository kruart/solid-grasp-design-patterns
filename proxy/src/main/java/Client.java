public class Client {
    private final WebServer server = new ProxyWebServer();

    public static void main(String[] args) {
        //code in main method
        Client client = new Client();
        client.makeRequest("www.facebook.com");
        client.addSiteToBlackList("www.facebook.com");
        client.makeRequest("www.facebook.com");
        // Prints 'This website is blocked. Contact your administrator'
    }

    void makeRequest(String url) {
        server.makeRequest(url);
    }

    void addSiteToBlackList(String url) {
        if (server instanceof ProxyWebServer proxy) {   // jdk 15 experimental features must be turned on to work
            proxy.blockWebsite(url);
            return;
        }

        throw new UnsupportedOperationException("The current version of the web server does not support blocking sites");
    }
}
