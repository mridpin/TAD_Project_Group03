/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author ridao
 */
public class DashboardPanel extends CssLayout implements View {

    public DashboardPanel() {
        setSizeFull();
        addStyleName("games");
        Label l = new Label("PENE");
        addComponent(l);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        /*
        Called before the view is shown on screen.
        The event object contains information about parameters used when showing the view, 
        in addition to references to the old view and the new view.
        Override this method to perform initialization of your view.
        By default does nothing.
         */
    }

}
