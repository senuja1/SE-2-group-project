function loadCategories() {
    fetch("/api/categories")
        .then(res => res.json())
        .then(data => {
            let rows = "";

            if (data.length === 0) {
                rows = `
                    <tr>
                        <td colspan="3" style="text-align:center;color:#9ca3af;">
                            No categories found
                        </td>
                    </tr>
                `;
            } else {
                data.forEach(c => {
                    rows += `
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>
                                <button class="btn btn-danger"
                                    onclick="deleteCategory(${c.id})">
                                    Delete
                                </button>
                            </td>
                        </tr>
                    `;
                });
            }

            document.getElementById("categoryTable").innerHTML = rows;
        })
        .catch(() => {
            document.getElementById("categoryTable").innerHTML = `
                <tr>
                    <td colspan="3" style="text-align:center;color:red;">
                        Failed to load categories
                    </td>
                </tr>
            `;
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
    })
        .then(() => {
            document.getElementById("categoryName").value = "";
            loadCategories();
        });
}

function deleteCategory(id) {
    if (!confirm("Delete this category?")) return;

    fetch(`/api/categories/${id}`, {
        method: "DELETE"
    })
        .then(() => loadCategories());
}