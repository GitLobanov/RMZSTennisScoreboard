package com.controller;

import com.model.Match;
import com.model.Player;
import com.service.MatchService;
import com.service.OngoingMatchesService;
import com.service.PlayerService;
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
    private PlayerService playerService = new PlayerService();
    private OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private Player currentPlayer1;
    private Player currentPlayer2;

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
            long id = Long.parseLong(req.getParameter("player"));
            Player player = matchService.findById(id).get();

            ongoingMatchesService.appendGamePoint(player);

            if (ongoingMatchesService.isHaveGameWinner(player)) {
                ongoingMatchesService.startGame();
            }
            if (ongoingMatchesService.isHaveSetWinner(player)) {
                ongoingMatchesService.startSet();
            }

            if (ongoingMatchesService.isEndMatch()){
                ongoingMatchesService.getWinner();
                Match match = new Match();
                match.setPlayer1(currentPlayer1);
                match.setPlayer2(currentPlayer2);
                match.setWinner(player);
                matchService.save(match);
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

            currentPlayer1 = new Player();
            currentPlayer1.setName(req.getParameter("player1"));
            currentPlayer2 = new Player();
            currentPlayer2.setName(req.getParameter("player2"));

            playerService.save(currentPlayer1);
            playerService.save(currentPlayer2);

            ongoingMatchesService.startMatch(currentPlayer1, currentPlayer2);

            ongoingMatchesService.startSet();
            ongoingMatchesService.startGame();
            req.setAttribute("matchStatus", "game");
        }

        req.setAttribute("player1", currentPlayer1.getId());
        req.setAttribute("player2", currentPlayer2.getId());

        req.getRequestDispatcher("match.jsp").forward(req, resp);
//        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
