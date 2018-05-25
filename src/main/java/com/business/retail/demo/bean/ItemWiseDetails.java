package com.business.retail.demo.bean;

import com.business.retail.demo.repository.ProductInfo;
import com.business.retail.demo.service.*;

import java.math.*;
import java.util.*;

public class ItemWiseDetails implements BasicPrice {

    private ProductInfo product;
    private Integer purchasedQuantity;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private BigDecimal totalTax = BigDecimal.ZERO;


    private static Map<String, Tax> taxStrategies = new HashMap<>();
    //Strategy pattern
    static {
        taxStrategies.put("categoryA", new CategoryATax());
        taxStrategies.put("categoryB", new CategoryBTax());
        taxStrategies.put("categoryC", new CategoryCTax());
    }


    public ItemWiseDetails () {
    }

    public ItemWiseDetails (ProductInfo product, Integer purchasedQuantity) {
        this.product = product;
        this.purchasedQuantity = purchasedQuantity;
    }

    public void calculatePriceAndTax() {
        totalPrice = calculatePrice(purchasedQuantity, product.getPrice());
        totalTax = taxStrategies.get(product.getTaxCategory()).calculateTax(purchasedQuantity, product.getPrice());
    }

    public BigDecimal getTotalPrice () {
        return totalPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getTotalTax () {
        return totalTax.setScale(2, RoundingMode.HALF_EVEN);
    }

    public ProductInfo getProduct () {
        return product;
    }

    public Integer getPurchasedQuantity () {
        return purchasedQuantity;
    }
}

