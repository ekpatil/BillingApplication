package com.business.retail.demo.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductOrder {

    @JsonProperty
    private String productCode;

    private Integer quantity = 1;


    public ProductOrder () {
    }

    public ProductOrder (String productCode, Integer quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public Integer getQuantity () {
        return quantity;
    }

    public void setQuantity (Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductCode () {
        return productCode;
    }

    public void setProductCode (String productCode) {
        this.productCode = productCode;
    }
}
