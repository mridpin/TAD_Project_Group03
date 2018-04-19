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
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import upo.tad.tournamentmanager.controller.ArmyController;
import upo.tad.tournamentmanager.view.MainUI;

/**
 *
 * @author Antonio Jose Herrera Tabaco
 */
public class ArmiesPanel extends CssLayout implements View {

    public ArmiesPanel() {

        ArmyController armyController = new ArmyController();
        
        setSizeFull();
        addStyleName("armies");

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
        table.addContainerProperty("Faction", String.class, null);
        table.addContainerProperty("Strategy", String.class, null);
        table.addContainerProperty("Nickname", String.class, null);

        rellenaTabla(table);

        table.setWidth(100, Unit.PERCENTAGE);
        table.setHeight(100, Unit.PERCENTAGE);
        table.setSelectable(true);

        left.addComponent(table);

        TextField army_name = new TextField("Name");
        army_name.setIcon(FontAwesome.USER);
        army_name.setWidth(100, Unit.PERCENTAGE);
        ComboBox army_faction = new ComboBox("Faction");
        
        for(String s : armyController.getFactions()){
            army_faction.addItem(s);
        }
        army_faction.setNullSelectionAllowed(false);
        army_faction.setIcon(FontAwesome.GAMEPAD);
        army_faction.setWidth(100, Unit.PERCENTAGE);  
        /*
        get faction
        army_faction.addValueChangeListener(event
                -> // Java 8
                layout.addComponent(new Label("Selected "
                        + event.getProperty().getValue()))
        );
        */
      
        ComboBox army_strategy = new ComboBox("Strategy");
        for(String s : armyController.getStrategies()){
            army_strategy.addItem(s);
        }
        army_strategy.setNullSelectionAllowed(false);
        army_strategy.setIcon(FontAwesome.GAMEPAD);
        army_strategy.setWidth(100, Unit.PERCENTAGE);

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

        right.addComponents(army_name, army_faction, army_strategy, create, update, clean, remove);
        
        table.addItemClickListener((event) -> {
            Object currentItemId = event.getItemId();
            String name = (String) table.getItem(currentItemId).getItemProperty("Name").getValue();
            String faction = (String) table.getItem(currentItemId).getItemProperty("Faction").getValue();
            String strategy = (String) table.getItem(currentItemId).getItemProperty("Strategy").getValue();

            army_name.setValue(name);

            
            update.setVisible(true);
            create.setVisible(false);
        });
        
        create.addClickListener((event) -> {
            //CREAR EJERCITO
            rellenaTabla(table);
            Player p = (Player) MainUI.session.getAttribute("user");
            armyController.addArmy((String)army_name.getValue(), (String)army_faction.getValue(), (String)army_strategy.getValue(), p.getPlayerId());
            army_name.clear();            
        });
        
        clean.addClickListener((event) -> {
            army_name.clear();
            create.setVisible(true);
            update.setVisible(false);
        });
        
        remove.addClickListener((event) -> {
            //BORRAR JUGADOR
            rellenaTabla(table);
            army_name.clear();
            create.setVisible(true);
            update.setVisible(false);
        });
        
        update.addClickListener((event) -> {
            //ACTUALIZAR JUGADOR
            rellenaTabla(table);
            army_name.clear();
            create.setVisible(true);
            update.setVisible(false);
        });

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

    private void rellenaTabla(Table table) {
        table.addItem(new Object[]{"Army 1", "Faction 1", "Distancia", "Player 1"}, null);
        table.addItem(new Object[]{"Army 2", "Faction 2", "Cuerpo a cuerpo", "Player 1"}, null);
        table.addItem(new Object[]{"Army 3", "Faction 1", "Distancia", "Player 2"}, null);
        table.addItem(new Object[]{"Army 4", "Faction 3", "Cuerpo a cuerpo", "Player 3"}, null);
    }

}
