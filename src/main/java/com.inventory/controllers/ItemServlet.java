package com.inventory.controllers;

import com.inventory.dao.ItemDAO;
import com.inventory.models.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/items")
public class ItemServlet extends HttpServlet {

    private final ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<Item> items = itemDAO.findAll();
            req.setAttribute("items", items);
            req.getRequestDispatcher("/admin/manageItems.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            switch (action) {
                case "add": addItem(req); break;
                case "update": updateItem(req); break;
                case "delete": deleteItem(req); break;
            }

            resp.sendRedirect(req.getContextPath() + "/admin/items");

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/admin/manageItems.jsp").forward(req, resp);
        }
    }

    private void addItem(HttpServletRequest req) throws Exception {
        Item i = extractItem(req);
        itemDAO.addItem(i);
    }

    private void updateItem(HttpServletRequest req) throws Exception {
        Item i = extractItem(req);
        i.setItemId(Integer.parseInt(req.getParameter("item_id")));
        itemDAO.updateItem(i);
    }

    private void deleteItem(HttpServletRequest req) throws Exception {
        int id = Integer.parseInt(req.getParameter("item_id"));
        itemDAO.deleteItem(id);
    }

    // Convert request → Item object
    private Item extractItem(HttpServletRequest req) {
        Item i = new Item();
        i.setSku(req.getParameter("sku"));
        i.setName(req.getParameter("name"));
        i.setCategory(req.getParameter("category"));
        i.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        i.setPrice(Double.parseDouble(req.getParameter("price")));
        i.setReorderLevel(Integer.parseInt(req.getParameter("reorder_level")));

        String supplierId = req.getParameter("supplier_id");
        if (supplierId != null && !supplierId.isEmpty())
            i.setSupplierId(Integer.parseInt(supplierId));

        return i;
    }
}