function loadBrands() {
    fetch("/api/brands")
        .then(response => response.json())
        .then(data => {
            let rows = "";

            data.forEach(b => {
                rows += `
                    <tr>
                        <td>${b.id}</td>
                        <td>${b.name}</td>
                    </tr>
                `;
            });

            document.getElementById("brandTable").innerHTML = rows;
        })
        .catch(error => {
            console.error("Error loading brands:", error);
        });
}

function addBrand() {
    const brandNameInput = document.getElementById("brandName");
    const name = brandNameInput.value.trim();

    if (name === "") {
        alert("Brand name required");
        return;
    }

    fetch("/api/brands", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name: name })
    })
        .then(() => {
            brandNameInput.value = "";
            loadBrands();   // refresh table with DB data
        })
        .catch(error => {
            console.error("Error adding brand:", error);
        });
}
