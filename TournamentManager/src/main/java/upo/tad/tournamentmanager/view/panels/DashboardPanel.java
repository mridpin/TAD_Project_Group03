/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.view.panels;

import POJOs.Player;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
    Map<String, Date> dates = null;

    public DashboardPanel() {
        setSizeFull();
        addStyleName("dashboard");
        this.loadData();

        //Calendario
        Calendar cal = new Calendar("Last Games");
        cal.setSizeFull();
        Date month = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        cal.setStartDate(month);
        cal.setEndDate(new Date());
        Iterator it = dates.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String armies = (String) pair.getKey();
            Date d = (Date) pair.getValue();
            cal.addEvent(new BasicEvent(armies,
                    armies,
                    d, Date.from(d.toInstant().plusSeconds(3600 * 3))));
            it.remove(); // avoids a ConcurrentModificationException
        }
        VerticalLayout topleft = new VerticalLayout(cal);

        //Gráfico
        Chart chart = new Chart(ChartType.PIE);
        chart.setWidth(100, Unit.PERCENTAGE);
        chart.setHeight(100, Unit.PERCENTAGE);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Most popular factions");
        conf.setSubTitle("Popularity of each faction");
        DataSeries data = new DataSeries(facPop.keySet().toArray(new String[facPop.size()]), facPop.values().toArray(new Integer[facPop.size()]));
        PlotOptionsPie plot = new PlotOptionsPie();
        // Un color especifico para cada faccion
        plot.setColors(new SolidColor[]{new SolidColor("#9E0000"), new SolidColor("#2F478A"), new SolidColor("#4FAF00")});
        data.setPlotOptions(plot);
        conf.addSeries(data);
        VerticalLayout bottomleft = new VerticalLayout(chart);

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
        this.dates = gc.getGameDates();
    }

}
