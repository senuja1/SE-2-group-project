<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.inventory.models.Report" %>

<!DOCTYPE html>
<html>
<head>
    <title>System Reports</title>
    <style>
        body {
            font-family: Segoe UI, sans-serif;
            background: #0f2027;
            color: white;
            padding: 40px;
        }
        .card {
            background: #1e293b;
            padding: 20px;
            border-radius: 12px;
            margin-bottom: 15px;
            box-shadow: 0 10px 25px rgba(0,0,0,.4);
        }
        h2 { margin-bottom: 25px; }
    </style>
</head>

<body>

<h2>📊 Inventory Reports</h2>

<%
    List<Report> reports = (List<Report>) request.getAttribute("reports");
    for (Report r : reports) {
%>
    <div class="card">
        <h3><%= r.getTitle() %></h3>
        <p>Total: <strong><%= r.getTotal() %></strong></p>
    </div>
<% } %>

</body>
</html>