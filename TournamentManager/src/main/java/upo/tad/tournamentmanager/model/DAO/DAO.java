/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.model.DAO;

import POJOs.Army;
import POJOs.Game;
import POJOs.HibernateUtil;
import POJOs.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ridao
 */
public class DAO {

    Session sesion = null;

    /**
     * Check if a player han enter if the app by a Login.
     * 
     * @param user Nickname of the player
     * @param password Password of the player
     * @return An entity of the player
     */
    public Player login(String user, String password) {
        Player p = null;
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("from Player where nickname = '" + user + "' and password = '" + password + "'");

        if (q.uniqueResult() != null) {
            p = (Player) q.list().get(0);
        }

        tx.commit();
        return p;
    }

    /**
     * Return all players
     * 
     * @return List of all players
     */
    public List consultaJugadores() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Player");
        List listadoJugadores = q.list();

        tx.commit();
        return listadoJugadores;
    }

    /**
     * Return a list of all armies
     * 
     * @return A list of all armies
     */
    public List consultaEjercitos() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army");
        List listadoEjercitos = q.list();

        tx.commit();
        return listadoEjercitos;
    }

    /**
     * Check a player by an ID
     * 
     * @param playerId ID of the player
     * @return An entity of the player
     */
    public Player consultaJugador(int playerId) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Player where playerId = '" + playerId + "'");
        Player p = (Player) q.list().get(0);
        tx.commit();
        return p;
    }

    /**
     * Return a list of player whose type is 1 
     * 
     * @return A list of player
     */
    public List<Player> getPlayers() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Player where type = '1'");
        List listadoPlayers = q.list();

        tx.commit();
        return listadoPlayers;
    }

    /**
     * Add a new army
     * 
     * @param a Army
     */
    public void addArmy(Army a) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.save(a);
        tx.commit();
    }

    /**
     * Get the player by his nickname
     * 
     * @param nickname Nickname of the player
     * @return An entity of the player
     */
    public Player getPlayer(String nickname) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Player where nickname = '" + nickname + "'");
        Player p = (Player) q.uniqueResult();

        tx.commit();
        return p;
    }

    /**
     * Delete a player
     * 
     * @param p Player
     */
    public void removePlayer(Player p) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.delete(p);
        tx.commit();
    }

    /**
     * Add a new player
     * 
     * @param p Player
     */
    public void addPlayer(Player p) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.save(p);
        tx.commit();
    }

    /**
     * Update a player
     * 
     * @param p Player
     */
    public void updatePlayer(Player p) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.update(p);
        tx.commit();
    }

    /**
     * Get a list of all games
     * 
     * @return List of games
     */
    public List<Game> getGames() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Game");
        List listadoGames = q.list();

        tx.commit();
        return listadoGames;
    }

    /**
     * Get a list of armies
     * 
     * @return List of all armies
     */
    public List<Army> getArmies() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army");
        List listadoArmies = q.list();

        tx.commit();
        return listadoArmies;
    }

    /**
     * Delete an army
     * 
     * @param a Army
     */
    public void removeArmy(Army a) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.delete(a);
        tx.commit();
    }

    /**
     * Get list of armies of the player
     * 
     * @param playerId PlayerID
     * @return List of armies for user
     */
    public List<Army> getArmiesForUser(int playerId) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army where player_id='" + playerId + "'");
        List listadoArmies = q.list();

        tx.commit();
        return listadoArmies;
    }

    /**
     * Get an army by his ID
     * 
     * @param id ID of the army
     * @return Army
     */
    public Army getArmy(int id) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army where armyId='" + id + "'");
        Army army = (Army) q.uniqueResult();

        tx.commit();
        return army;
    }

    /**
     * Create a new game
     * 
     * @param g Game
     */
    public void addGame(Game g) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.save(g);
        tx.commit();
    }

    /**
     * Modify a game
     * 
     * @param g Game
     */
    public void updateGame(Game g) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.update(g);
        tx.commit();
    }

    /**
     * Delete a game
     * 
     * @param g Game
     */
    public void removeGame(Game g) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.delete(g);
        tx.commit();
    }

    /**
     * Get a list of all factions
     * 
     * @return List of factions
     */
    public List<String> getFactions() {
        List<String> factions = new ArrayList<>();
        factions.add("IMPERIUM");
        factions.add("XENOS");
        factions.add("CHAOS");
        return factions;
    }
    
    /**
     * Get a list of all strategies
     * 
     * @return List of strategies
     */
    public List<String> getStrategies() {
        List<String> strategies = new ArrayList<>();
        strategies.add("Aggresive");
        strategies.add("Defensive");
        strategies.add("Balanced");
        strategies.add("Magic");
        strategies.add("Melee");
        return strategies;
    }

    /**
     * Returns a list of all the games that this army has won
     *
     * @param armyId The army we want to know about
     * @return A List with games it has won
     */
    public List<Game> armyWins(Integer armyId) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("from Game where armyByWinnerId='" + armyId + "'");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    /**
     * Returns a list of all the games that this army has lost
     *
     * @param armyId The army we want to know about
     * @return A List with games it has lost
     */
    public List<Game> armyLoses(Integer armyId) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("from Game where armyByLoserId='" + armyId + "'");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    /**
     * Returns a list of all the games that this faction has won
     *
     * @param faction The faction we want to know about
     * @return A List with games it has won
     */
    public List<Game> factionWins(String faction) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Army a, Game g where a.faction='" + faction + "' and a.armyId=g.armyByWinnerId");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    /**
     * Returns a list of all the games that this faction has lost
     *
     * @param faction The faction we want to know about
     * @return A List with games it has lost
     */
    public List<Game> factionLoses(String faction) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Army a, Game g where a.faction='" + faction + "' and a.armyId=g.armyByLoserId");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    public Game getGame(int id) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Game where gameId='" + id + "'");
        Game game = (Game) q.uniqueResult();

        tx.commit();
        return game;
    }

    /**
     * Returns a list of all the games that this strategy has won
     *
     * @param s The strategy we want to know about
     * @return A List with games it has lost
     */
    public List<Game> stratWins(String s) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Army a, Game g where a.strategy='" + s + "' and a.armyId=g.armyByWinnerId");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    /**
     * Returns a list of all the games that this strategy has lost
     *
     * @param s The strategy we want to know about
     * @return A List with games it has lost
     */
    public List<Game> stratLosses(String s) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Army a, Game g where a.strategy='" + s + "' and a.armyId=g.armyByLoserId");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    public void updateArmy(Army a) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.update(a);
        tx.commit();
    }

    /**
     * Returns a list of games where an army using this strategy has
     * participated.
     *
     * @param strat strategy we are interested in
     * @return list of games
     */
    public List<Game> getStrategyGames(String strat) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Army a, Game g where a.strategy='" + strat + "' and (a.armyId=g.armyByLoserId or a.armyId=g.armyByWinnerId)");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    /**
     * Returns a list of games where an army from this faction has participated.
     *
     * @param faction faction we are interested in
     * @return list of games
     */
    public List<Game> getFactionGames(String faction) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Army a, Game g where a.faction='" + faction + "' and (a.armyId=g.armyByLoserId or a.armyId=g.armyByWinnerId)");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

    /**
     * Returns a list of games where this army has played
     *
     * @param armyId id of the army we are interested in
     * @return list of games
     */
    public List<Game> getArmyGames(Integer armyId) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("select distinct g from Game g where g.armyByLoserId='" + armyId + "' or g.armyByWinnerId='" + armyId + "'");
        List<Game> result = q.list();
        tx.commit();
        return result;
    }

}
