package com.ongame.pokerstore.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: zac
 * Date: 2010-aug-18
 * Time: 23:46:31
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
public class Customer implements Serializable {

    private String id;
    private double credit;

    public Customer() {}

    public Customer(String name, double credit) {
        this.id = name;
        this.credit = credit;
    }

    @Id
    @Column(unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
