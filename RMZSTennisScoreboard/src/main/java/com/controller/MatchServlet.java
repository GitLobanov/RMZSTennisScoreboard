package com.controller;

import com.model.Player;
import com.service.MatchService;
import com.service.OngoingMatchesService;
import com.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;

import java.io.IOException;

@WebServlet(name = "MatchServlet", urlPatterns = {"/match"})
public class MatchServlet extends HttpServlet {

    private MatchService matchService = new MatchService();
    private OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("player : " + req.getParameter("player"));
        req.getRequestDispatcher("match.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Match match = new Match();
//        Player player1 = new Player();
//        player1.setName("Bob");
//        Player player2 = new Player();
//        player2.setName("Dock");
//
//        match.setPlayer1(player1);
//        match.setPlayer2(player2);
//        match.setWinner(player2);

//        matchService.saveMatches(match);

        String resultSets = "0";
        String resultGames = "0";

        if (req.getParameter("player") != null) {
            int id = Integer.parseInt(req.getParameter("player"));
            Session session = HibernateUtil.getSessionFactory().openSession();
            Player player = session.get(Player.class, id);
            session.close();
            ongoingMatchesService.appendGamePoint(player);

            if (ongoingMatchesService.isHaveGameWinner(player)) {
                ongoingMatchesService.startGame();
            }
            if (ongoingMatchesService.isHaveSetWinner(player)) {
                ongoingMatchesService.startSet();
            }

            if (ongoingMatchesService.isEndMatch()){
                ongoingMatchesService.getWinner();
                req.setAttribute("matchStatus", "end");
            } else {
                req.setAttribute("matchStatus", "game");
            }

            resultSets = ongoingMatchesService.getResultSet();
            resultGames = ongoingMatchesService.getResultGames();
            String resultGame = ongoingMatchesService.getResultGame();
            req.setAttribute("resultSets", resultSets);
            req.setAttribute("resultGames", resultGames);
            req.setAttribute("resultGame", resultGame);
        } else {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Player player1 = session.get(Player.class, 1);
            Player player2 = session.get(Player.class, 2);
            session.close();
            ongoingMatchesService.startMatch(player1, player2);

            ongoingMatchesService.startSet();
            ongoingMatchesService.startGame();
            req.setAttribute("matchStatus", "game");
        }

        req.getRequestDispatcher("match.jsp").forward(req, resp);
//        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
