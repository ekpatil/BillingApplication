package com.business.retail.demo.service;

import java.math.BigDecimal;

public interface BasicPrice {

    default BigDecimal calculatePrice (Integer quantity, BigDecimal price) {
        return price.multiply(new BigDecimal(quantity)).setScale(2,BigDecimal.ROUND_CEILING);
    }
}
