/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import POJOs.Player;
import java.util.List;
import upo.tad.tournamentmanager.model.DAO.DAO;

/**
 *
 * @author ridao
 */
public class PlayerController {

    DAO dao = new DAO();

    public boolean checkLogin(String username, String password) {
        return dao.login(username, password);
    }

    public List<Player> getPlayers() {
        return dao.getPlayers();
    }

    public void addPlayer(String name, String nickName, String password, String email) {
        Player p = new Player(name, password, email, 0, nickName, true);
        dao.addPlayer(p);
    }
    
    public void updatePlayer(String name, String nickName, String password, String email){
        Player p = dao.getPlayer(nickName);
        p.setName(name);
        p.setNickname(nickName);
        p.setEmail(email);
        if(!password.equals("")){
            p.setPassword(password);
        }
        dao.updatePlayer(p);
    }
    
    public void removePlayer(String nickName){
        Player p = dao.getPlayer(nickName);
        dao.removePlayer(p);
    }

}
