package com.business.retail.demo.controller;

import com.business.retail.demo.bean.ItemWiseDetails;
import com.business.retail.demo.input.ProductOrder;
import com.business.retail.demo.repository.ProductInfo;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SalesOrderControllerTest {

    private SalesOrderController controller = new SalesOrderController();

    @Test
    public void generateBillForMissingNote () {
        List<ProductOrder> originalOrder = createMockedOrder();
        originalOrder.add(new ProductOrder("PEN_INVALID", 1));

        String output = controller.generateBill(originalOrder);

        assertTrue(output.contains("*** Note : Input list have some products which are not available"));
        //print it if you want to visualize the output.
        //System.out.println(output);
    }

    @Test
    public void generateBillForNotMissingNote () {
        List<ProductOrder> originalOrder = createMockedOrder();

        String output = controller.generateBill(originalOrder);

        assertFalse(output.contains("*** Note : Input list have some products which are not available"));
    }

    @Test
    public void getItemWiseDetails () {
        List<ItemWiseDetails> itemWiseDetails = controller.getItemWiseDetails(createMockedOrder());

        assertTrue(itemWiseDetails.get(0).getProduct() == ProductInfo.HERO_PEN);
        assertTrue(itemWiseDetails.get(0).getPurchasedQuantity().equals(Integer.valueOf(2)));
        assertTrue(itemWiseDetails.get(1).getProduct() == ProductInfo.PARK_PERFUME);
        assertTrue(itemWiseDetails.get(1).getPurchasedQuantity().equals(Integer.valueOf(2)));
        assertTrue(itemWiseDetails.get(2).getProduct() == ProductInfo.ONE_PLUS_6T);
        assertTrue(itemWiseDetails.get(2).getPurchasedQuantity().equals(Integer.valueOf(2)));
    }

    @Test
    public void getItemWiseAndFinalBill() {
        List<ItemWiseDetails> itemWiseDetails = controller.getItemWiseDetails(createMockedOrder());

        String output = controller.getItemWiseAndFinalBill(itemWiseDetails).toString();

        assertTrue(output.contains("Total Base Amount : 70547.80"));
        assertTrue(output.contains("Total Tax Amount : 14044.66"));
        assertTrue(output.contains("Total (Payable amount) : 84592.46"));

    }
    private List<ProductOrder> createMockedOrder () {
        List<ProductOrder> order = new ArrayList<>();
        order.add(new ProductOrder("H_PEN", 2));
        order.add(new ProductOrder("P_PERFUME", 2));
        order.add(new ProductOrder("ONE_PLUS_6T", 2));
        return order;
    }
}