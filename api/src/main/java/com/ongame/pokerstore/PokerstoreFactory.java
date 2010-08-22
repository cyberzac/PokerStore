package com.ongame.pokerstore;

import com.ongame.pokerstore.backoffice.BackOfficeService;
import com.ongame.pokerstore.storefront.StoreFrontService;

/**
 * Defines a factory which can return Pokerstore service Object instances
 * (possibly shared or independent) when invoked.
 *
 * @author bwin Games AB
 * @version 2008-05-05
  */
public interface PokerstoreFactory {
    /**
     * Return an instance (possibly shared or indenpendent) of the BackOffice Service.
     *
     * @return an instance of the BackOffice Service
     */
    BackOfficeService createBackOfficeService();

    /**
     * Return an instance (possibly shared or indenpendent) of the StoreFront Service.
     *
     * @return an instance of the StoreFront Service
     */
    StoreFrontService createStoreFrontService();
}
