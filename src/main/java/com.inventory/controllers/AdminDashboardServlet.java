package com.inventory.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Dummy values (later connect DAO)
        int totalItems = 120;
        int totalSuppliers = 18;
        int pendingOrders = 7;

        request.setAttribute("totalItems", totalItems);
        request.setAttribute("totalSuppliers", totalSuppliers);
        request.setAttribute("pendingOrders", pendingOrders);

        request.getRequestDispatcher("/admin/dashboard.jsp")
               .forward(request, response);
    }
}
