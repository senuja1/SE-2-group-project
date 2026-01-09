<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);

    // If admin already logged in → go to dashboard
    if (sessionObj != null && Boolean.TRUE.equals(sessionObj.getAttribute("admin"))) {
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        return;
    }

    // Otherwise → go to login page
    response.sendRedirect(request.getContextPath() + "/login.jsp");
%>
