package com.inventory.controllers;

import com.inventory.dao.ItemDAO;
import com.inventory.models.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {

    private final ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Item> items = itemDAO.findAll();
            request.setAttribute("items", items);
            request.getRequestDispatcher("/admin/manageItems.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}