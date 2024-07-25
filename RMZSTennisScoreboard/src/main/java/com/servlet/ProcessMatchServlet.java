package com.servlet;

import com.model.Match;
import com.model.Player;
import com.service.FinishedMatchesPersistenceService;
import com.service.OngoingCalculationMatchesService;
import com.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// TODO CHANGE to /match-score

@WebServlet(name = "ProcessMatchServlet", urlPatterns = {"/match"})
public class ProcessMatchServlet extends HttpServlet {

    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private PlayerService playerService;
    private OngoingCalculationMatchesService ongoingCalculationMatchesService = new OngoingCalculationMatchesService();
    private Player currentPlayer1;
    private Player currentPlayer2;

    @Override
    public void init() throws ServletException {

        finishedMatchesPersistenceService =
                (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
        playerService =
                (PlayerService) getServletContext().getAttribute("playerService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("player : " + req.getParameter("player"));
        req.getRequestDispatcher("match.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String resultSets = "0";
        String resultGames = "0";

        if (req.getParameter("player") != null) {
            long id = Long.parseLong(req.getParameter("player"));
            Player player = playerService.findById(id).get();

            ongoingCalculationMatchesService.appendGamePoint(player);

            if (ongoingCalculationMatchesService.isHaveGameWinner(player)) {
                ongoingCalculationMatchesService.startGame();
            }
            if (ongoingCalculationMatchesService.isHaveSetWinner(player)) {
                ongoingCalculationMatchesService.startSet();
            }

            if (ongoingCalculationMatchesService.isEndMatch()){
                ongoingCalculationMatchesService.getWinner();
                Match match = new Match();
                match.setPlayer1(currentPlayer1);
                match.setPlayer2(currentPlayer2);
                match.setWinner(player);
                finishedMatchesPersistenceService.save(match);
                req.setAttribute("matchStatus", "end");
                req.setAttribute("winner", match.getWinner().getName());
            } else {
                req.setAttribute("matchStatus", "game");
            }

            resultSets = ongoingCalculationMatchesService.getResultSet();
            resultGames = ongoingCalculationMatchesService.getResultGames();
            String resultGame = ongoingCalculationMatchesService.getResultGame();
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

            ongoingCalculationMatchesService.startMatch(currentPlayer1, currentPlayer2);

            ongoingCalculationMatchesService.startSet();
            ongoingCalculationMatchesService.startGame();
            req.setAttribute("matchStatus", "game");
        }

        req.setAttribute("player1", currentPlayer1.getId());
        req.setAttribute("player1Name", currentPlayer1.getName());
        req.setAttribute("player2", currentPlayer2.getId());
        req.setAttribute("player2Name", currentPlayer2.getName());

        req.getRequestDispatcher("match.jsp").forward(req, resp);
//        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
