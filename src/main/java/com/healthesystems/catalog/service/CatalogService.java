package com.healthesystems.catalog.service;

import com.healthesystems.catalog.model.Catalog;

import java.util.List;

/**
 * Created by apurdon on 8/22/16.
 */
public interface CatalogService {


    boolean isItemExist(Catalog catalog);
    Catalog getProduct( String productName);
    List<Catalog> getProductByHcpc( String hcpc);
    Catalog getProductBySku( String sku);

    Catalog getCatalogById(String id);
    Catalog save(Catalog catalog);

}
