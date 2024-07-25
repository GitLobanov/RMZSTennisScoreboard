package com.listener;

import com.factory.MatchFactory;
import com.factory.PlayerFactory;
import com.model.Match;
import com.model.Player;
import com.service.FinishedMatchesPersistenceService;
import com.service.PlayerService;
import com.util.HibernateUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.hibernate.Session;
import org.hibernate.Transaction;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        PlayerService playerService = new PlayerService();

        servletContext.setAttribute("finishedMatchesPersistenceService", finishedMatchesPersistenceService);
        servletContext.setAttribute("playerService", playerService);

        addDemoMatches(finishedMatchesPersistenceService, playerService);
    }

    public void addDemoMatches(FinishedMatchesPersistenceService finishedMatchesPersistenceService,
                               PlayerService playerService) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Player p1 = PlayerFactory.createPlayer("А. Петров");
            Player p2 = PlayerFactory.createPlayer("И. Иванов");
            Player p3 = PlayerFactory.createPlayer("С. Сидоров");
            Player p4 = PlayerFactory.createPlayer("У. Ушаков");
            Player p5 = PlayerFactory.createPlayer("Б. Баранов");

            Player p6 = PlayerFactory.createPlayer("К. Кузнецов");
            Player p7 = PlayerFactory.createPlayer("Л. Лебедев");
            Player p8 = PlayerFactory.createPlayer("М. Морозов");
            Player p9 = PlayerFactory.createPlayer("Н. Николаев");
            Player p10 = PlayerFactory.createPlayer("О. Орлов");

            playerService.saveGroup(new Player[]{p1,p2,p3,p4,p5,p6,p7,p8,p9,p10});

            Match m1 = MatchFactory.createMatch(p1,p2,p2);
            Match m1_1 = MatchFactory.createMatch(p2,p3,p3);
            Match m2 = MatchFactory.createMatch(p2,p3,p3);
            Match m3 = MatchFactory.createMatch(p3,p4,p4);
            Match m4 = MatchFactory.createMatch(p4,p5,p5);
            Match m4_1 = MatchFactory.createMatch(p4,p5,p5);
            Match m5 = MatchFactory.createMatch(p5,p6,p6);
            Match m6 = MatchFactory.createMatch(p6,p7,p7);
            Match m7 = MatchFactory.createMatch(p7,p8,p8);
            Match m8 = MatchFactory.createMatch(p8,p9,p9);
            Match m9 = MatchFactory.createMatch(p9,p10,p10);
            Match m10 = MatchFactory.createMatch(p10,p8,p8);

            finishedMatchesPersistenceService.saveGroup(new Match[]{m1, m1_1,m2,m3,m4, m4_1,m5,m6,m7,m8,m9,m10});

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
