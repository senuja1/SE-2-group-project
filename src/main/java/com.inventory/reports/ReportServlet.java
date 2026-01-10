package com.inventory.reports;

import com.inventory.dao.ReportDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ReportDAO dao = new ReportDAO();
        req.setAttribute("items", dao.getAllItems());
        req.getRequestDispatcher("reports/reports.jsp").forward(req, res);
    }
}