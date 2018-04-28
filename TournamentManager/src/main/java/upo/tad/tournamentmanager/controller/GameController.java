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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import upo.tad.tournamentmanager.model.DAO.DAO;

public class GameController {

    DAO dao = new DAO();
    PlayerController pc = new PlayerController();

    public List<Game> getGames() {
        return dao.getGames();
    }

    public void addGame(Army winner, Army loser, Date date) {
        Game game = new Game(winner, loser, date);
        Player p = winner.getPlayer();
        p.setPoints(p.getPoints() + 10);
        dao.addGame(game);
        dao.updatePlayer(p);
    }

    public void updateGame(int id, Army winner, Army loser, Date date, Player old_winner) {
        Game game = new Game(winner, loser, date);
        game.setGameId(id);
        dao.updatePlayer(old_winner);
        dao.updatePlayer(winner.getPlayer());
        dao.updateGame(game);
    }

    public void removeGame(int id, Army winner, Army loser, Date date) {
        Game game = new Game(winner, loser, date);
        game.setGameId(id);
        dao.removeGame(game);
    }

    /**
     * Counts the number of games for each faction
     *
     * @return HashMap <Faction, Count> with a count of each faction
     */
    public Map<String, Integer> getFactionPopularity() {
        Map<String, Integer> popularity = new HashMap<>();
        List<Game> games = this.getGames();
        List<String> factions = dao.getFactions();
        // Loop all the games one for each faction
        for (String s : factions) {
            // Initialize popularity of this faction to 0
            Integer count = 0;
            // Loop all the games
            for (Game g : games) {
                // If the winner or loser army belongs to this faction, add to count
                if (g.getArmyByLoserId().getFaction().equalsIgnoreCase(s)) {
                    count += 1;
                }
                if (g.getArmyByWinnerId().getFaction().equalsIgnoreCase(s)) {
                    count += 1;
                }
            }
            popularity.put(s.toUpperCase(), count);
        }
        return popularity;
    }

    /**
     * Returns a list of all the dates of the games
     *
     * @return List of dates
     */
    public Map<String, Date> getGameDates() {
        List<Game> games = this.getGames();
        Map<String, Date> gameDates = new HashMap<>();
        Calendar now = Calendar.getInstance();
        for (Game g : games) {
            Date d = g.getDate();
            now.setTime(d);
            now.set(Calendar.HOUR_OF_DAY, 10);
            d = now.getTime();
            gameDates.put("Game " + g.getGameId() + ": " + g.getArmyByWinnerId().getName() + " vs. " + g.getArmyByLoserId(), d);
        }
        return gameDates;
    }

    /**
     * For each faction, returns a List of faction names and win ratios. To do
     * this, for each faction (first List), we add another List that acts as a
     * tuple and holds a faction and its winratio
     *
     * @return List<List<Faction, Winratio>>
     */
    public List<List> getFactionsWinRatio() {
        List<List> result = new ArrayList<>();
        List<String> factions = dao.getFactions();
        for (String s : factions) {
            Integer wins = dao.factionWins(s).size();
            Integer loses = dao.factionLoses(s).size();
            Double winratio = 0.0;
            if (!loses.equals(0)) {
                winratio = wins.doubleValue() / loses.doubleValue();
            } else {
                winratio = wins.doubleValue();
            }
            List tuple = new ArrayList();
            tuple.add(s.toUpperCase());
            tuple.add(winratio);
            result.add(tuple);
        }
        return result;
    }

    /**
     * Returns a list with the games that an army has won.
     *
     * @param army The army we are querying for
     * @return The list of games
     */
    public List<Game> armyWins(Army army) {
        return dao.armyWins(army.getArmyId());
    }

    /**
     * Returns a list with the games that an army has lost.
     *
     * @param army The army we are querying for
     * @return The list of games
     */
    public List<Game> armyLosses(Army army) {
        return dao.armyLoses(army.getArmyId());
    }

    public Game getGame(int id) {
        return dao.getGame(id);
    }

    /**
     * Returns a list with the games that a faction has won.
     *
     * @param faction The faction we are querying for
     * @return The list of games
     */
    public List<Game> factionWins(String faction) {
        return dao.factionWins(faction);
    }

    /**
     * Returns a list with the games that a faction has won.
     *
     * @param faction The faction we are querying for
     * @return The list of games
     */
    public List<Game> factionLosses(String faction) {
        return dao.factionLoses(faction);
    }
}
