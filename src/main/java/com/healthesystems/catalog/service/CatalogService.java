package com.healthesystems.catalog.service;

import com.healthesystems.catalog.model.Catalog;

import java.util.List;

/**
 * Created by apurdon on 8/22/16.
 */
public interface CatalogService {


    boolean isItemExist(Catalog catalog);
    List<Catalog> getProduct( String productName);
    List<Catalog> getProductByHcpc( String hcpc);
    List<Catalog> getProductBySku( String sku);
    Catalog getCatalogById(String id);

    Catalog save(Catalog catalog);

}
