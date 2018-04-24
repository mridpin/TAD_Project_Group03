package upo.tad.tournamentmanager.view.panels;

import POJOs.Army;
import POJOs.Game;
import POJOs.Player;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import upo.tad.tournamentmanager.controller.ArmyController;
import upo.tad.tournamentmanager.controller.GameController;

public class GamesPanel extends CssLayout implements View {

    GameController gc = new GameController();
    ArmyController ac = new ArmyController();

    public GamesPanel() {
        setSizeFull();
        addStyleName("games");

        HorizontalSplitPanel mainLayout = new HorizontalSplitPanel();
        mainLayout.setLocked(true);
        mainLayout.setSplitPosition(80, Unit.PERCENTAGE);

        VerticalLayout left = new VerticalLayout();
        left.setSpacing(true);
        left.setMargin(true);
        left.setSizeFull();

        VerticalLayout right = new VerticalLayout();
        right.setSpacing(true);
        right.setMargin(true);

        mainLayout.setFirstComponent(left);
        mainLayout.setSecondComponent(right);

        addComponent(mainLayout);

        Table table = new Table();

        table.addContainerProperty("Id", Integer.class, null);
        table.addContainerProperty("Winner", Army.class, null);
        table.addContainerProperty("Loser", Army.class, null);
        table.addContainerProperty("Date", Date.class, null);

        rellenaTabla(table);

        table.setWidth(100, Unit.PERCENTAGE);
        table.setHeight(100, Unit.PERCENTAGE);
        table.setSelectable(true);

        left.addComponent(table);

        TextField game_id = new TextField("Id");
        game_id.setWidth(100, Unit.PERCENTAGE);
        game_id.setIcon(FontAwesome.FLAG);
        game_id.setEnabled(false);

        ComboBox game_winner = new ComboBox("Winner");
        game_winner.addItems(ac.getArmies());
        game_winner.setWidth(100, Unit.PERCENTAGE);
        game_winner.setIcon(FontAwesome.ARROW_UP);

        ComboBox game_loser = new ComboBox("Loser");
        game_loser.addItems(ac.getArmies());
        game_loser.setWidth(100, Unit.PERCENTAGE);
        game_loser.setIcon(FontAwesome.ARROW_DOWN);

        DateField game_date = new DateField("Game's date");
        game_date.setWidth(100, Unit.PERCENTAGE);
        game_date.setIcon(FontAwesome.CALENDAR);

        Button create = new Button("Create");
        create.setIcon(FontAwesome.PLUS);
        create.setWidth(100, Unit.PERCENTAGE);
        create.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        Button update = new Button("Update");
        update.setIcon(FontAwesome.ARROW_UP);
        update.setWidth(100, Unit.PERCENTAGE);
        update.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        update.setVisible(false);
        Button clean = new Button("Clean");
        clean.setIcon(FontAwesome.REFRESH);
        clean.setWidth(100, Unit.PERCENTAGE);
        clean.setStyleName(ValoTheme.BUTTON_PRIMARY);
        Button remove = new Button("Remove");
        remove.setIcon(FontAwesome.REMOVE);
        remove.setWidth(100, Unit.PERCENTAGE);
        remove.setStyleName(ValoTheme.BUTTON_DANGER);

        right.addComponents(game_id, game_winner, game_loser, game_date, create, update, clean, remove);

        table.addItemClickListener((event) -> {
            Object currentItemId = event.getItemId();
            Integer id = (Integer) table.getItem(currentItemId).getItemProperty("Id").getValue();
            Army winner = (Army) table.getItem(currentItemId).getItemProperty("Winner").getValue();
            Army loser = (Army) table.getItem(currentItemId).getItemProperty("Loser").getValue();
            Date date = (Date) table.getItem(currentItemId).getItemProperty("Date").getValue();

            Collection ids = game_winner.getItemIds();

            Iterator it = ids.iterator();
            while (it.hasNext()) {
                Army aux = (Army) it.next();
                if (aux.getArmyId() == winner.getArmyId()) {
                    game_winner.select(aux);
                }
            }

            ids = game_loser.getItemIds();

            it = ids.iterator();
            while (it.hasNext()) {
                Army aux = (Army) it.next();
                if (aux.getArmyId() == loser.getArmyId()) {
                    game_loser.select(aux);
                }
            }

            game_id.setValue(id.toString());

            game_date.setValue(date);

            update.setVisible(true);
            create.setVisible(false);
        });

        create.addClickListener((event) -> {
            gc.addGame((Army) game_winner.getValue(), (Army) game_loser.getValue(), game_date.getValue());
            rellenaTabla(table);
            game_id.clear();
            game_winner.clear();
            game_loser.clear();
            game_date.clear();
        });

        clean.addClickListener((event) -> {
            game_id.clear();
            game_winner.clear();
            game_loser.clear();
            game_date.clear();
            create.setVisible(true);
            update.setVisible(false);
        });

        remove.addClickListener((event) -> {
            gc.removeGame(Integer.parseInt(game_id.getValue()), (Army) game_winner.getValue(), (Army) game_loser.getValue(), game_date.getValue());
            rellenaTabla(table);
            game_id.clear();
            game_winner.clear();
            game_loser.clear();
            game_date.clear();
            create.setVisible(true);
            update.setVisible(false);
        });

        update.addClickListener((event) -> {
            Game g1 = gc.getGame(Integer.parseInt(game_id.getValue()));
            Player p = g1.getArmyByWinnerId().getPlayer();
            p.setPoints(p.getPoints()-10);
            //PERSISTIR AL ANTIGUO GANADOR
            Army a1 = (Army) game_winner.getValue();
            a1.getPlayer().setPoints(a1.getPlayer().getPoints()+10);
            //PERSISTIR AL NUEVO
            gc.updateGame(Integer.parseInt(game_id.getValue()), (Army) game_winner.getValue(), (Army) game_loser.getValue(), game_date.getValue(), p);
            rellenaTabla(table);
            game_id.clear();
            game_winner.clear();
            game_loser.clear();
            game_date.clear();
            create.setVisible(true);
            update.setVisible(false);
        });
    }

    public void rellenaTabla(Table table) {
        table.removeAllItems();
        List<Game> games = gc.getGames();
        for (Game g : games) {
            table.addItem(new Object[]{g.getGameId(), g.getArmyByWinnerId(), g.getArmyByLoserId(), g.getDate()}, null);
        }
    }

    @Override
    /**
     * Called before the view is shown on screen. The event object contains
     * information about parameters used when showing the view, in addition to
     * references to the old view and the new view. Override this method to
     * perform initialization of your view. By default does nothing.
     */
    public void enter(ViewChangeEvent event) {

    }

}
