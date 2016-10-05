package com.healthesystems.catalog;

import com.healthesystems.catalog.model.PriceLocale;
import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.model.ProductPrice;
import com.healthesystems.catalog.repository.CatalogRepository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apurdon on 9/27/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("local")
//@Ignore
public class ModelTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogRepository catalogRepository;

    // Model Test
    @Test
    public void saveShouldPersistData() throws Exception {
        //Product product = this.entityManager.persistFlushFind(new Product("1234", "9876","bigwheels"));
        //assertThat(product.getProductName()).isEqualTo("bigwheels");
        //assertThat(product.getSku()).isEqualTo("1234");
    }

    //Repository Test
    @Test
    public void findByUsernameShouldReturnUser() throws Exception {
      //  this.entityManager.persist(new Product("1234", "9876","wheelchair"));
        //Product product = this.catalogRepository.findByProductNameLike("wheelchair").get(0);
        //assertThat(product.getSku()).isEqualTo("1234");
       // assertThat(product.getHcpc()).isEqualTo("9876");
    }

    @Test
    public void testCustomerPricing() {

        // Product price
        Set<ProductPrice> prices = new HashSet<ProductPrice>() ;
        prices.add(new ProductPrice(null, BigDecimal.valueOf(10.00),new Date(), PriceLocale.XX,"***********","LIBERTY"));
        Product p = new Product("1234999","9876","bandaid",prices);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySku("1234999");
        assertThat(savedProduct).isEqualTo(p);

    }

}
