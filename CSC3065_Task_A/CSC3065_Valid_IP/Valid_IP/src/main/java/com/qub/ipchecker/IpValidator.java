
package com.qub.ipchecker;


import org.springframework.stereotype.Component;

// @Component tells Spring to manage this class automatically
@Component
public class IpValidator {

   
    public boolean isValidIp(String ip) {
        //if no IP was provided or it's just spaces
        if (ip == null || ip.trim().isEmpty()) {
            return false;  // Return false (invalid)
        }
        
        // Check if it's either a valid IPv4 OR a valid IPv6
        return isValidIpV4(ip) || isValidIpV6(ip);
    }

    // This function specifically checks IPv4 addresses
    public boolean isValidIpV4(String ip) {
        // Split the IP address by dots into separate parts
        String[] parts = ip.split("\\.");
        // IPv4 must have exactly 4 parts
        return parts.length == 4;
    }

    //checks IPv6 addresses 
    public boolean isValidIpV6(String ip) {
        // First check: if no IP was provided or it's just spaces
        if (ip == null || ip.trim().isEmpty()) {
            return false;  // Return false (invalid)
        }
        
        // Split the IP address by colons into separate parts
        String[] parts = ip.split(":");
        
        // IPv6 can have 2 to 8 groups separated by colons
        return parts.length >= 2 && parts.length <= 8;
    }

}
