package com.listener;

import com.model.Match;
import com.model.Player;
import com.service.MatchService;
import com.service.PlayerService;
import com.util.HibernateUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MatchService matchService = new MatchService();
        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("matchService", matchService);
        //Добавляет тестовые матчи
        addSomeMatches();
    }

    public void addSomeMatches() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            MatchService matchDao = new MatchService();
            PlayerService playerService = new PlayerService();

            Player p1 = new Player();
            p1.setName("А. Петров");

            Player p2 = new Player();
            p2.setName("И. Иванов");

            Player p3 = new Player();
            p3.setName("С. Сидоров");

            Player p4 = new Player();
            p4.setName("У. Ушаков");

            Player p5 = new Player();
            p5.setName("Б. Баранов");

            playerService.save(p1);
            playerService.save(p2);
            playerService.save(p3);
            playerService.save(p4);
            playerService.save(p5);

            Match m1 = new Match();
            m1.setPlayer1(p1);
            m1.setPlayer2(p2);
            m1.setWinner(p2);

            Match m2 = new Match();
            m2.setPlayer1(p1);
            m2.setPlayer2(p3);
            m2.setWinner(p1);

            Match m3 = new Match();
            m3.setPlayer1(p1);
            m3.setPlayer2(p4);
            m3.setWinner(p4);

            Match m4 = new Match();
            m4.setPlayer1(p1);
            m4.setPlayer2(p5);
            m4.setWinner(p1);

            Match m5 = new Match();
            m5.setPlayer1(p2);
            m5.setPlayer2(p3);
            m5.setWinner(p3);

            matchDao.save(m1);
            matchDao.save(m2);
            matchDao.save(m3);
            matchDao.save(m4);
            matchDao.save(m5);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
