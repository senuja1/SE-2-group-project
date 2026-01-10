<h2>Inventory Report</h2>
<a href="../export-pdf">Export PDF</a> |
<a href="../export-csv">Export CSV</a>

<table border="1">
<tr>
<th>ID</th><th>Name</th><th>Category</th><th>Qty</th><th>Price</th>
</tr>
<c:forEach var="i" items="${items}">
<tr>
<td>${i.itemId}</td>
<td>${i.name}</td>
<td>${i.category}</td>
<td>${i.quantity}</td>
<td>${i.price}</td>
</tr>
</c:forEach>
</table>