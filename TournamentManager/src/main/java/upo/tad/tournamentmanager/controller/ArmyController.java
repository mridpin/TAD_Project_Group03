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
import java.util.List;
import java.util.Map;
import upo.tad.tournamentmanager.model.DAO.*;

/**
 *
 * @author ridao
 */
public class ArmyController {

    DAO dao = new DAO();

    /**
     * Get a list of all factions
     * 
     * @return List of all factions 
     */
    public List<String> getFactions() {
        return dao.getFactions();
    }

    /**
     * Get a list of all strategies
     * 
     * @return List of all strategies
     */
    public List<String> getStrategies() {
        return dao.getStrategies();
    }

    /**
     * Get a list of all armies
     * 
     * @return List of all armies
     */
    public List<Army> getArmies() {
        return dao.getArmies();
    }

    /**
     * Return all armies of the user
     * 
     * @param playerId ID of the player
     * @return List of armies' player
     */
    public List<Army> getArmiesForUser(int playerId) {
        return dao.getArmiesForUser(playerId);
    }
    
    /**
     * Return a army by his ID
     * 
     * @param id ID of the army
     * @return 
     */
    public Army getArmy(int id) {
        return dao.getArmy(id);
    }

    /**
     * Add a new army for the player
     * 
     * @param name Name of the army
     * @param faction Faction of the army
     * @param strategy Strategy of the army
     * @param playerId ID of the player
     */
    public void addArmy(String name, String faction, String strategy, int playerId) {
        Army a = new Army(dao.consultaJugador(playerId), name, faction, strategy);
        dao.addArmy(a);        
    }

    /**
     * Remove the army of the player
     * 
     * @param armyId ID of the army
     * @param armyName Name of the army
     * @param armyFaction Faction of the army
     * @param armyStrategy Strategy of the army
     * @param player Player who has the army
     */
    public void removeArmy(int armyId, String armyName, String armyFaction, String armyStrategy, Player player){
        Army a = new Army(player, armyName, armyFaction, armyStrategy);
        a.setArmyId(armyId);
        dao.removeArmy(a);
    }

    /**
     * For each army, returns a List of army names and win ratios. To do this, 
     * for each army (first List), we add another List that acts as a tuple 
     * and holds an Army and its winratio
     * 
     * @return List<List<Army, Winratio>>
     */
    public List<List> getArmiesWinRatio() {
        List<List> result = new ArrayList<>();
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
            List tuple = new ArrayList();
            tuple.add(a);
            tuple.add(winratio);
            result.add(tuple);
        }
        return result;
    }
    
    /**
     * Check if an army has played
     * 
     * @param id of the army
     * @return True if the army has played
     */
    public boolean ifArmyPlay(int id){
        Army a = getArmy(id);
        boolean canDelete = false;
        
        if(a.getGamesForWinnerId().size() == 0 || a.getGamesForWinnerId().size() == 0){
            canDelete = true;
        }
        
        return canDelete;
    }
    
    /**
     * Update the name of the army
     * 
     * @param id ID of the army
     * @param name  new name of the army
     */
    public void updateArmy(int id, String name){        
        Army a = dao.getArmy(id);
        a.setName(name);
        dao.updateArmy(a);
    }
}
