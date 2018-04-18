/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import upo.tad.tournamentmanager.model.DAO.DAO;

/**
 *
 * @author ridao
 */
public class PlayerController {
    DAO dao = new DAO();
    public boolean checkLogin(String username, String password){
        return dao.login(username, password);
    }
}
