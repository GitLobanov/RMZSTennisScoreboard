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

        List<Match> matchList = finishedMatchesPersistenceService.findAll();
        req.setAttribute("matches", matchList);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }



}
