/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Player;
import java.util.List;
import upo.tad.tournamentmanager.model.DAO.DAO;
import upo.tad.tournamentmanager.view.MainUI;

/**
 *
 * @author ridao
 */
public class PlayerController {

    DAO dao = new DAO();

    public Player checkLogin(String username, String password) {
        return dao.login(username, password);
    }

    public List<Player> getPlayers() {
        return dao.getPlayers();
    }

    public void addPlayer(String name, String nickName, String password, String email) {
        Player p = new Player(name, password, email, 0, nickName, true);
        dao.addPlayer(p);
    }

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

    public void removePlayer(String nickName) {
        Player p = dao.getPlayer(nickName);
        dao.removePlayer(p);
    }

    public boolean updatePlayerProfile(String name, String nickname, String email, String old_pass, String new_pass) {
        Player p1 = dao.getPlayer(nickname);
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

}
