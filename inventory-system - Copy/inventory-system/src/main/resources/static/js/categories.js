function loadCategories() {
    fetch("/api/categories")
        .then(r => r.json())
        .then(data => {
            let rows = "";
            data.forEach(c => {
                rows += `
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.name}</td>
                    </tr>
                `;
            });
            document.getElementById("categoryTable").innerHTML = rows;
        });
}

function addCategory() {
    const name = document.getElementById("categoryName").value.trim();
    if (!name) {
        alert("Category name required");
        return;
    }

    fetch("/api/categories", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name })
    }).then(() => {
        document.getElementById("categoryName").value = "";
        loadCategories();
    });
}
