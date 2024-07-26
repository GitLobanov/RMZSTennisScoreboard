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

    public List<Match> findByPlayerName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Match> toReturn =
                session.createQuery("select m from Match m where m.player1.name = :name or m.player2.name = :name").setParameter("name",name).list();
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
