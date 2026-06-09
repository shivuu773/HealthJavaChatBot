package com.healthcare.servlet;

import com.healthcare.db.DatabaseInitializer;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class IndexServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DatabaseInitializer.initialize();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
