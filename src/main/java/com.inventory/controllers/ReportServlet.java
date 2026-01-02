package com.inventory.controllers;

import com.inventory.dao.ReportDAO;
import com.inventory.models.SalesReport;
import com.inventory.models.StockReport;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String type = req.getParameter("type");

        if ("sales".equals(type)) {
            Date from = Date.valueOf(req.getParameter("from"));
            Date to = Date.valueOf(req.getParameter("to"));

            List<SalesReport> sales = ReportDAO.getSalesReport(from, to);
            req.setAttribute("sales", sales);
            req.getRequestDispatcher("admin/sales-report.jsp").forward(req, res);

        } else if ("stock".equals(type)) {
            List<StockReport> stock = ReportDAO.getStockReport();
            req.setAttribute("stock", stock);
            req.getRequestDispatcher("admin/stock-report.jsp").forward(req, res);

        } else if ("dashboard".equals(type)) {
            double revenue = ReportDAO.getTotalRevenue();
            req.setAttribute("revenue", revenue);
            req.getRequestDispatcher("admin/dashboard.jsp").forward(req, res);
        }
    }
}