/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;

/**
 *
 * @author ridao
 */
public class RankingsPanel extends CssLayout implements View {

    public RankingsPanel() {

    }

    @Override
    /**
     * Called before the view is shown on screen. The event object contains
     * information about parameters used when showing the view, in addition to
     * references to the old view and the new view. Override this method to
     * perform initialization of your view. By default does nothing.
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    
    private void loadData () {
        
    }

}
