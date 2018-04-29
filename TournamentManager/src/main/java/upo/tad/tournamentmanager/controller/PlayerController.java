/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Army;
import POJOs.Player;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import upo.tad.tournamentmanager.model.DAO.DAO;
import upo.tad.tournamentmanager.view.MainUI;

/**
 *
 * @author ridao
 */
public class PlayerController {

    DAO dao = new DAO();

    /**
     * Check if a player can log in the app by his username and password
     * 
     * @param username Nickname of the player
     * @param password Password of the player
     * @return A entity player
     */
    public Player checkLogin(String username, String password) {
        return dao.login(username, password);
    }

    /**
     * Get a list of all players
     * 
     * @return List of all players
     */
    public List<Player> getPlayers() {
        return dao.getPlayers();
    }

    /**
     * Add a new player 
     * 
     * @param name Name of the player
     * @param nickName Nickname of the player
     * @param password Password of the player
     * @param email Email of the player
     */
    public void addPlayer(String name, String nickName, String password, String email) {
        Player p = new Player(name, password, email, 0, nickName, true);
        dao.addPlayer(p);
    }

    /**
     * Modifies the data that the player has changed
     * 
     * @param name New name of the player
     * @param nickName New nickname of the player
     * @param password New password of the player
     * @param email New email of the player
     */
    public void updatePlayer(String name, String nickName, String password, String email) {
        Player p = dao.getPlayer(nickName);
        p.setName(name);
        p.setNickname(nickName);
        p.setEmail(email);
        if (!password.equals("")) {
            p.setPassword(password);
        }
        dao.updatePlayer(p);
    }

    /**
     * Remove a player from BD
     * 
     * @param nickName Nickname of the player
     */
    public void removePlayer(String nickName) {
        Player p = dao.getPlayer(nickName);
        dao.removePlayer(p);
    }

    /**
     * Update the profile of the own player
     * 
     * @param name New name of the player
     * @param nickname New nickname of the player
     * @param email New email of the player
     * @param old_pass Old password of the player
     * @param new_pass New password of the player
     * @return A boolean if changed
     */
    public boolean updatePlayerProfile(String name, String nickname, String email, String old_pass, String new_pass) {
        Player p1 = (Player) MainUI.session.getAttribute("user");
        p1 = dao.getPlayer(p1.getName());
        boolean result = true;
        if (new_pass.equals("")) {
            p1.setName(name);
            p1.setNickname(nickname);
            p1.setEmail(email);
        } else {
            if (p1.getPassword().equals(old_pass)) {
                p1.setName(name);
                p1.setNickname(nickname);
                p1.setEmail(email);
                if (!new_pass.equals("")) {
                    p1.setPassword(new_pass);
                }
            } else {
                result = false;
            }
        }

        if (result) {
            dao.updatePlayer(p1);
            MainUI.session.setAttribute("user", p1);
        }

        return result;
    }

    /**
     * Check if a player has played a game
     * 
     * @param nickname Nickname of the player
     * @return A boolean if the player has played
     */
    public boolean ifPlayerPlay(String nickname) {
        boolean haJugado = false;
        Player p = dao.getPlayer(nickname);

        Set listArmy = p.getArmies();

        for (Iterator it = listArmy.iterator(); it.hasNext();) {
            Army a = (Army) it.next();

            if (a.getGamesForLoserId().size() > 0 || a.getGamesForWinnerId().size() > 0) {
                haJugado = true;
            }

        }

        return haJugado;
    }

}
