package com.ongame.pokerstore.supplier;

import com.ongame.pokerstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zac
 * Date: 2010-aug-20
 * Time: 17:30:01
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
public class SupplierServiceImpl implements SupplierService {
    Logger log = LoggerFactory.getLogger(SupplierServiceImpl.class);
    private List<Product> orders = new ArrayList<Product>();

    public void order(Product product) {
        log.info("Time to order {} inventory is {}", product.getName(), product.getInventory());
        orders.add(product);
    }

    public List<Product> getOrders() {
        return orders;
    }
}
