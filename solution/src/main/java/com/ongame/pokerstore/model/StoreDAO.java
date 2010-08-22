package com.ongame.pokerstore.model;

import java.util.List;

/**
 * User: zac
 * Date: 2010-aug-19
 * Time: 00:06:44
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
public interface StoreDAO {

    Product getProduct(String productId);

    Product save(Product product);

    Customer getCustomer(String customerId);

    Customer save(Customer customer);

    List<String> getOutOfStockProductIds();
}
