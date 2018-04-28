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

    public List<String> getFactions() {
        return dao.getFactions();
    }

    public List<String> getStrategies() {
        return dao.getStrategies();
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
    
    public boolean ifArmyPlay(int id){
        Army a = getArmy(id);
        boolean canDelete = true;
        
        if(a.getGamesForWinnerId().size() > 0 || a.getGamesForWinnerId().size() > 0){
            canDelete = false;
        }
        
        return canDelete;
    }
}
