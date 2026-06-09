package com.healthcare.servlet;

import com.healthcare.dao.DiseaseDAO;
import com.healthcare.db.DatabaseInitializer;
import com.healthcare.model.Disease;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class DiseaseServlet extends HttpServlet {

    private DiseaseDAO diseaseDAO;

    @Override
    public void init() throws ServletException {
        DatabaseInitializer.initialize();
        diseaseDAO = new DiseaseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "view":
                String idParam = req.getParameter("id");
                if (idParam != null) {
                    Disease d = diseaseDAO.getDiseaseById(Integer.parseInt(idParam));
                    req.setAttribute("disease", d);
                    req.getRequestDispatcher("/WEB-INF/views/disease-detail.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/disease");
                }
                break;
            case "list":
            default:
                List<Disease> diseases = diseaseDAO.getAllDiseases();
                req.setAttribute("diseases", diseases);
                req.getRequestDispatcher("/WEB-INF/views/disease-list.jsp").forward(req, resp);
                break;
        }
    }
}
