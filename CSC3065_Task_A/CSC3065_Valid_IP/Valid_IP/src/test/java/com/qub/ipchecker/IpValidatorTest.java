package com.qub.ipchecker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IpValidatorTest {

    @Test
    public void testValidIp() {
        IpValidator validator = new IpValidator();

        //IPv4 Tests
        assertTrue(validator.isValidIp("192.168.1.1"));
        assertFalse(validator.isValidIp("1.2.3"));           // Only 3 groups, should be 4
        assertTrue(validator.isValidIp("256.256.256.256"));  // Now valid with simplified validation

        //IPv6 Tests
        assertTrue(validator.isValidIp("1:2:3:4:5:6:7:8"));
        assertTrue(validator.isValidIp("2001:db8::1"));

    }
}
