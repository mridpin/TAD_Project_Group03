package upo.tad.tournamentmanager.view.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;

public class GamesPanel extends CssLayout implements View {

    public GamesPanel() {
        setSizeFull();
        addStyleName("games");

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

        Button boton = new Button("Delete games");

        addComponents(table, boton);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
