/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;

public class PlayerPanel extends CssLayout implements View {

    public PlayerPanel() {
        setSizeFull();
        addStyleName("players");

        Table table = new Table();

        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Nick name", String.class, null);
        table.addContainerProperty("# Armies", Integer.class, null);
        table.addContainerProperty("won games", Integer.class, null);
        table.addContainerProperty("lost games", Integer.class, null);

        table.addItem(new Object[]{"Player 1", "player1", 4, 9, 7}, null);
        table.addItem(new Object[]{"Player 2", "player2", 7, 16, 74}, null);
        table.addItem(new Object[]{"Player 3", "player3", 6, 7, 6}, null);
        table.addItem(new Object[]{"Player 4", "player4", 9, 0, 4}, null);

        table.setPageLength(table.size());

        addComponent(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
