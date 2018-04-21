/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Army;
import POJOs.Player;
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
        leftMidBot.setSecondComponent(factionsTable);
        
        VerticalSplitPanel right = new VerticalSplitPanel();
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
