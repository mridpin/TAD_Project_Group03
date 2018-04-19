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


/**
 * 
 * @author Antonio Jose Herrera Tabaco 
 */
public class ArmiesPanel extends CssLayout implements View{

    public ArmiesPanel(){
        
        setSizeFull();
        addStyleName("armies");

        Table table = new Table();

        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Faction", String.class, null);
        table.addContainerProperty("Strategy", String.class, null);
        table.addContainerProperty("Nickname", String.class, null);        

        table.addItem(new Object[]{"Army 1", "Faction 1", "Distancia", "Player 1"}, null);
        table.addItem(new Object[]{"Army 2", "Faction 2", "Cuerpo a cuerpo", "Player 1"}, null);
        table.addItem(new Object[]{"Army 3", "Faction 1", "Distancia", "Player 2"}, null);
        table.addItem(new Object[]{"Army 4", "Faction 3", "Cuerpo a cuerpo", "Player 3"}, null);
        
        addComponents(table);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       
}
