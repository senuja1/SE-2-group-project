function loadDashboard() {

    
    fetch("/api/products/count")
        .then(r => r.text())
        .then(c => document.getElementById("totalProducts").innerText = c);

    fetch("/api/brands/count")
        .then(r => r.text())
        .then(c => document.getElementById("totalBrands").innerText = c);

    fetch("/api/categories/count")
        .then(r => r.text())
        .then(c => document.getElementById("totalCategories").innerText = c);

    document.getElementById("totalOrders").innerText = 0;


   
    fetch("/api/products/brand-summary")
        .then(r => r.json())
        .then(data => {

            const labels = data.map(d => d.brand);
            const values = data.map(d => d.count);

            new Chart(document.getElementById("salesChart"), {
                type: "pie",
                data: {
                    labels: labels,
                    datasets: [{
                        data: values,
                        backgroundColor: [
                            "#3b82f6",
                            "#f97316",
                            "#10b981",
                            "#ef4444",
                            "#6366f1",
                            "#22c55e"
                        ]
                    }]
                }
            });
        });



    fetch("/api/products/category-summary")
        .then(r => r.json())
        .then(data => {


            const labels = data.map(d => d.category);
            const values = data.map(d => d.count);

            new Chart(document.getElementById("categoryChart"), {
                type: "pie",
                data: {
                    labels: labels,
                    datasets: [{
                        data: values,
                        backgroundColor: [
                            "#22c55e",  
                            "#3b82f6",  
                            "#f59e0b",  
                            "#ef4444",
                            "#8b5cf6",
                            "#14b8a6"
                        ]
                    }]
                }
            });
        });
}