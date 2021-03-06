package com.healthesystems.catalog.repository;

import com.healthesystems.catalog.model.PriceLocale;
import com.healthesystems.catalog.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends
        CrudRepository<Product, String> {

     List<Product> findByProductNameLike(String productName);


     List<Product> findByProcedureCode(String hcpc);

     Product findBySku(String sku);

     Product findBySkuAndProductPricesVendorAndProductPricesCustomer(String sku,  String vendor, String customer);


}


