<?php
// --- FIX: THESE HEADERS MUST BE AT THE VERY TOP ---
header("Access-Control-Allow-Origin: *"); // Allows ANY website to talk to this script
header("Access-Control-Allow-Methods: GET, POST, OPTIONS");
header("Content-Type: application/json");

require_once 'CountryLocator.php';

// Get the IP from the URL
$ipAddress = $_GET['ip'] ?? '';

// Run the logic
$locator = new CountryLocator();
$country = $locator->findCountry($ipAddress);

// Return JSON
echo json_encode([
    "ip" => $ipAddress,
    "country" => $country
]);
?>
