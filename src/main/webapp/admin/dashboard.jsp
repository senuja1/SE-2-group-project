<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 20px;
        }
        h1 {
            margin-bottom: 20px;
        }
        .cards {
            display: flex;
            gap: 20px;
        }
        .card {
            background: white;
            padding: 20px;
            width: 200px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
            text-align: center;
        }
        .number {
            font-size: 32px;
            font-weight: bold;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<h1>Admin Dashboard</h1>

<div class="cards">
    <div class="card">
        <div>Total Items</div>
        <div class="number">
            <%= request.getAttribute("totalItems") %>
        </div>
    </div>

    <div class="card">
        <div>Total Suppliers</div>
        <div class="number">
            <%= request.getAttribute("totalSuppliers") %>
        </div>
    </div>

    <div class="card">
        <div>Pending Orders</div>
        <div class="number">
            <%= request.getAttribute("pendingOrders") %>
        </div>
    </div>
</div>

</body>
</html>
