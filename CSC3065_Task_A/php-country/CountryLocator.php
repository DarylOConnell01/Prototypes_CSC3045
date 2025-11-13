<?php
// This tells the server that this file contains PHP code

// Define a new class called CountryLocator
// A class is like a blueprint for creating objects that do specific tasks
class CountryLocator
{
    // This is a public function, meaning other code can call it
    // It takes one input parameter: an IP address as text
    public function findCountry($ip)
    {
        // Extract the first 3 characters from the IP address
        // For example, if IP is "100.1.1.1", this gets "100"
        // substr() is a built-in function that cuts out part of a text string
        $first3char = substr($ip, 0, 3); 

        // Check if the first 3 characters match specific patterns
        // Each pattern corresponds to a different country
        
        // If IP starts with "100", it's from the United States
        if($first3char == "100"){
            return "US";  // Send back "US" as the result
        } 
        // If IP starts with "101", it's from Great Britain
        elseif ($first3char == "101"){
            return "GB";  // Send back "GB" as the result
        } 
        // If IP starts with "102", it's from China
        elseif ($first3char == "102"){
            return "China";  // Send back "China" as the result
        } 
        // For any other pattern, we don't know the country
        else {
            return "Unknown";  // Send back "Unknown" as the result
        }
    }
}

// This closes the PHP code section
?>
