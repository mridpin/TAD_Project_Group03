/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Army;
import POJOs.Game;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import upo.tad.tournamentmanager.model.DAO.DAO;

public class GameController {

    DAO dao = new DAO();

    public List<Game> getGames() {
        return dao.getGames();
    }

    public void addGame(Army winner, Army loser, Date date) {
        Game game = new Game(winner, loser, date);
        dao.addGame(game);
    }

    public void updateGame(int id, Army winner, Army loser, Date date) {
        Game game = new Game(winner, loser, date);
        game.setGameId(id);
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
                if (g.getArmyByLoserId().getFaction().equals(s)) {
                    count += 1;
                }
                if (g.getArmyByWinnerId().getFaction().equals(s)) {
                    count += 1;
                }
            }
            popularity.put(s, count);
        }
        return popularity;
    }
}
