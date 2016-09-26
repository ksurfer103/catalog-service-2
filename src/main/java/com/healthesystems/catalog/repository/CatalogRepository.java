package com.healthesystems.catalog.repository;

import com.healthesystems.catalog.model.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends
        CrudRepository<Catalog, String> {

     Catalog findByProductName(String productName);

     List<Catalog> findByHcpc(String hcpc);

     Catalog findBySku(String sku);

     Catalog findByProductId(String id);
}


