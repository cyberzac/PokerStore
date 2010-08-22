package com.ongame.pokerstore;

import com.ongame.pokerstore.backoffice.BackOfficeService;
import com.ongame.pokerstore.storefront.StoreFrontService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * User: zac
 * Date: 2010-aug-17
 * Time: 17:41:29
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
public class PokerstoreFactoryImpl implements PokerstoreFactory {

    private BackOfficeService backOfficeService;

    private StoreFrontService storeFrontService;

    public PokerstoreFactoryImpl() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        backOfficeService = ctx.getBean(BackOfficeService.class);
        storeFrontService = ctx.getBean(StoreFrontService.class);

    }

    /**
     * Return an instance (possibly shared or independent) of the BackOffice Service.
     *
     * @return an instance of the BackOffice Service
     */
    public BackOfficeService createBackOfficeService() {
        return backOfficeService;
    }

    /**
     * Return an instance (possibly shared or independent) of the StoreFront Service.
     *
     * @return an instance of the StoreFront Service
     */
    public StoreFrontService createStoreFrontService() {
        return storeFrontService;
    }
}
