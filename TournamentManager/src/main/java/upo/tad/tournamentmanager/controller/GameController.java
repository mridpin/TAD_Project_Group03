/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Army;
import POJOs.Game;
import java.util.Date;
import java.util.List;
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
}
