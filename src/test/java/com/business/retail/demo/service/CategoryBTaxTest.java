package com.business.retail.demo.service;

import org.junit.Test;

import java.math.*;

import static org.junit.Assert.*;

public class CategoryBTaxTest {

    private Tax tax = new CategoryBTax();

    @Test
    public void calculateTax () {
        BigDecimal taxOfTwo = tax.calculateTax(2, new BigDecimal(100.50));
        assertEquals(taxOfTwo,new BigDecimal("40.20").setScale(2,RoundingMode.HALF_EVEN));
    }

}