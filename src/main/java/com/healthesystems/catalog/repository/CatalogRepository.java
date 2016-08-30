package com.healthesystems.catalog.repository;

import com.healthesystems.catalog.model.Catalog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends
        CrudRepository<Catalog, String> {

     List<Catalog> findByProductName(String productName);

     List<Catalog> findByHcpc(String hcpc);

     List<Catalog> findBySku(String sku);

    Catalog findByProductId(String id);
}


