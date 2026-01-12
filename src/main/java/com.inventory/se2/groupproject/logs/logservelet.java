package com.se2.groupproject.logs;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/logs")
public class LogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Log> logs = LogDAO.getAllLogs();
        req.setAttribute("logs", logs);
        req.getRequestDispatcher("/admin/logs.jsp").forward(req, resp);
    }
}