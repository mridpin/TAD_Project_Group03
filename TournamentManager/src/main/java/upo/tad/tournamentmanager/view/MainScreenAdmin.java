/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import upo.tad.tournamentmanager.view.panels.ArmiesPanel;
import upo.tad.tournamentmanager.view.panels.DashboardPanel;
import upo.tad.tournamentmanager.view.panels.GamesPanel;
import upo.tad.tournamentmanager.view.panels.PlayersPanel;
import upo.tad.tournamentmanager.view.panels.RankingsPanel;

public class MainScreenAdmin extends HorizontalLayout {

    private Menu menu;

    public MainScreenAdmin(MainUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        // Le damos nombre "" al panel Dashboard para que sea el panel por defecto
        menu.addView(new DashboardPanel(), "", "Dashboard", FontAwesome.ARCHIVE);
        menu.addView(new PlayersPanel(), "Players", "Players", FontAwesome.MALE);
        menu.addView(new GamesPanel(), "Games", "Games", FontAwesome.GAMEPAD);        
        menu.addView(new RankingsPanel(), "Rankings", "Rankings", FontAwesome.CHEVRON_UP);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeListener.ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}
