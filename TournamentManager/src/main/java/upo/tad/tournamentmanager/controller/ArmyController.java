/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Army;
import java.util.ArrayList;
import java.util.List;
import upo.tad.tournamentmanager.model.DAO.DAO;

/**
 *
 * @author ridao
 */
public class ArmyController {

    DAO dao = new DAO();

    public List<String> getFactions() {
        List<String> factions = new ArrayList<>();
        factions.add("Imperium");
        factions.add("Xenos");
        factions.add("Chaos");
        return factions;
    }

    public List<String> getStrategies() {
        List<String> strategies = new ArrayList<>();
        strategies.add("Agresiva");
        strategies.add("Defensiva");
        strategies.add("Balanceada");
        strategies.add("Magia");
        strategies.add("Cuerpo a Cuerpo");
        return strategies;
    }

    public List<Integer> getFactionCount() {
        List<Integer> factionCount = new ArrayList<>();
        factionCount.add(3);
        factionCount.add(6);
        factionCount.add(8);
        return factionCount;
    }

    public void addArmy(String name, String faction, String strategy, int playerId) {
        Army a = new Army(dao.consultaJugador(playerId), name, faction, strategy);
    }
}
