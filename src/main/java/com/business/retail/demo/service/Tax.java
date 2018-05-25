package com.business.retail.demo.service;

import java.math.BigDecimal;

public interface Tax {

    default BigDecimal calculateTax (Integer quantity, BigDecimal price) {
        return BigDecimal.ZERO;
    }
}
