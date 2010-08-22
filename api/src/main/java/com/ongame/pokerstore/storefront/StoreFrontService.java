package com.ongame.pokerstore.storefront;

import com.ongame.pokerstore.common.InsufficientCreditException;

/**
 * High-level business interface for the StoreFront Service. It provides functionality
 * targeted to Pokerstore customers, such as purchasing products.
 *
 * @author bwin Games AB
 * @version 2008-05-05
 */
public interface StoreFrontService {
    /**
     * Makes a purchase of the given quantity of the given product
     * for the given customer. Pokerstore will reorder from the
     * supplier if a product is out of stock.
     *
     * @param productId  the product ID
     * @param customerId the customer ID
     * @param quantity   the number of items to purchase
     * @throws InsufficientCreditException if the customer does not have enough credit
     */
    void purchase(String productId, String customerId, int quantity)
            throws InsufficientCreditException;
}
