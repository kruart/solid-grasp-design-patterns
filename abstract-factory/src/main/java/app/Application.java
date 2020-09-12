package app;

import app.factory.AndroidWidgetFactory;
import app.factory.IOSWidgetFactory;
import app.factory.WidgetFactory;
import app.widgets.button.Button;
import app.widgets.navigationbar.NavigationBar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application {
    private Button button;
    private NavigationBar navigationBar;

    public Application() {
        configureApplication();
    }

    private void configureApplication() {
        String platform = readPlatformType();

        WidgetFactory factory = switch (platform) {
            case "android" -> new AndroidWidgetFactory();
            case "ios" -> new IOSWidgetFactory();
            default -> throw new RuntimeException("Unsupported platform");
        };

        button = factory.createButton();
        navigationBar = factory.createNavigationBar();
    }

    public void paint() {
        button.paint();
        navigationBar.paint();
    }

    private String readPlatformType() {
        // simulating reading platform meta info
        Properties properties = new Properties();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Unable to read platform meta info");
        }

        return properties.getProperty("platform");
    }
}
