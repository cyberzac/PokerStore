package com.ongame.pokerstore.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: zac
 * Date: 2010-aug-18
 * Time: 23:42:10
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
@Entity
public class Product implements Serializable {
    private long id;
    private String name;
    private double price;
    private long inventory;

    public Product() {
    }

    public Product(String name, double price, long inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getInventory() {
        return inventory;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    public void changeInventory(long quantity) {
        inventory -= quantity;
    }

    @Transient
    public boolean isTimeToReorder() {
        return inventory <= 0;
    }
}
