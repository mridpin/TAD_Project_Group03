/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Army;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;
import java.util.List;
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
    List<List> strategies = null;
    List<List> armies = null;
    List<List> factions = null;

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
        Configuration pieConf = pie.getConfiguration();
        pieConf.setTitle("Points per game");
        pieConf.setSubTitle("Wins vs. Loses");
        DataSeries dataWinLoss = new DataSeries();
        PlotOptionsPie plot = new PlotOptionsPie();
        plot.setColors(new SolidColor[]{new SolidColor("green"), new SolidColor("red")});
        dataWinLoss.setPlotOptions(plot);
        pieConf.addSeries(dataWinLoss);
        right.setFirstComponent(pie);

        // Chart 2: Points per game line chart
        Chart line = new Chart(ChartType.LINE);
        line.setWidth(100, Unit.PERCENTAGE);
        line.setHeight(100, Unit.PERCENTAGE);
        Configuration lineConf = line.getConfiguration();
        lineConf.setTitle("Points history");
        lineConf.setSubTitle("Cumulative points per game");
        XAxis xaxis = new XAxis();
        xaxis.setTitle("Game Number");
        xaxis.setMin(0);
        // Max size is the total number of games in the tournament so far
        xaxis.setMax(gc.getGames().size());
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Points");
        // Max size is the total number of points achievable in the tournament so far
        yaxis.setMax(gc.getGames().size() * 10);
        lineConf.addxAxis(xaxis);
        lineConf.addyAxis(yaxis);
        ListSeries dataLine = new ListSeries("Points");
        lineConf.addSeries(dataLine);
        right.setSecondComponent(line);

        /* Left side: Tables */
        // Divide left side in 3 equal parts
        VerticalSplitPanel leftMidBot = new VerticalSplitPanel();
        leftMidBot.setLocked(true);
        VerticalSplitPanel left = new VerticalSplitPanel();
        left.setSplitPosition(33, Unit.PERCENTAGE);
        left.setLocked(true);
        left.setSecondComponent(leftMidBot);

        // Table 1: Strategies
        Table stratsTable = new Table("Strategies Rankings");
        stratsTable.addContainerProperty("Strategy", String.class, null);
        stratsTable.addContainerProperty("Win Ratio", Double.class, null);
        int i = 1;
        for (List tuple : this.strategies) {
            String strat = (String) tuple.get(0);
            Double d = (Double) tuple.get(1);
            stratsTable.addItem(new Object[]{strat.toUpperCase(), d}, i);
            i++;
        }
        stratsTable.setWidth(100, Unit.PERCENTAGE);
        stratsTable.setPageLength(0);
        stratsTable.setSelectable(true);
        stratsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            /**
             * Changes the information displayed on the graphs according to what
             * has been clicked
             */
            public void itemClick(ItemClickEvent event) {
                Integer stratId = (Integer) event.getItemId() - 1; // -1 Prevents index out of bounds
                String strat = (String) strategies.get(stratId).get(0);
                // Draw pie chart
                dataWinLoss.clear();
                Integer wins = gc.strategyWins(strat).size();
                Integer losses = gc.strategyLosses(strat).size();
                dataWinLoss.setData(new String[]{"WINS", "LOSSES"}, new Integer[]{wins, losses});
                pieConf.setTitle("Performance: " + strat.toUpperCase());
                pie.drawChart();
                // Draw line chart
                List<Number> pointsPerGame = gc.strategyPointHistory(strat);
                dataLine.setData(pointsPerGame);
                lineConf.setTitle("Points history: " + strat.toUpperCase());
                line.drawChart();
            }
        });
        left.setFirstComponent(stratsTable);

        // Table 2: Armies
        Table armiesTable = new Table("Army Rankings");
        armiesTable.addContainerProperty("Army Name", String.class, null);
        armiesTable.addContainerProperty("Win Ratio", Double.class, null);
        i = 1;
        for (List tuple : this.armies) {
            String armyName = ((Army) tuple.get(0)).getName();
            Double d = (Double) tuple.get(1);
            armiesTable.addItem(new Object[]{armyName, d}, i);
            i++;
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
                Integer armyId = (Integer) event.getItemId() - 1; // -1 Prevents index out of bounds
                Army army = (Army) armies.get(armyId).get(0);
                // Draw pir chart
                dataWinLoss.clear();
                Integer wins = gc.armyWins(army).size();
                Integer losses = gc.armyLosses(army).size();
                dataWinLoss.setData(new String[]{"WINS", "LOSSES"}, new Integer[]{wins, losses});
                pieConf.setTitle("Performance: " + army.getName().toUpperCase());
                pie.drawChart();
                // Draw line chart
                List<Number> pointsPerGame = gc.armyPointHistory(army);
                dataLine.setData(pointsPerGame);
                lineConf.setTitle("Points history: " + army.getName().toUpperCase());
                line.drawChart();
            }
        });
        leftMidBot.setFirstComponent(armiesTable);

        // Table 3: Factions ranking
        Table factionsTable = new Table("Factions Rankings");
        factionsTable.addContainerProperty("Faction Name", String.class, null);
        factionsTable.addContainerProperty("Win Ratio", Double.class, null);
        i = 1;
        for (List tuple : this.factions) {
            String faction = (String) tuple.get(0);
            Double d = (Double) tuple.get(1);
            factionsTable.addItem(new Object[]{faction, d}, i);
            i++;
        }
        factionsTable.setWidth(100, Unit.PERCENTAGE);
        factionsTable.setPageLength(0);
        factionsTable.setSelectable(true);
        factionsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            /**
             * Changes the information displayed on the graphs according to what
             * has been clicked
             */
            public void itemClick(ItemClickEvent event) {
                Integer factionId = (Integer) event.getItemId() - 1; // -1 Prevents index out of bounds
                String faction = (String) factions.get(factionId).get(0);
                // Draw pir chart
                dataWinLoss.clear();
                Integer wins = gc.factionWins(faction).size();
                Integer losses = gc.factionLosses(faction).size();
                dataWinLoss.setData(new String[]{"WINS", "LOSSES"}, new Integer[]{wins, losses});
                pieConf.setTitle("Performance: " + faction.toUpperCase());
                pie.drawChart();
                // Draw line chart
                List<Number> pointsPerGame = gc.factionPointHistory(faction);
                dataLine.setData(pointsPerGame);
                lineConf.setTitle("Points history: " + faction.toUpperCase());
                line.drawChart();
            }
        });

        leftMidBot.setSecondComponent(factionsTable);
        HorizontalSplitPanel hsp = new HorizontalSplitPanel(left, right);
        addComponent(hsp);
    }

    /**
     * Called before the view is shown on screen. The event object contains
     * information about parameters used when showing the view, in addition to
     * references to the old view and the new view. Override this method to
     * perform initialization of your view. By default does nothing.
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private void loadData() {
        this.strategies = gc.getStrategiesWinRatio();
        this.armies = ac.getArmiesWinRatio();
        this.factions = gc.getFactionsWinRatio();
    }

}
