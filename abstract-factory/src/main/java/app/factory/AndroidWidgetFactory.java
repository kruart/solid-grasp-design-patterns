package app.factory;

import app.widgets.button.AndroidButton;
import app.widgets.navigationbar.NavigationBar;
import app.widgets.button.Button;
import app.widgets.navigationbar.AndroidNavigationBar;

public class AndroidWidgetFactory implements WidgetFactory {
    @Override
    public Button createButton() {
        return new AndroidButton();
    }

    @Override
    public NavigationBar createNavigationBar() {
        return new AndroidNavigationBar();
    }
}
