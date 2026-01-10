package com.inventory.reports;
@WebServlet("/low-stock")
public class LowStockReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        ReportDAO dao = new ReportDAO();
        req.setAttribute("items", dao.getLowStockItems());
        req.getRequestDispatcher("reports/lowStock.jsp").forward(req, res);
    }
}