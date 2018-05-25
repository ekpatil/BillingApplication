package com.business.retail.demo.service;

import org.junit.*;

import java.math.*;

import static java.math.BigDecimal.ROUND_CEILING;
import static org.junit.Assert.assertEquals;

public class CategoryATaxTest {

    private Tax tax = new CategoryATax();

    @Test
    public void calculateTax () {
        BigDecimal taxOfTwo = tax.calculateTax(2, new BigDecimal(100.50));
        assertEquals(taxOfTwo,new BigDecimal("20.10").setScale(2,RoundingMode.HALF_EVEN));
    }

}