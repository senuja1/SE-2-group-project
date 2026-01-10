package com.inventory.reports;
@WebServlet("/export-pdf")
public class ExportPDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("application/pdf");
        res.setHeader("Content-Disposition", "attachment; filename=inventory.pdf");

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, res.getOutputStream());
            doc.open();
            doc.add(new Paragraph("Inventory Report"));

            ReportDAO dao = new ReportDAO();
            for (ReportItem i : dao.getAllItems()) {
                doc.add(new Paragraph(
                    i.getItemId()+" | "+i.getName()+" | "+i.getCategory()+" | "+i.getQuantity()+" | "+i.getPrice()
                ));
            }
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}