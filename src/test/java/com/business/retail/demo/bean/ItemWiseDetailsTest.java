package com.business.retail.demo.bean;

import com.business.retail.demo.repository.ProductInfo;
import org.junit.Test;

import java.math.*;

import static org.junit.Assert.*;


public class ItemWiseDetailsTest {

    private ItemWiseDetails itemWiseDetails =new ItemWiseDetails();


    @Test
    public void calculatePrice () {
        BigDecimal taxOfTwo = itemWiseDetails.calculatePrice(2, new BigDecimal(100.50));
        assertEquals(taxOfTwo,new BigDecimal("201.00").setScale(2,RoundingMode.HALF_EVEN));
    }

    @Test
    public void calculatePriceAndTax_CategoryA() {
        //given
        itemWiseDetails =new ItemWiseDetails(ProductInfo.PARK_PERFUME,2);
        //when
        itemWiseDetails.calculatePriceAndTax();
        // then
        assertEquals(itemWiseDetails.getTotalPrice(), new BigDecimal(447).setScale(2, RoundingMode.HALF_EVEN));
        assertEquals(itemWiseDetails.getTotalTax(), new BigDecimal(44.70).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void calculatePriceAndTax_CategoryB() {
        //given
        itemWiseDetails =new ItemWiseDetails(ProductInfo.ONE_PLUS_6T,2);
        //when
        itemWiseDetails.calculatePriceAndTax();
        // then
        assertEquals(itemWiseDetails.getTotalPrice(), new BigDecimal(69999.80).setScale(2, RoundingMode.HALF_EVEN));
        assertEquals(itemWiseDetails.getTotalTax(), new BigDecimal(13999.96).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void calculatePriceAndTax_CategoryC() {
        //given
        itemWiseDetails =new ItemWiseDetails(ProductInfo.HERO_PEN,2);
        //when
        itemWiseDetails.calculatePriceAndTax();
        // then
        assertEquals(itemWiseDetails.getTotalPrice(), new BigDecimal(101).setScale(2, RoundingMode.HALF_EVEN));
        assertEquals(itemWiseDetails.getTotalTax(), BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
    }

}