package upo.tad.tournamentmanager.view.panels;

import POJOs.Game;
import POJOs.Player;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Date;
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
        table.addContainerProperty("Winner", String.class, null);
        table.addContainerProperty("Loser", String.class, null);
        table.addContainerProperty("Date", Date.class, null);

        rellenaTabla(table);

        table.setWidth(100, Unit.PERCENTAGE);
        table.setHeight(100, Unit.PERCENTAGE);
        table.setSelectable(true);

        left.addComponent(table);

        ComboBox winner = new ComboBox("Winner");
        winner.addItems(components);
        TextField player_name = new TextField("Name");
        player_name.setIcon(FontAwesome.USER);
        player_name.setWidth(100, Unit.PERCENTAGE);
        TextField player_nickname = new TextField("Nick name");
        player_nickname.setIcon(FontAwesome.GAMEPAD);
        player_nickname.setWidth(100, Unit.PERCENTAGE);
        player_nickname.setEnabled(false);
        PasswordField player_password = new PasswordField("Password");
        player_password.setIcon(FontAwesome.LOCK);
        player_password.setWidth(100, Unit.PERCENTAGE);
        TextField player_email = new TextField("Email");
        player_email.setIcon(FontAwesome.ENVELOPE);
        player_email.setWidth(100, Unit.PERCENTAGE);
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

        right.addComponents(player_name, player_nickname, player_password, player_email, create, update, clean, remove);

        table.addItemClickListener((event) -> {
            Object currentItemId = event.getItemId();
            String name = (String) table.getItem(currentItemId).getItemProperty("Name").getValue();
            String nick = (String) table.getItem(currentItemId).getItemProperty("Nick name").getValue();
            String email = (String) table.getItem(currentItemId).getItemProperty("Email").getValue();

            player_name.setValue(name);
            player_nickname.setValue(nick);
            player_email.setValue(email);

            update.setVisible(true);
            create.setVisible(false);
        });

        create.addClickListener((event) -> {
            //pc.addPlayer(player_name.getValue(), player_nickname.getValue(), player_password.getValue(), player_email.getValue());

            rellenaTabla(table);
            player_email.clear();
            player_name.clear();
            player_nickname.clear();
            player_password.clear();
        });

        clean.addClickListener((event) -> {
            player_email.clear();
            player_name.clear();
            player_nickname.clear();
            player_password.clear();
            create.setVisible(true);
            update.setVisible(false);
        });

        remove.addClickListener((event) -> {
            //pc.removePlayer(player_nickname.getValue());
            rellenaTabla(table);
            player_email.clear();
            player_name.clear();
            player_nickname.clear();
            player_password.clear();
            create.setVisible(true);
            update.setVisible(false);
        });

        update.addClickListener((event) -> {
            //pc.updatePlayer(player_name.getValue(), player_nickname.getValue(), player_password.getValue(), player_email.getValue());
            rellenaTabla(table);
            player_email.clear();
            player_name.clear();
            player_nickname.clear();
            player_password.clear();
            create.setVisible(true);
            update.setVisible(false);
        });
    }

    public void rellenaTabla(Table table) {
        table.removeAllItems();
        List<Game> games = gc.getGames();
        for (Game g : games) {
            table.addItem(new Object[]{g.getGameId(), g.getArmyByWinnerId().getName(), g.getArmyByLoserId().getName(), g.getDate()}, null);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
