function loadBrands() {
    fetch("/api/brands")
        .then(res => res.json())
        .then(response => {

            console.log("Raw brand response:", response);


            const data = Array.isArray(response)
                ? response
                : response.data || [];

            const phoneBrand  = document.getElementById("phoneBrand");
            const laptopBrand = document.getElementById("laptopBrand");
            const pcBrand     = document.getElementById("pcBrand");

            if (!phoneBrand || !laptopBrand || !pcBrand) {
                console.error("Brand select not found in DOM");
                return;
            }

            let options = `<option value="">Select Brand</option>`;

            data.forEach(b => {
                const id   = b.id || b.brandId;
                const name = b.name || b.brandName;

                options += `<option value="${id}">${name}</option>`;
            });

            phoneBrand.innerHTML  = options;
            laptopBrand.innerHTML = options;
            pcBrand.innerHTML     = options;
        })
        .catch(err => {
            console.error("Failed to load brands", err);
        });
}



function saveProduct(category) {

    let nameEl, qtyEl, priceEl, brandEl;

    if (category === "Phone") {
        nameEl  = document.getElementById("phoneName");
        qtyEl   = document.getElementById("phoneQty");
        priceEl = document.getElementById("phonePrice");
        brandEl = document.getElementById("phoneBrand");
    }

    if (category === "Laptop") {
        nameEl  = document.getElementById("laptopName");
        qtyEl   = document.getElementById("laptopQty");
        priceEl = document.getElementById("laptopPrice");
        brandEl = document.getElementById("laptopBrand");
    }

    if (category === "Desktop PC") {
        nameEl  = document.getElementById("pcName");
        qtyEl   = document.getElementById("pcQty");
        priceEl = document.getElementById("pcPrice");
        brandEl = document.getElementById("pcBrand");
    }

    if (!nameEl.value || !qtyEl.value || !priceEl.value || !brandEl.value) {
        alert("Fill all fields");
        return;
    }

    fetch("/api/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: nameEl.value,
            quantity: qtyEl.value,
            price: priceEl.value,
            category: category,
            brandId: brandEl.value
        })
    })
        .then(() => {
            alert(category + " added successfully");


            nameEl.value = "";
            qtyEl.value = "";
            priceEl.value = "";
            brandEl.selectedIndex = 0;
        });
}