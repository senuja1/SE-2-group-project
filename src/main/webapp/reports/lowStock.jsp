<h2>Low Stock Items</h2>
<table border="1">
<tr><th>Name</th><th>Qty</th></tr>
<c:forEach var="i" items="${items}">
<tr>
<td>${i.name}</td>
<td>${i.quantity}</td>
</tr>
</c:forEach>
</table>
