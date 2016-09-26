package com.healthesystems.catalog.service;

import com.healthesystems.catalog.model.Product;

import java.util.List;

/**
 * Created by apurdon on 8/22/16.
 */
public interface CatalogService {


    boolean isItemExist(Product product);
    Product getProduct(String productName);
    List<Product> getProductByHcpc(String hcpc);
    Product getProductBySku(String sku);

    Product getCatalogById(String id);
    Product save(Product product);

}
