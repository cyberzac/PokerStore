package com.ongame.pokerstore;

import com.ongame.pokerstore.backoffice.BackOfficeService;
import com.ongame.pokerstore.common.InsufficientCreditException;
import com.ongame.pokerstore.storefront.StoreFrontService;
import com.ongame.pokerstore.supplier.SupplierService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Simple unit test (JUnit 4) for demonstrating a basic program flow
 * of Pokerstore.
 *
 * @author bwin Games AB
 * @version 2008-05-05
 */
public class SpringPokerstoreTest {
    private static final String DELL = "dell-precision-m20";
    private static final String CUSTOMER = "customer1";

    private BackOfficeService backOfficeService;
    private StoreFrontService storeFrontService;
    private int inventory;
    private double price;
    private static ApplicationContext ctx;
    private SupplierService supplierService;

    @BeforeClass
    public static void setUpApplicationContext() {
        ctx = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Before
    public void setUp() throws Exception {
        backOfficeService = ctx.getBean(BackOfficeService.class);
        storeFrontService = ctx.getBean(StoreFrontService.class);
        supplierService = ctx.getBean(SupplierService.class);
        inventory = 10;
        price = 1000.0d;
        backOfficeService.stock(DELL, price, inventory);
    }

    /**
     * Makes a purchase and tests that inventory and customer credit
     * have been updated accordingly.
     */
    @Test
    public void normalFlow() {

        int quantity = 5;
        double credit = inventory * price * 2;
        backOfficeService.setCustomerCredit(CUSTOMER, credit);
        storeFrontService.purchase(DELL, CUSTOMER, quantity);
        assertEquals("Inventory not decreased", inventory - quantity, backOfficeService.getInventory(DELL));
        assertEquals("Credit not decreased", credit - (quantity * price), backOfficeService.getCustomerCredit(CUSTOMER));
    }

    /**
     * Try to make a purchase with insuffcient funds
     */
    @Test(expected = InsufficientCreditException.class)
    public void insuffcientFunds() {
        int quantity = 2;
        double credit = price;
        backOfficeService.setCustomerCredit(CUSTOMER, credit);
        storeFrontService.purchase(DELL, CUSTOMER, quantity);
        fail("Expected InsufficientCreditException");
    }

    /**
     * Verify that a reorder is done if the inventory is zero
     */
    @Test
    public void reorder() {
        double credit = inventory * price * 2;
        backOfficeService.setCustomerCredit(CUSTOMER, credit);
        storeFrontService.purchase(DELL, CUSTOMER, 5);
        assertTrue("No order should have been placed", supplierService.getOrders().isEmpty());
        storeFrontService.purchase(DELL, CUSTOMER, 5);
        assertFalse("Order should have been placed", supplierService.getOrders().isEmpty());
    }

    /**
     * Verify that is it possible to set the credit for a customer
     */
    @Test
    public void customerCredit() {
        double credit = 200;
        backOfficeService.setCustomerCredit(CUSTOMER, credit);
        assertEquals(credit, backOfficeService.getCustomerCredit(CUSTOMER));
    }

    /**
     * Verify that stock sets inventory and price for a product.
     */
    @Test
    public void stock() {
        backOfficeService.stock(DELL, price, inventory);
        assertEquals(inventory, backOfficeService.getInventory(DELL));
        assertEquals(price, backOfficeService.getPrice(DELL));
    }

    /**
     * Verify that a list of all products out of stock can be retrived
     */
    @Test
    public void outOfStock() {
        String hp = "HP Proliant 380DL";
        backOfficeService.stock(hp, price, inventory);
        assertTrue("No products should be out of stock.", backOfficeService.getOutOfStock().isEmpty());
        backOfficeService.setCustomerCredit(CUSTOMER, price * inventory * 4);
        storeFrontService.purchase(DELL, CUSTOMER, inventory);
        List<String> outOfStock = backOfficeService.getOutOfStock();
        assertEquals("Wrong number of products out of stock", 1, outOfStock.size());
        assertEquals("Expected " + DELL + " to be out of stock", DELL, outOfStock.get(1));
        storeFrontService.purchase(hp, CUSTOMER, inventory);
        assertEquals("Wrong number of products out of stock", 2, outOfStock.size());
        if (DELL.equals(outOfStock.get(0))) {
            assertEquals("Second product should be " + hp, hp, outOfStock.get(1));
        } else if (hp.equals(outOfStock.get(0))) {
            assertEquals("Second product should be " + DELL, DELL, outOfStock.get(1));
        } else {
            fail("Wrong products out of stock");
        }
    }
}
