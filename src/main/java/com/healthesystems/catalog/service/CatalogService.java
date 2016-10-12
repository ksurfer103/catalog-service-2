package com.healthesystems.catalog.service;

import com.healthesystems.catalog.model.Product;

import java.util.List;

/**
 * Created by apurdon on 8/22/16.
 */
public interface CatalogService {


    boolean isItemExist(Product product);
    List<Product> getProductByName(String productName);
    List<Product> getProductByHcpcProcedureCode(String hcpc);
    Product getProductBySku(String sku);
    Product save(Product product);

}
