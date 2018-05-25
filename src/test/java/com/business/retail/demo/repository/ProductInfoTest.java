package com.business.retail.demo.repository;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ProductInfoTest {


    @Test
    // test to get correct product info from valid product code
    public void getProductByCode(){
        Optional<ProductInfo> heroPen = ProductInfo.getProductByCode("H_PEN");
        assertTrue(heroPen.isPresent());
        assertTrue(heroPen.get()== ProductInfo.HERO_PEN);
    }

    @Test
    // test to get empty optional for invalid product code
    public void getProductByCodeForInvalidProductCode(){
        Optional<ProductInfo> heroPen = ProductInfo.getProductByCode("H_PEN_1");
        assertFalse(heroPen.isPresent());
    }

}