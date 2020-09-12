package app.widgets.navigationbar;

public class IOSNavigationBar implements NavigationBar {
    @Override
    public void paint() {
        System.out.println("Drawing iOS Navigation Bar");
    }
}
