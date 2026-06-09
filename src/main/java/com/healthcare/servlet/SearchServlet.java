package com.healthcare.servlet;

import com.healthcare.dao.DiseaseDAO;
import com.healthcare.model.Disease;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class SearchServlet extends HttpServlet {

    private DiseaseDAO diseaseDAO;

    @Override
    public void init() throws ServletException {
        diseaseDAO = new DiseaseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String query = req.getParameter("q");
        String symptom = req.getParameter("symptom");

        if (symptom != null && !symptom.trim().isEmpty()) {
            List<Disease> results = diseaseDAO.getDiseasesBySymptom(symptom.trim());
            req.setAttribute("results", results);
            req.setAttribute("searchType", "symptom");
            req.setAttribute("query", symptom);
        } else if (query != null && !query.trim().isEmpty()) {
            List<Disease> results = diseaseDAO.searchDiseases(query.trim());
            req.setAttribute("results", results);
            req.setAttribute("searchType", "general");
            req.setAttribute("query", query);
        } else {
            req.setAttribute("results", diseaseDAO.getAllDiseases());
            req.setAttribute("searchType", "all");
        }

        req.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(req, resp);
    }
}
