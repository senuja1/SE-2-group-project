<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Application Error</title>
</head>
<body>

<h2>Something went wrong</h2>

<p>
    <% if (exception != null) { %>
    <%= exception.getMessage() %>
    <% } else { %>
    Unexpected error occurred. Please try again later.
    <% } %>
</p>

<a href="index.jsp">Return to Home</a>

</body>
</html>