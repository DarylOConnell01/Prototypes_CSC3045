# Import necessary libraries (pre-built code we can use)
from flask import Flask, request, jsonify  # Flask helps create web services
from flask_cors import CORS  # CORS allows web browsers to access our service
from enum import Enum  # Enum helps create a list of fixed choices

# Create a new web application
app = Flask(__name__)

# Enable CORS so web browsers can talk to our service
CORS(app) 

# Create an enumeration (list of fixed choices) for IP types
# This ensures we only use "IPv4" or "IPv6" as valid types
class IpType(Enum):
    IPV4 = "IPv4"  # Represents IPv4 addresses (like 192.168.1.1)
    IPV6 = "IPv6"  # Represents IPv6 addresses (like 2001:db8::1)

# Define a class that can classify IP addresses
class ClassifyIP:
    # This function determines if an IP is IPv4, IPv6, or invalid
    def get_ip_type(self, ip):
        # If no IP was provided (empty or None), return nothing
        if not ip:
            return None

        # Check for IPv4 format (contains dots)
        if "." in ip:
            # Split the IP by dots (e.g., "192.168.1.1" becomes ["192", "168", "1", "1"])
            parts = ip.split(".")
            # IPv4 must have exactly 4 parts
            if len(parts) == 4:
                return IpType.IPV4  # Return IPv4 type
    
        # Check for IPv6 format (contains colons)
        elif ":" in ip:
            # Split the IP by colons
            parts = ip.split(":")
            
            # IPv6 can have 2-8 parts (depending on compression)
            if len(parts) <= 8 and len(parts) >= 2:
                return IpType.IPV6  # Return IPv6 type
                
        # If it doesn't match IPv4 or IPv6 format, return nothing
        return None

# --- WEB ENDPOINT SECTION ---
# Create a web endpoint that responds to HTTP requests
# When someone visits "/classify", this function runs
@app.route('/classify', methods=['GET'])
def classify_ip():
    # Get the IP address from the web request parameters
    ip_address = request.args.get('ip')
    # Create an instance of our classifier
    classifier = ClassifyIP()
    # Use the classifier to determine the IP type
    result = classifier.get_ip_type(ip_address)
    

    # Send back different responses based on what we found
    if result == IpType.IPV4:
        # Return JSON response saying it's IPv4, with success code 200
        return {"type": "IPv4"}, 200
    elif result == IpType.IPV6:
        # Return JSON response saying it's IPv6, with success code 200
        return {"type": "IPv6"}, 200
    

    # If it's not IPv4 or IPv6, it's invalid
    return {"type": "Invalid"}, 200

# --- SERVER STARTUP SECTION ---
# This runs only if this file is executed directly (not imported)
if __name__ == '__main__':
    # Start the web server on all network interfaces, port 5001, with debugging enabled
    app.run(host='0.0.0.0', port=5001, debug=True)