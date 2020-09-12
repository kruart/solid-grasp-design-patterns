package app.factory;

import app.widgets.navigationbar.NavigationBar;
import app.widgets.button.Button;

public interface WidgetFactory {
    Button createButton();
    NavigationBar createNavigationBar();
}
