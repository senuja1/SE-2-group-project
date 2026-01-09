package com.inventory.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private static final String ADMIN_PASSWORD = "admin123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String password = request.getParameter("password");

        // ✅ correct password → dashboard
        if (ADMIN_PASSWORD.equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("admin", true);

            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        // ❌ wrong password → back to login (NO servlet URL in browser)
        request.setAttribute("error", "Invalid admin password");
        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    // safety: block direct access
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
