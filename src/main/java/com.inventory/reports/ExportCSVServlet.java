package com.inventory.reports;
@WebServlet("/export-csv")
public class ExportCSVServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        ReportDAO dao = new ReportDAO();
        res.setContentType("text/csv");
        res.setHeader("Content-Disposition", "attachment; filename=inventory.csv");

        PrintWriter out = res.getWriter();
        out.println("ID,Name,Category,Quantity,Price");

        dao.getAllItems().forEach(i -> {
            out.println(i.getItemId()+","+i.getName()+","+i.getCategory()+","+i.getQuantity()+","+i.getPrice());
        });
    }
}
