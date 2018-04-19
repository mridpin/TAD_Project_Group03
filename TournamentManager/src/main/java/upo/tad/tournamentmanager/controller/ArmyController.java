/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ridao
 */
public class ArmyController {

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
    
}
