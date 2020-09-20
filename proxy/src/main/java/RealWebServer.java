public class RealWebServer implements WebServer {
    @Override
    public void makeRequest(String url) {
        System.out.println("Making request to " + url);
    }
}