<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>

    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: "Segoe UI", Arial, sans-serif;
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
        }

        .login-card {
            width: 380px;
            background: #121821;
            border-radius: 14px;
            padding: 40px;
            box-shadow: 0 12px 35px rgba(0,0,0,0.45);
            text-align: center;
            color: #fff;
        }

        .login-card h2 {
            margin-bottom: 8px;
        }

        .login-card p {
            margin-bottom: 25px;
            color: #9aa4b2;
            font-size: 14px;
        }

        input[type="password"] {
            width: 100%;
            padding: 13px;
            border-radius: 8px;
            border: none;
            outline: none;
            background: #1e2633;
            color: #fff;
            font-size: 15px;
            margin-bottom: 18px;
        }

        button {
            width: 100%;
            padding: 13px;
            border-radius: 8px;
            border: none;
            background: #ff4b4b;
            color: white;
            font-size: 16px;
            cursor: pointer;
            transition: 0.2s;
        }

        button:hover {
            background: #ff2e2e;
        }

        .error {
            margin-top: 15px;
            color: #ff6b6b;
            font-size: 14px;
        }
    </style>
</head>

<body>

<div class="login-card">
    <h2>Admin Access</h2>
    <p>Authorized users only</p>


    <form action="<%= request.getContextPath() %>/admin/login" method="post">
        <input
                type="password"
                name="password"
                placeholder="Enter admin password"
                required
        />
        <button type="submit">Login</button>
    </form>

    <% if (request.getParameter("error") != null) { %>
    <div class="error"> Invalid admin password</div>
    <% } %>
</div>

</body>
</html>
