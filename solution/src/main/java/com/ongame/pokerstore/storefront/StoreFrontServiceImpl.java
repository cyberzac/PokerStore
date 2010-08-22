package com.ongame.pokerstore.storefront;

import com.ongame.pokerstore.backoffice.BackOfficeService;
import com.ongame.pokerstore.common.InsufficientCreditException;
import com.ongame.pokerstore.model.Customer;
import com.ongame.pokerstore.model.Product;
import com.ongame.pokerstore.model.StoreDAO;
import com.ongame.pokerstore.supplier.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: zac
 * Date: 2010-aug-17
 * Time: 21:16:33
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
public class StoreFrontServiceImpl implements StoreFrontService {


    @Autowired
    StoreDAO storeDAO;

    @Autowired
    BackOfficeService backOfficeService;

    @Autowired
    SupplierService supplierService;

    /**
     * Makes a purchase of the given quantity of the given product
     * for the given customer. Pokerstore will reorder from the
     * supplier if a product is out of stock.
     *
     * @param productId  the product ID
     * @param customerId the customer ID
     * @param quantity   the number of items to purchase
     * @throws com.ongame.pokerstore.common.InsufficientCreditException
     *          if the customer does not have enough credit
     */
    @Transactional
    public void purchase(String productId, String customerId, int quantity) throws InsufficientCreditException {
        Customer customer = storeDAO.getCustomer(customerId);
        Product product = storeDAO.getProduct(productId);
        double orderValue = product.getPrice() * quantity;
        double credit = customer.getCredit();
        if (credit < orderValue) {
            throw new InsufficientCreditException();
        }
        customer.setCredit(credit - orderValue);
        product.changeInventory(quantity);
        if(product.isTimeToReorder()) {
          supplierService.order(product);
        }
        storeDAO.save(customer);
        storeDAO.save(product);
    }
}
