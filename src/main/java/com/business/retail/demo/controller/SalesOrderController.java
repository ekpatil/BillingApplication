package com.business.retail.demo.controller;


import com.business.retail.demo.bean.ItemWiseDetails;
import com.business.retail.demo.input.ProductOrder;
import com.business.retail.demo.repository.ProductInfo;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.math.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SalesOrderController {

    @RequestMapping("/test")
    public String restTest() {
        return "testing!";
    }


    @RequestMapping(value ="/generateBill" , method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    public String generateBill(@RequestBody List<ProductOrder> order) {
        boolean missingProducts;
        StringBuilder outputMsg;
        List<ItemWiseDetails> itemWiseDetails = getItemWiseDetails(order);
        missingProducts = itemWiseDetails.size()!= order.size();
        outputMsg = getItemWiseAndFinalBill(itemWiseDetails);
        outputMsg.append(missingProducts ? "\n\n *** Note : Input list have some products which are not available" : "");
        return outputMsg.toString();
    }


    List<ItemWiseDetails> getItemWiseDetails (List<ProductOrder> order) {
        return order.stream()
                .filter(product -> ProductInfo.getProductByCode(product.getProductCode()).isPresent())
                .map(product -> new ItemWiseDetails(ProductInfo.getProductByCode(product.getProductCode()).get(),product.getQuantity()))
                .collect(Collectors.toList());
    }

    StringBuilder getItemWiseAndFinalBill (List<ItemWiseDetails> itemWiseDetails) {
        // This is bit clumsy to format the bill, I am not spending much time on format : candidate for refactoring
        StringBuilder outputMsg = new StringBuilder("*** Detailed Bill : \n =============================\n");
        outputMsg.append("Product Name | base price | Units |Total Base Amount | Total Tax\n");
        BigDecimal totalBaseAmount = BigDecimal.ZERO;
        BigDecimal totalTaxAmount = BigDecimal.ZERO;

        for(ItemWiseDetails item : itemWiseDetails) {
            item.calculatePriceAndTax();
            outputMsg.append(item.getProduct().getName() + " | " + item.getProduct().getPrice() +" | " + item.getPurchasedQuantity() +" | " +
                    item.getTotalPrice() +" | " + item.getTotalTax() +"\n");
            totalBaseAmount = totalBaseAmount.add(item.getTotalPrice());
            totalTaxAmount = totalTaxAmount.add(item.getTotalTax());
        }
        outputMsg.append("============================\n");
        outputMsg.append(" Total Base Amount : "  + totalBaseAmount.setScale(2, RoundingMode.HALF_EVEN));
        outputMsg.append(" Total Tax Amount : "  + totalTaxAmount.setScale(2, RoundingMode.HALF_EVEN));
        outputMsg.append("\n============================\n");
        outputMsg.append("Total (Payable amount) : " + totalBaseAmount.add(totalTaxAmount).setScale(2,RoundingMode.HALF_EVEN));
        return outputMsg;
    }

}
