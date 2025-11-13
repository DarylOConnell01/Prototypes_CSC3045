// Define which group/package this code belongs to (like organizing files in folders)
package com.qub.ipchecker;

// Import statements - these bring in pre-built code we can use
import org.springframework.beans.factory.annotation.Autowired;  // Helps Spring automatically connect components
import org.springframework.web.bind.annotation.GetMapping;      // Creates web endpoints that respond to GET requests
import org.springframework.web.bind.annotation.RequestParam;    // Gets parameters from web requests
import org.springframework.web.bind.annotation.RestController;  // Marks this class as a web service controller
import org.springframework.web.bind.annotation.CrossOrigin;     // Allows web browsers to access this service

import java.util.Arrays;  // Provides tools for working with arrays (lists of items)
import java.util.Map;     // Provides tools for creating key-value pairs (like dictionaries)

// @RestController tells Spring this class handles web requests and returns data (not web pages)
@RestController
// @CrossOrigin allows any website to call our service (removes browser security restrictions)
@CrossOrigin(origins = "*")
public class IPCheckController {

    // @Autowired tells Spring to automatically provide us with an IpValidator object
    // This is called "dependency injection" - Spring manages creating and connecting objects for us
    @Autowired
    private IpValidator ipValidator;  // This object will validate IP addresses for us

    // Helper method to split a text string into individual IP addresses
    // Takes a string like "1.2.3.4,5.6.7.8" and returns ["1.2.3.4", "5.6.7.8"]
    private String[] getIpList(String items) {
        // split(",") breaks the text apart wherever it finds a comma
        return items.split(",");
    }

    // @GetMapping creates a web endpoint that responds to GET requests at "/api/valid"
    // When someone visits http://localhost:8081/api/valid?items=1.2.3.4,5.6.7.8 this method runs
    @GetMapping("/api/valid")
    public Map<String, Integer> getTotalValidIPs(@RequestParam String items) {
        
        // Convert the comma-separated IP string into an array of individual IPs
        String[] ipArray = getIpList(items);

        // Use Java Streams to process the array efficiently:
        // 1. Arrays.stream(ipArray) - converts array to a "stream" for processing
        // 2. .filter() - keeps only IPs that pass the validation test
        // 3. .count() - counts how many valid IPs remain after filtering
        long count = Arrays.stream(ipArray)
                           .filter(ip -> ipValidator.isValidIp(ip))  // Test each IP with our validator
                           .count();  // Count the valid ones

        // Map.of() creates a response object with key-value pairs
        // This returns JSON like: {"total_valid_ips": 3}
        // We convert the long number to int because that's what our API expects
        return Map.of("total_valid_ips", (int) count);
    }

    
}
