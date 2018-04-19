/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.tournamentmanager.model.DAO;

import POJOs.HibernateUtil;
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
    
    public List consultaJugadores(){
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        
        Query q = sesion.createQuery("from Player");
        List listadoJugadores = q.list();
        
        tx.commit();
        return listadoJugadores;
    }
    
    public List consultaEjercitos(){
        sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        org.hibernate.Transaction tx = sesion.beginTransaction();
        
        Query q = sesion.createQuery("from Army");
        List listadoEjercitos = q.list();
        
        tx.commit();
        return listadoEjercitos;
    }    
}
