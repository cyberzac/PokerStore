package com.ongame.pokerstore.backoffice;

import java.util.List;

/**
 * High-level business interface for the BackOffice service. It provides functionality
 * targeted to Pokerstore employees such as product stocking, inventory queries
 * and customer credits.
 *
 * @author bwin Games AB
 * @version 2008-05-05
 */
public interface BackOfficeService {
    /**
     * Sets the inventory and price for the product with the given product ID.
     * Any previous inventory and price values for the product are replaced.
     *
     * @param productId the product ID
     * @param price     the price of the product
     * @param inventory the number of items in stock
     */
    void stock(String productId, double price, long inventory);

    /**
     * Returns the number of items in stock of the product
     * with the given product ID.
     *
     * @param productId the product ID
     * @return number of items
     */
    long getInventory(String productId);

    /**
     * Returns the price of the product with the given product ID.
     *
     * @param productId the product ID
     * @return the product's price
     */
    double getPrice(String productId);

    /**
     * Sets the amount of money that the customer with the given
     * customer ID has available for making purchases.
     *
     * @param customerId the customer ID
     * @param credit     the new customer credit amount
     */
    void setCustomerCredit(String customerId, double credit);

    /**
     * Returns the amount of money that the customer with the given
     * customer ID has available for making purchases.
     *
     * @param customerId the customer ID
     * @return the customer's credit amount
     */
    double getCustomerCredit(String customerId);

    /**
     * Returns a list ids of products out of stock
     * @return list of product ids
     */
    List<String> getOutOfStock();
}
