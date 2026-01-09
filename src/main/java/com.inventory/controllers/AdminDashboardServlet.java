@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("totalItems", 120);
        request.setAttribute("totalSuppliers", 18);
        request.setAttribute("pendingOrders", 7);

        request.getRequestDispatcher("/admin/dashboard.jsp")
                .forward(request, response);
    }
}
