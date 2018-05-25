package com.business.retail.demo.service;

import org.junit.Test;

import java.math.*;

import static org.junit.Assert.*;

public class CategoryCTaxTest {

    private Tax tax = new CategoryCTax();

    @Test
    public void calculateTax () {
        BigDecimal taxOfTwo = tax.calculateTax(2, new BigDecimal(100.50));
        assertEquals(taxOfTwo,BigDecimal.ZERO);
    }
}