function login() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (username === "" || password === "") {
        alert("Username and password are required");
        return;
    }

    if (username === "admin" && password === "admin") {
        localStorage.setItem("auth", "true");
        window.location.href = "/dashboard.html";
    } else {
        alert("Invalid username or password");
    }
}


function checkAuth() {
    if (localStorage.getItem("auth") !== "true") {
        window.location.href = "/login.html";
    }
}


function logout() {
    localStorage.removeItem("auth");
    window.location.href = "/login.html";
}
