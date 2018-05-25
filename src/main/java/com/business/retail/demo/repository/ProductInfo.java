package com.business.retail.demo.repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;


public enum ProductInfo {

    PARK_PERFUME("P_PERFUME", "Park avenue perfume 200 ml",BigDecimal.valueOf(223.50), "categoryA" ),
    FOG_PERFUME("F_PERFUME", "Fog perfume 200 ml",BigDecimal.valueOf(145), "categoryA" ),
    DOVE("D_SOAP", "Dove beauty soap ,pack of 3 bars",BigDecimal.valueOf(111), "categoryA" ),
    LUX("L_SOAP", "Lux cream and silky,pack of 3 bars",BigDecimal.valueOf(122.50), "categoryA" ),

    ONE_PLUS_6T("ONE_PLUS_6T", "One plus 6T mobile set",BigDecimal.valueOf(34999.90), "categoryB" ),
    SAM_G_8("SAM_G_8", "Samsung galaxy G8 mobile set",BigDecimal.valueOf(17850), "categoryB" ),
    MOTO_G5("MOTO_G5", "Motorola G 5 mobile set",BigDecimal.valueOf(13899), "categoryB" ),
    REDMI_4("REDMI_4", "Redmi 4 16GB black mobile set",BigDecimal.valueOf(9999), "categoryB" ),

    HERO_PEN("H_PEN", "Hero ink pen",BigDecimal.valueOf(50.50), "categoryC" ),
    REY_PEN("R_PEN", "Reynolds ball pen",BigDecimal.valueOf(20), "categoryC" ),
    CLA_NOTEBOOK("C_NOTEBOOK", "Classmate notebook 200 pgs",BigDecimal.valueOf(70.50), "categoryC" ),
    S_NOTEBOOK("S_NOTEBOOK", "Student notebook 200 pgs",BigDecimal.valueOf(65.50), "categoryC" );

    private String code;
    private String name;
    private BigDecimal price;
    private String taxCategory;

    ProductInfo (String code, String name, BigDecimal price, String taxCategory) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.taxCategory = taxCategory;
    }

    public static Optional<ProductInfo> getProductByCode(String productCode){
        return Stream.of(values())
                .filter(productInfo -> productInfo.code.equalsIgnoreCase(productCode))
                .findFirst();
    }

    public BigDecimal getPrice () {
        return price;
    }

    public String getTaxCategory () {
        return taxCategory;
    }

    public String getName () {
        return name;
    }
}
