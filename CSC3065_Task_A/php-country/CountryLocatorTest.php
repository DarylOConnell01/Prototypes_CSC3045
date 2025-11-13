<?php

require_once 'CountryLocator.php';

class CountryLocatorTest
{
    private $countryLocator;
    private $passed = 0;
    private $failed = 0;

    public function __construct()
    {
        $this->countryLocator = new CountryLocator();
    }

    public function assertEquals($expected, $actual, $message = '')
    {
        if ($expected === $actual) {
            $this->passed++;
            echo "✓ PASS: $message\n";
        } else {
            $this->failed++;
            echo "✗ FAIL: $message - Expected '$expected', got '$actual'\n";
        }
    }

    public function testUSCountry()
    {
        $result = $this->countryLocator->findCountry("100.1.1.1");
        $this->assertEquals("US", $result, "IP starting with 100 should return US");
    }

    public function testGBCountry()
    {
        $result = $this->countryLocator->findCountry("101.2.3.4");
        $this->assertEquals("GB", $result, "IP starting with 101 should return GB");
    }

    public function testChinaCountry()
    {
        $result = $this->countryLocator->findCountry("102.5.6.7");
        $this->assertEquals("China", $result, "IP starting with 102 should return China");
    }

    public function testUnknownCountry()
    {
        $result = $this->countryLocator->findCountry("192.168.1.1");
        $this->assertEquals("Unknown", $result, "IP starting with 192 should return Unknown");
    }

    public function testEdgeCases()
    {
        $result = $this->countryLocator->findCountry("10");
        $this->assertEquals("Unknown", $result, "Short IP should return Unknown");

        $result = $this->countryLocator->findCountry("999.999.999.999");
        $this->assertEquals("Unknown", $result, "Invalid IP should return Unknown");
    }

    public function runAllTests()
    {
        echo "Running PHP Country Locator Tests...\n\n";
        
        $this->testUSCountry();
        $this->testGBCountry();
        $this->testChinaCountry();
        $this->testUnknownCountry();
        $this->testEdgeCases();
        
        echo "\n--- Test Results ---\n";
        echo "Passed: {$this->passed}\n";
        echo "Failed: {$this->failed}\n";
        echo "Total: " . ($this->passed + $this->failed) . "\n";
        
        return $this->failed === 0;
    }
}

// Run tests if called directly
if (basename(__FILE__) === basename($_SERVER['SCRIPT_NAME'])) {
    $test = new CountryLocatorTest();
    $success = $test->runAllTests();
    exit($success ? 0 : 1);
}

?>