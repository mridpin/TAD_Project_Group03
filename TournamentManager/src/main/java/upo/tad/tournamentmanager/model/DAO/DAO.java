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
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ridao
 */
public class DAO {

    Session sesion = null;

    public boolean login(String user, String password) {
        boolean login = false;
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        Query q = sesion.createQuery("from Player where name = '" + user + "' and password = '" + password + "'");

        if (q.uniqueResult() != null) {
            login = true;
        }

        tx.commit();
        return login;
    }

    public List consultaEjercitos() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army");
        List listadoEjercitos = q.list();

        tx.commit();
        return listadoEjercitos;
    }

    public List<Player> getPlayers() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Player where type = '1'");
        List listadoPlayers = q.list();

        tx.commit();
        return listadoPlayers;
    }

    public void addPlayer(Player p) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.save(p);
        tx.commit();
    }

    public void updatePlayer(Player p) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.update(p);

        tx.commit();
    }

    public Player getPlayer(String nickname) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Player where nickname = '" + nickname + "'");
        Player p = (Player) q.uniqueResult();

        tx.commit();
        return p;
    }

    public void removePlayer(Player p) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        sesion.delete(p);
        tx.commit();
    }

    public List<Game> getGames() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Game");
        List listadoGames = q.list();

        tx.commit();
        return listadoGames;
    }

    public List<Army> getArmies() {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army");
        List listadoArmies = q.list();

        tx.commit();
        return listadoArmies;
    }

    public Army getArmy(int id) {
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();

        Query q = sesion.createQuery("from Army where armyId='" + id + "'");
        Army army = (Army) q.uniqueResult();

        tx.commit();
        return army;
    }
}
