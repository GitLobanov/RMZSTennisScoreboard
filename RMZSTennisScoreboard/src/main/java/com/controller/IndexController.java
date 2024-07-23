package com.controller;


import com.model.Match;
import com.service.MatchService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = {"/"})
public class IndexController extends HttpServlet {

    MatchService matchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        matchService = (MatchService) config.getServletContext().getAttribute("matchService");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List list = matchService.getAll(new Match());

        req.setAttribute("matches", list);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
