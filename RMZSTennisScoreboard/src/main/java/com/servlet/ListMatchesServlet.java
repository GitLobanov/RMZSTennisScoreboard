package com.servlet;


import com.model.Match;
import com.service.FinishedMatchesPersistenceService;
import com.service.PlayerService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ListMatchesServlet", urlPatterns = "/matches")
public class ListMatchesServlet extends HttpServlet {

    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        finishedMatchesPersistenceService = (FinishedMatchesPersistenceService) getServletContext().getAttribute("finishedMatchesPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String nameOfPlayer = req.getParameter("filter_by_player_name");

        List<Match> matchList;
        if (nameOfPlayer != null) {
            matchList = finishedMatchesPersistenceService.findByPlayerName(nameOfPlayer);
        } else {
            matchList = finishedMatchesPersistenceService.findAll();
        }


        int matchesInOnePage = 5;
        int pages = (int) (Math.ceil( (double) matchList.size() / matchesInOnePage));
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int min = (page-1) * matchesInOnePage;
        int max = Math.min((min + matchesInOnePage), (matchList.size()));

        List<Match> matchesOnPage = new ArrayList<>();
        for (;min < max; min++) {
            matchesOnPage.add(matchList.get(min));
        }

        req.setAttribute("matches", matchesOnPage);
        req.setAttribute("pages", pages);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }



}
