<h2>Add New Item</h2>
<form action="items" method="post">
    <input type="hidden" name="action" value="add"/>
    SKU: <input type="text" name="sku"/><br/>
    Name: <input type="text" name="name"/><br/>
    Category: <input type="text" name="category"/><br/>
    Quantity: <input type="number" name="quantity"/><br/>
    Price: <input type="number" step="0.01" name="price"/><br/>
    Reorder Level: <input type="number" name="reorder_level"/><br/>
    Supplier ID: <input type="number" name="supplier_id"/><br/>
    <button type="submit">Add Item</button>
</form>
