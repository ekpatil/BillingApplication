package com.business.retail.demo.service;

import java.math.*;

public class CategoryBTax implements Tax {

    private final BigDecimal taxationPercentage = BigDecimal.valueOf(0.2);

    @Override
    public BigDecimal calculateTax (Integer quantity, BigDecimal price) {
        return taxationPercentage.multiply(new BigDecimal(quantity)).multiply(price).setScale(2,RoundingMode.HALF_EVEN);
    }

}