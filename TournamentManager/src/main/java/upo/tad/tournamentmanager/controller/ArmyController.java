/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Army;
import POJOs.Game;
import POJOs.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Army> getArmies() {
        return dao.getArmies();
    }

    public List<Army> getArmiesForUser(int playerId) {
        return dao.getArmiesForUser(playerId);
    }
    
    public Army getArmy(int id) {
        return dao.getArmy(id);
    }

    public void addArmy(String name, String faction, String strategy, int playerId) {
        Army a = new Army(dao.consultaJugador(playerId), name, faction, strategy);
        dao.addArmy(a);        
    }

    public void removeArmy(int armyId, String armyName, String armyFaction, String armyStrategy, Player player){
        Army a = new Army(player, armyName, armyFaction, armyStrategy);
        a.setArmyId(armyId);
        dao.removeArmy(a);
    }

    /**
     * Returns a Map of army names and win ratios of each army. To do this, for each army,
     * it counts all the wins and all the loses with two HQL clauses, and returns the division
     * 
     * @return Map<Army name, Win Ratio>
     */
    public Map<String, Double> getArmiesWinRatio() {
        Map<String, Double> result = new HashMap<>();
        List<Game> games = dao.getGames();
        List<Army> armies = dao.getArmies();
        for (Army a : armies) {
            Integer wins = dao.armyWins(a.getArmyId()).size();
            Integer loses = dao.armyLoses(a.getArmyId()).size();
            Double winratio = 0.0;
            if (!loses.equals(0)) {
                winratio = wins.doubleValue() / loses.doubleValue();
            } else {
                winratio = wins.doubleValue();
            }
            result.put(a.getName(), winratio);
        }
        return result;
    }
}
