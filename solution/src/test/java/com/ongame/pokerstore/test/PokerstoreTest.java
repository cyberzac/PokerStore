package com.ongame.pokerstore.test;

import com.ongame.pokerstore.PokerstoreFactory;
import com.ongame.pokerstore.backoffice.BackOfficeService;
import com.ongame.pokerstore.storefront.StoreFrontService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Simple unit test (JUnit 4) for demonstrating a basic program flow
 * of Pokerstore.
 *
 * @author bwin Games AB
 * @version 2008-05-05
 */
public class PokerstoreTest {
    private static final String DELL = "dell-precision-m20";
    private static final String CUSTOMER = "customer1";

    private PokerstoreFactory pokerstoreFactory;
    private BackOfficeService backOfficeService;
    private StoreFrontService storeFrontService;

    @Before
    public void setUp() throws Exception {
        pokerstoreFactory = (PokerstoreFactory) Class.forName(
                "com.ongame.pokerstore.PokerstoreFactoryImpl").newInstance();
        backOfficeService = pokerstoreFactory.createBackOfficeService();
        storeFrontService = pokerstoreFactory.createStoreFrontService();
    }

    /**
     * Makes a purchase and tests that inventory and customer credit
     * have been updated accordingly.
     */
    @Test
    public void testNormalFlow() {
        int inventory = 10;
        double price = 1000.0d;
        int quantity = 5;
        double credit = inventory * price * 2;

        stock(DELL, price, inventory);
        setCustomerCredit(credit);

        storeFrontService.purchase(DELL, CUSTOMER, quantity);
        assertEquals(inventory - quantity, backOfficeService.getInventory(DELL));
        assertEquals(credit - (quantity * price), backOfficeService.getCustomerCredit(CUSTOMER));
    }

    /**
     * Sets the customer's credit to the given amount and verifies
     * that it got updated.
     *
     * @param credit amount of money in SEK
     */
    private void setCustomerCredit(double credit) {
        backOfficeService.setCustomerCredit(CUSTOMER, credit);
        assertEquals(credit, backOfficeService.getCustomerCredit(CUSTOMER));
    }

    /**
     * Sets inventory and price for a product and verifies that
     * the levels have been updated.
     *
     * @param productId the product ID
     * @param price     the price in SEK
     * @param inventory the number of items in stock
     */
    private void stock(String productId, double price, int inventory) {
        backOfficeService.stock(productId, price, inventory);
        assertEquals(inventory, backOfficeService.getInventory(productId));
        assertEquals(price, backOfficeService.getPrice(productId));
    }
}
