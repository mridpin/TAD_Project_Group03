/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Player;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;
import java.util.List;
import java.util.Map;
import upo.tad.tournamentmanager.controller.ArmyController;
import upo.tad.tournamentmanager.controller.PlayerController;

/**
 *
 * @author ridao
 */
public class RankingsPanel extends CssLayout implements View {

    PlayerController pc = new PlayerController();
    ArmyController ac = new ArmyController();
    List<Player> players = null;
    Map<String, Float> armies = null;

    public RankingsPanel() {
        setSizeFull();
        addStyleName("rankings");
        this.loadData();

        /* Left side: Tables */
        // Divide left side in 3 equal parts
        VerticalSplitPanel leftMidBot = new VerticalSplitPanel();
        leftMidBot.setLocked(true);
        VerticalSplitPanel left = new VerticalSplitPanel();
        left.setSplitPosition(67, Unit.PERCENTAGE);
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
        armiesTable.addContainerProperty("Win Ratio", Float.class, null);

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
    }

}
