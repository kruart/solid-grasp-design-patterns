package app.factory;

import app.widgets.button.IOSButton;
import app.widgets.navigationbar.NavigationBar;
import app.widgets.button.Button;
import app.widgets.navigationbar.IOSNavigationBar;

public class IOSWidgetFactory implements WidgetFactory {
    @Override
    public Button createButton() {
        return new IOSButton();
    }

    @Override
    public NavigationBar createNavigationBar() {
        return new IOSNavigationBar();
    }
}
