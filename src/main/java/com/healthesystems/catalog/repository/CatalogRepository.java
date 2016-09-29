package com.healthesystems.catalog.repository;

import com.healthesystems.catalog.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends
        CrudRepository<Product, String> {

     List<Product> findByProductNameLike(String productName);

     List<Product> findByHcpc(String hcpc);

     Product findBySku(String sku);
}


