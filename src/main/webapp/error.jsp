<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <title>Something went wrong</title>

    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: "Segoe UI", Arial, sans-serif;
            background: linear-gradient(135deg, #232526, #414345);
            color: white;
        }

        .error-box {
            background: #121821;
            padding: 40px;
            border-radius: 14px;
            width: 420px;
            text-align: center;
            box-shadow: 0 12px 35px rgba(0,0,0,0.5);
        }

        h1 {
            margin-bottom: 10px;
            color: #ff6b6b;
        }

        p {
            color: #cbd5e1;
            font-size: 14px;
            margin-bottom: 25px;
        }

        a {
            display: inline-block;
            padding: 12px 20px;
            background: #ff4b4b;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            font-size: 14px;
        }

        a:hover {
            background: #ff2e2e;
        }
    </style>
</head>

<body>

<div class="error-box">
    <h1>🚨 Oops!</h1>

    <p>
        Something went wrong while processing your request.<br>
        Don’t worry — it’s not your fault.
    </p>

    <%-- Optional: show error message if available --%>
    <% if (exception != null) { %>
    <p style="color:#94a3b8; font-size:12px;">
        Error: <%= exception.getMessage() %>
    </p>
    <% } %>

    <a href="<%= request.getContextPath() %>/index.jsp">Go Home</a>
</div>

</body>
</html>
