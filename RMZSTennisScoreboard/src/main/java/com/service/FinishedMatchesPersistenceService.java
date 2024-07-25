package com.service;

import com.model.Match;
import com.model.Player;
import com.repository.CrudRepository;
import com.sun.xml.bind.v2.model.core.ID;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class FinishedMatchesPersistenceService implements CrudRepository<Match, Long> {

    @Override
    public void save(Match match) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(match);
            session.flush();
            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Match entity) {
    }

    @Override
    public void update(Match entity) {
    }

    public void saveGroup (Match[] matches){
        for (Match p : matches) {
            save(p);
        }
    }

    @Override
    public Optional<Match> findById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Optional<Match> toReturn = Optional.of(session.get(Match.class, id));
        session.close();
        return toReturn;
    }

    public Optional<Player> findByPlayerName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Optional<Player> toReturn = Optional.of((Player)
                session.createQuery("select m, p from Player p,Match m where p.name = :name").setParameter("name",name).list().get(0));
        session.close();
        return toReturn;
    }


    @Override
    public List<Match> findAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List list = session.createCriteria(Match.class).list();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

    }
}
