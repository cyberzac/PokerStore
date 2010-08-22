package com.ongame.pokerstore.model;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: zac
 * Date: 2010-aug-19
 * Time: 00:28:28
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
public class HibernateStoreDAO implements StoreDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Product getProduct(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Product product where product.name =?").setParameter(0, name);
        return (Product) query.uniqueResult();
    }

    public Product save(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
        return product;
    }

    public Customer getCustomer(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer customer where customer.name =?").setParameter(0, name);
        return (Customer) query.uniqueResult();
    }

    public Customer save(Customer customer) {
        sessionFactory.getCurrentSession().saveOrUpdate(customer);
        return customer;
    }

    @SuppressWarnings("unchecked")
    public List<String> getOutOfStockProductIds() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        criteria.add(Restrictions.le("inventory", 0L));
        return (List<String>) criteria.list();
    }
}
