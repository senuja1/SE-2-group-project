package com.inventory.controllers;

import com.inventory.dao.UserDAO;
import com.inventory.models.User;
import com.inventory.utils.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = userDAO.findByUsername(username);

            if (user != null && PasswordUtil.verify(password, user.getPasswordHash())) {

                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);

                if ("ADMIN".equals(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/staff/dashboard.jsp");
                }
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("error", "Invalid username or password");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();

        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
