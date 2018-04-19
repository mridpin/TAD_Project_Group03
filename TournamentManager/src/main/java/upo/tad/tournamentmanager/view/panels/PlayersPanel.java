/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class PlayersPanel extends CssLayout implements View {

    public PlayersPanel() {
        setSizeFull();
        addStyleName("players");

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

        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Nick name", String.class, null);
        table.addContainerProperty("Email", String.class, null);
        table.addContainerProperty("# Armies", Integer.class, null);
        table.addContainerProperty("won games", Integer.class, null);
        table.addContainerProperty("lost games", Integer.class, null);
        table.addContainerProperty("Points", Integer.class, null);

        rellenaTabla(table);

        table.setWidth(100, Unit.PERCENTAGE);
        table.setHeight(100, Unit.PERCENTAGE);
        table.setSelectable(true);

        left.addComponent(table);

        TextField player_name = new TextField("Name");
        player_name.setIcon(FontAwesome.USER);
        player_name.setWidth(100, Unit.PERCENTAGE);
        TextField player_nickname = new TextField("Nick name");
        player_nickname.setIcon(FontAwesome.GAMEPAD);
        player_nickname.setWidth(100, Unit.PERCENTAGE);
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
            //CREAR JUGADOR
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
            //BORRAR JUGADOR
            rellenaTabla(table);
            player_email.clear();
            player_name.clear();
            player_nickname.clear();
            player_password.clear();
            create.setVisible(true);
            update.setVisible(false);
        });
        
        update.addClickListener((event) -> {
            //ACTUALIZAR JUGADOR
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
        //RELLENAR TABLA
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
        table.addItem(new Object[]{"Player 1", "player1", "player1@tad.com", 4, 9, 7, 452}, null);
        table.addItem(new Object[]{"Player 2", "player2", "player2@tad.com", 7, 16, 74, 236}, null);
        table.addItem(new Object[]{"Player 3", "player3", "player3@tad.com", 6, 7, 6, 410}, null);
        table.addItem(new Object[]{"Player 4", "player4", "player4@tad.com", 9, 0, 4, 127}, null);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
