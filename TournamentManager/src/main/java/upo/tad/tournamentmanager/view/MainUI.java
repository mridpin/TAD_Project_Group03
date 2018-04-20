package upo.tad.tournamentmanager.view;

import POJOs.Player;
import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import upo.tad.tournamentmanager.view.LoginScreen.LoginListener;

@PreserveOnRefresh
@Viewport("user-scalable=no,initial-scale=1.0")
@Theme("mytheme")
public class MainUI extends UI {

    public static WrappedSession session;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        session = getSession().getSession();
        Responsive.makeResponsive(this);
        if (session.getAttribute("user") == null) {
            setContent(new LoginScreen(new LoginListener() {
                @Override
                public void loginSuccessful() {
                    Player p = (Player) session.getAttribute("user");
                    if (!p.isType()) {
                        showMainAdminView();
                    } else {
                        showMainUserView();
                    }
                }
            }));
        } else {
            Player p = (Player) session.getAttribute("user");
            if (!p.isType()) {
                showMainAdminView();
            } else {
                showMainUserView();
            }
        }
    }

    protected void showMainAdminView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreenAdmin(MainUI.this));
        getNavigator().navigateTo(getNavigator().getState());
    }

    protected void showMainUserView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreenUser(MainUI.this));
        getNavigator().navigateTo(getNavigator().getState());
    }

    @WebServlet(urlPatterns = "/*", name = "MainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet {
    }
}
