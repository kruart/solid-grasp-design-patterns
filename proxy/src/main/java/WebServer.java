public interface WebServer {
    /**
     * Method responsible for making a call to the webserver with a specific endpoint
     **/
    void makeRequest(String url);
}