// Import required libraries (pre-built code we can use)
const express = require('express');  // Express helps create web servers
const cors = require('cors');        // CORS allows web browsers to access our service

// Create a new web application using Express
const app = express();
// Set the port number our server will listen on
const port = 4000;

// Enable CORS so web browsers can talk to our service from other websites
app.use(cors());

// Define a list of IP addresses that are considered "bad"
// These are the specific IPs that our service should flag as problematic
const BAD_IPS = [
    "100.200.300.400",  // Bad IP #1
    "101.201.301.401",  // Bad IP #2  
    "102.202.302.402",  // Bad IP #3
    "103.203.303.403"   // Bad IP #4
]; 

// Function to check if an IPv4 address has the correct format
// IPv4 addresses look like: 192.168.1.1 (4 numbers separated by dots)
const isIPv4FormatValid = (ip) => {
    // Split the IP address by dots to get individual parts
    const parts = ip.split('.');
    
    // IPv4 must have exactly 4 parts (4 numbers)
    if (parts.length !== 4) return false;

    // Check each part to make sure it's a valid number between 0-255
    return parts.every(part => {
        // Convert the text to a number
        const num = parseInt(part, 10);
        // Check if it's a real number and within valid range
        return !isNaN(num) && num >= 0 && num <= 255;
    });
};

// Function to check if an IPv6 address has the correct format
// IPv6 addresses look like: 2001:db8::1 (hex numbers separated by colons)
const isIPv6FormatValid = (ip) => {
    // First check: make sure we have a valid string
    if (!ip || typeof ip !== 'string') return false;
    
    // Handle IPv6 compressed notation (:: means missing zeros)
    if (ip.includes('::')) {
        // Count how many :: sections exist (should be only one)
        const doubleColonCount = (ip.match(/::/g) || []).length;
        if (doubleColonCount > 1) return false;  // Invalid if more than one
        
        // Split the IP at the :: to analyze parts
        const segments = ip.split('::');
        if (segments.length > 2) return false;  // Should only split into 2 parts max
        
        // Count total number of parts in the IP
        let totalParts = 0;
        for (const segment of segments) {
            if (segment) {  // If this segment isn't empty
                // Split by colons and add to our count
                const parts = segment.split(':');
                totalParts += parts.length;
            }
        }
        
        // IPv6 can have at most 8 parts
        return totalParts <= 8;
    } else {
        // Full IPv6 format (no compression, all parts shown)
        const parts = ip.split(':');
        // Full IPv6 must have exactly 8 parts
        return parts.length === 8;
    }
};

// Master function that checks if an IP is valid (either IPv4 or IPv6)
const isValidIP = (ip) => {
    // Return true if it's valid IPv4 OR valid IPv6
    return isIPv4FormatValid(ip) || isIPv6FormatValid(ip);
};

// Define the main web endpoint that handles requests
// When someone visits "/check-bad", this function runs
app.get('/check-bad', (req, res) => {
    // Get the IP address from the web request and remove extra spaces
    const ip = req.query.ip ? req.query.ip.trim() : "";

    // First check: make sure an IP was actually provided
    if (!ip) {
        // Send back an error response if no IP was given
        return res.status(400).json({ ip: "", result: "Error: No IP provided" });
    }

    // Second check: see if this IP is in our "bad" list
    if (BAD_IPS.includes(ip)) {
        // Send back response saying it's a bad IP
        return res.json({ ip: ip, result: "Bad IP" });
    }

    // Third check: make sure the IP format is valid (IPv4 or IPv6)
    if (!isValidIP(ip)) {
        // Send back response saying the format is invalid
        return res.json({ ip: ip, result: "Invalid Format" });
    }

    // If we get here, the IP passed all checks - it's good!
    return res.json({ ip: ip, result: "Good IP" });
}); 

// Only start the server if this file is run directly (not imported)
if(require.main === module){
    // Start listening for web requests on our chosen port
    app.listen(port, ()=>{
        // Print a message to show the server started successfully
        console.log(`Node.js Bad-IP Service running on http://0.0.0.0:${port}`);
    });
}

// Export the app so other code can import and test it
module.exports = app;