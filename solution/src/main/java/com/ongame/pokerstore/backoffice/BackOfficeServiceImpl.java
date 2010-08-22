package com.ongame.pokerstore.backoffice;

import com.ongame.pokerstore.model.Customer;
import com.ongame.pokerstore.model.Product;
import com.ongame.pokerstore.model.StoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: zac
 * Date: 2010-aug-17
 * Time: 21:14:40
 * <p/>
 * Copyright © 2010 Martin Zachrison
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
@Component
public class BackOfficeServiceImpl implements BackOfficeService {

    @Autowired
    StoreDAO storeDAO;

    /**
     * Sets the inventory and price for the product with the given product ID.
     * Any previous inventory and price values for the product are replaced.
     *
     * @param productId the product ID
     * @param price     the price of the product
     * @param inventory the number of items in stock
     */
    @Transactional
    public void stock(String productId, double price, long inventory) {
        Product product = new Product(productId, price, inventory);
        storeDAO.save(product);

    }

    /**
     * Returns the number of items in stock of the product
     * with the given product ID.
     *
     * @param productId the product ID
     * @return number of items
     */
    @Transactional(readOnly = true)
    public long getInventory(String productId) {
        Product product = storeDAO.getProduct(productId);
        return product.getInventory();
    }

    /**
     * Returns the price of the product with the given product ID.
     *
     * @param productId the product ID
     * @return the product's price
     */
    @Transactional(readOnly = true)
    public double getPrice(String productId) {
        Product product = storeDAO.getProduct(productId);
        return product.getPrice();
    }

    /**
     * Sets the amount of money that the customer with the given
     * customer ID has available for making purchases.
     *
     * @param customerId the customer ID
     * @param credit     the new customer credit amount
     */
    @Transactional
    public void setCustomerCredit(String customerId, double credit) {
        Customer customer = new Customer(customerId, credit);
        storeDAO.save(customer);
    }

    /**
     * Returns the amount of money that the customer with the given
     * customer ID has available for making purchases.
     *
     * @param customerId the customer ID
     * @return the customer's credit amount
     */
    @Transactional(readOnly = true)
    public double getCustomerCredit(String customerId) {
        Customer customer = storeDAO.getCustomer(customerId);
        return customer.getCredit();
    }

    /**
     * Returns a list ids of products out of stock
     *
     * @return list of product ids
     */
    @Transactional(readOnly = true)
    public List<String> getOutOfStock() {
        return storeDAO.getOutOfStockProductIds();
    }
}
