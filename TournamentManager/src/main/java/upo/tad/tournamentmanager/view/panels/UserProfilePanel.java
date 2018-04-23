/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Player;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import upo.tad.tournamentmanager.controller.PlayerController;
import upo.tad.tournamentmanager.view.MainUI;

public class UserProfilePanel extends CssLayout implements View {

    PlayerController pc = new PlayerController();

    public UserProfilePanel() {
        setSizeFull();
        addStyleName("profile");

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        TextField name = new TextField("Name");

        name.setIcon(FontAwesome.USER);
        name.setWidth(400, Unit.PIXELS);
        TextField nickname = new TextField("Nickname");

        nickname.setIcon(FontAwesome.GAMEPAD);
        nickname.setWidth(400, Unit.PIXELS);
        TextField email = new TextField("Email");

        email.setIcon(FontAwesome.AT);
        email.setWidth(400, Unit.PIXELS);
        PasswordField old_pass = new PasswordField("Old password");
        old_pass.setIcon(FontAwesome.LOCK);
        old_pass.setWidth(400, Unit.PIXELS);
        PasswordField new_pass = new PasswordField("New password");
        new_pass.setIcon(FontAwesome.LOCK);
        new_pass.setWidth(400, Unit.PIXELS);

        Label points = new Label();
        points.setStyleName(ValoTheme.LABEL_SUCCESS);

        points.setWidth(400, Unit.PIXELS);
        Button update = new Button("Update");
        update.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        update.setWidth(400, Unit.PIXELS);
        
        rellenaCampos(name, nickname, email, points);

        layout.addComponents(name, nickname, email, old_pass, new_pass, points, update);

        layout.setComponentAlignment(name, Alignment.TOP_CENTER);
        layout.setComponentAlignment(nickname, Alignment.TOP_CENTER);
        layout.setComponentAlignment(email, Alignment.TOP_CENTER);
        layout.setComponentAlignment(old_pass, Alignment.TOP_CENTER);
        layout.setComponentAlignment(new_pass, Alignment.TOP_CENTER);
        layout.setComponentAlignment(points, Alignment.TOP_CENTER);
        layout.setComponentAlignment(update, Alignment.TOP_CENTER);

        addComponent(layout);

        update.addClickListener((event) -> {
            boolean result = pc.updatePlayerProfile(name.getValue(), nickname.getValue(), email.getValue(), old_pass.getValue(), new_pass.getValue());
            if(result){
                rellenaCampos(name, nickname, email, points);
                old_pass.clear();
                new_pass.clear();
                Notification.show("Updated", Notification.Type.HUMANIZED_MESSAGE);
            }else{
                Notification.show("Incorrect data", Notification.Type.ERROR_MESSAGE);
            }
        });

    }

    public void rellenaCampos(TextField name, TextField nickname, TextField email, Label points) {
        Player p = (Player) MainUI.session.getAttribute("user");
        name.setValue(p.getName());
        nickname.setValue(p.getNickname());
        email.setValue(p.getEmail());
        Integer point = p.getPoints();
        points.setValue(point.toString() + " points");
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
