package com.inventory.controllers;

// Importing necessary DAO, Model, and Utility classes (Part of OOP Encapsulation)
import com.inventory.dao.UserDAO;
import com.inventory.models.User;
import com.inventory.utils.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// @WebServlet is an Annotation that maps this class to the "/login" URL path
@WebServlet("/login")
public class AuthServlet extends HttpServlet {

    // Dependency Injection/Composition: Using the UserDAO to handle database operations
    private final UserDAO userDAO = new UserDAO();

    /**
     * doPost handles the Login logic (Security best practice: use POST for passwords)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. Data Retrieval: Getting user input from the HTML login form
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // 2. Database Lookup: Calling the DAO to find a user by their unique username
            User user = userDAO.findByUsername(username);

            // 3. Logic/Authentication: 
            // Check if user exists AND if the hashed password matches (Security: never store plain text)
            if (user != null && PasswordUtil.verify(password, user.getPasswordHash())) {

                // 4. Session Management: Create a new session for the user
                // req.getSession(true) creates a session if one doesn't exist
                HttpSession session = req.getSession(true);
                
                // Store the User object in the session to "remember" the user across pages
                session.setAttribute("user", user);

                // 5. Authorization (Role-based access control):
                // Check the user's role to determine where to redirect them
                if ("ADMIN".equals(user.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/staff/dashboard.jsp");
                }
                return; // Stop further execution after a successful redirect
            }

        } catch (Exception e) {
            // Error Handling: Print errors to the server logs for debugging
            e.printStackTrace();
        }

        // 6. Failure Logic: If login fails, send the user back to the login page with an error message
        req.setAttribute("error", "Invalid username or password");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    /**
     * doGet handles the Logout logic (Usually triggered by a link or direct URL access)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 1. Session Invalidation: Get the existing session and destroy it (Logout)
        HttpSession session = req.getSession(false); // 'false' means don't create a new one
        if (session != null) {
            session.invalidate(); // Clears all session data
        }

        // 2. Redirection: Send the user back to the login screen
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}