/**
 * AUTHENTICATION MODULE
 * * DESIGN PATTERN: STATE PERSISTENCE
 * This script manages the user session state using the Browser's LocalStorage API.
 * It ensures that the application maintains "Memory" of the user's login status 
 * across different page views.
 */

/**
 * FUNCTION: login()
 * PURPOSE: Handles the event-driven user authentication flow.
 * LOGIC: 
 * 1. Data Retrieval: Accesses DOM elements to capture user input.
 * 2. Input Sanitization: Uses .trim() to ensure no leading/trailing whitespace bypasses validation.
 * 3. Validation: Performs a check for null or empty strings (Guard Clause).
 */
function login() {
    // Accessing the Document Object Model (DOM) to retrieve user-entered values
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    // GUARD CLAUSE: Prevents unnecessary processing if fields are empty
    if (username === "" || password === "") {
        alert("Username and password are required");
        return; // Early return to stop execution
    }

    /**
     * CONDITIONAL AUTHENTICATION LOGIC:
     * Compares inputs against authorized administrative credentials.
     * Note: In a production environment, this would be validated via a secure API.
     */
    if (username === "admin" && password === "admin") {
        // PERSISTENCE: Storing the 'auth' flag in the browser's local storage
        localStorage.setItem("auth", "true");
        
        // NAVIGATION: Redirecting the user to the secure dashboard view
        window.location.href = "/dashboard.html";
    } else {
        // ERROR HANDLING: Feedback for unauthorized access attempts
        alert("Invalid username or password");
    }
}

/**
 * FUNCTION: checkAuth()
 * PURPOSE: Middleware-style security check.
 * USAGE: Executed on page load to verify if a user is currently authenticated.
 * PRINCIPLE: ACCESS CONTROL
 */
function checkAuth() {
    // Verifies if the 'auth' key exists and is set to true
    if (localStorage.getItem("auth") !== "true") {
        // If not authenticated, force the user back to the login gateway
        window.location.href = "/login.html";
    }
}

/**
 * FUNCTION: logout()
 * PURPOSE: Session Termination.
 * LOGIC: Clears session state from LocalStorage and invalidates the current access token.
 */
function logout() {
    // Removes the authentication flag, effectively logging the user out
    localStorage.removeItem("auth");
    
    // Redirect to login page to prevent access to cached views
    window.location.href = "/login.html";
}
