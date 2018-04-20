/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Game;
import POJOs.Player;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;
import upo.tad.tournamentmanager.controller.GameController;
import upo.tad.tournamentmanager.controller.PlayerController;

/**
 *
 * @author ridao
 */
public class DashboardPanel extends CssLayout implements View {

    PlayerController pc = new PlayerController();
    GameController gc = new GameController();
    List<Player> players = null;
    Map<String, Integer> facPop = null;

    public DashboardPanel() {
        setSizeFull();
        addStyleName("dashboard");
        this.loadData();

        //Calendario
        VerticalLayout topleft = new VerticalLayout();
        
        //Gráfico
        Chart chart = new Chart(ChartType.PIE);
        VerticalLayout bottomleft = new VerticalLayout();

        VerticalSplitPanel left = new VerticalSplitPanel(topleft, bottomleft);

        // Tabla con ranking de jugadores
        Table table = new Table("Player ranking");
        table.addContainerProperty("Nickname", String.class, null);
        table.addContainerProperty("Points", Integer.class, null);
        int i = 1;
        for (Player p : this.players) {
            table.addItem(new Object[]{p.getNickname(), p.getPoints()}, i);
            i++;
        }
        table.setWidth(100, Unit.PERCENTAGE);
        table.setPageLength(0);
        VerticalLayout right = new VerticalLayout(table);

        HorizontalSplitPanel hsp = new HorizontalSplitPanel(left, right);
        addComponent(hsp);
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

    private void loadData() {
        this.players = pc.getPlayers();
        this.facPop = gc.getFactionPopularity();
    }

}
