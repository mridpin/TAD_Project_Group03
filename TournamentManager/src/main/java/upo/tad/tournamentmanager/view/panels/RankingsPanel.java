/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Army;
import POJOs.Player;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import upo.tad.tournamentmanager.controller.ArmyController;
import upo.tad.tournamentmanager.controller.GameController;
import upo.tad.tournamentmanager.controller.PlayerController;

/**
 *
 * @author ridao
 */
public class RankingsPanel extends CssLayout implements View {

    PlayerController pc = new PlayerController();
    GameController gc = new GameController();
    ArmyController ac = new ArmyController();
    List<Player> players = null;
    Map<String, Double> armies = null;
    Map<String, Double> factions = null;

    public RankingsPanel() {
        setSizeFull();
        addStyleName("rankings");
        this.loadData();

        /*Right side: charts*/
        VerticalSplitPanel right = new VerticalSplitPanel();
        // Chart 1: Win/loss count
        Chart pie = new Chart(ChartType.PIE);
        pie.setWidth(100, Unit.PERCENTAGE);
        pie.setHeight(100, Unit.PERCENTAGE);
        Configuration conf = pie.getConfiguration();
        conf.setTitle("Performance");
        conf.setSubTitle("Wins vs. Loses");
        DataSeries data1 = new DataSeries();
        PlotOptionsPie plot = new PlotOptionsPie();
        plot.setColors(new SolidColor[]{new SolidColor("green"), new SolidColor("red")});
        data1.setPlotOptions(plot);
        conf.addSeries(data1);
        right.setFirstComponent(pie);
        /* Left side: Tables */
        // Divide left side in 3 equal parts
        VerticalSplitPanel leftMidBot = new VerticalSplitPanel();
        leftMidBot.setLocked(true);
        VerticalSplitPanel left = new VerticalSplitPanel();
        left.setSplitPosition(33, Unit.PERCENTAGE);
        left.setLocked(true);
        left.setSecondComponent(leftMidBot);

        // Table 1: Players
        Table playersTable = new Table("Player Rankings");
        playersTable.addContainerProperty("Nickname", String.class, null);
        playersTable.addContainerProperty("Points", Integer.class, null);
        int i = 1;
        for (Player p : this.players) {
            playersTable.addItem(new Object[]{p.getNickname(), p.getPoints()}, i);
            i++;
        }
        playersTable.setWidth(100, Unit.PERCENTAGE);
        playersTable.setPageLength(0);
        playersTable.setSelectable(true);
        left.setFirstComponent(playersTable);

        // Table 2: Armies
        Table armiesTable = new Table("Army Rankings");
        armiesTable.addContainerProperty("Army Name", String.class, null);
        armiesTable.addContainerProperty("Win Ratio", Double.class, null);
        i = 1;
        Iterator it = this.armies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String army = (String) pair.getKey();
            Double d = (Double) pair.getValue();
            armiesTable.addItem(new Object[]{army, d}, i);
            i++;
            it.remove(); // avoids a ConcurrentModificationException
        }
        armiesTable.setWidth(100, Unit.PERCENTAGE);
        armiesTable.setPageLength(0);
        armiesTable.setSelectable(true);
        armiesTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            /**
             * Changes the information displayed on the graphs according to what
             * has been clicked
             */
            public void itemClick(ItemClickEvent event) {
                data1.clear();
            }
        });
        leftMidBot.setFirstComponent(armiesTable);

        // Table 3: Factions ranking
        Table factionsTable = new Table("Factions Rankings");
        factionsTable.addContainerProperty("Faction Name", String.class, null);
        factionsTable.addContainerProperty("Win Ratio", Double.class, null);
        i = 1;
        it = this.factions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String faction = (String) pair.getKey();
            Double d = (Double) pair.getValue();
            factionsTable.addItem(new Object[]{faction, d}, i);
            i++;
            it.remove(); // avoids a ConcurrentModificationException
        }
        factionsTable.setWidth(100, Unit.PERCENTAGE);
        factionsTable.setPageLength(0);
        factionsTable.setSelectable(true);
        leftMidBot.setSecondComponent(factionsTable);

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
        this.armies = ac.getArmiesWinRatio();
        this.factions = gc.getFactionsWinRatio();
    }

}
