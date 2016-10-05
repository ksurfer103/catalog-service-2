package com.healthesystems.catalog;

import com.healthesystems.catalog.model.PriceLocale;
import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.model.ProductPrice;
import com.healthesystems.catalog.repository.CatalogRepository;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.filter.ValueNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    private static final Logger logger = LoggerFactory.getLogger(ModelTests.class);



    @Test
    public void testCustomerPricingXX() {

        // Product price
        Set<ProductPrice> prices = new HashSet<ProductPrice>() ;
        prices.add(new ProductPrice(null, BigDecimal.valueOf(10.00),new Date(), PriceLocale.XX,"***********","LIBERTY"));
        Product p = new Product("1234999","9876","bandaid",prices);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySkuAndProductPricesVendorAndProductPricesCustomer("1234999","***********","LIBERTY");
        logger.info("savedProduct: {}",savedProduct.getProductPrices());
        System.out.println("saved prod: " + savedProduct.toJSON());
        assertThat(savedProduct).isEqualTo(p);
        assertThat(savedProduct.getProductPrices().size()).isEqualTo(1);
        // parse some JSON
        String jsonString = savedProduct.toJSON();
        String jsonExp = "$.productPrices";
        List<Object> pps = JsonPath.read(jsonString, jsonExp);
        System.out.println("what we get : " + pps.get(0));
        // first record
        String cust = JsonPath.read(pps.get(0), "$.customer");
        assertThat(cust).isEqualTo("LIBERTY");
        // check locale
        String locale = JsonPath.read(pps.get(0), "$.priceLocale");
        assertThat(locale).isEqualTo(  "XX" );
        // check vendor
        String vend = JsonPath.read(pps.get(0), "$.vendor");
        assertThat(vend).isEqualTo( "***********");

    }

    @Test
    public void testVendorPricingCA() {

        // Product price
        Set<ProductPrice> prices = new HashSet<ProductPrice>() ;
        prices.add(new ProductPrice(null, BigDecimal.valueOf(100.00),new Date(), PriceLocale.CA,"Acme Medical Supply","***********"));
        Product p = new Product("1234999","9876","bandaid",prices);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySkuAndProductPricesVendorAndProductPricesCustomer("1234999","Acme Medical Supply","***********");
        logger.info("savedProduct: {}",savedProduct.getProductPrices());
        assertThat(savedProduct).isEqualTo(p);
        assertThat(savedProduct.getProductPrices().size()).isEqualTo(1);
        // parse some JSON
        String jsonString = savedProduct.toJSON();
        String jsonExp = "$.productPrices";
        List<Object> pps = JsonPath.read(jsonString, jsonExp);
        System.out.println("what we get : " + pps.get(0));
        // first record
        String cust = JsonPath.read(pps.get(0), "$.customer");
        assertThat(cust).isEqualTo("***********");
        // check locale
        String locale = JsonPath.read(pps.get(0), "$.priceLocale");
        assertThat(locale).isEqualTo(  "CA" );
        // check vendor
        String vend = JsonPath.read(pps.get(0), "$.vendor");
        assertThat(vend).isEqualTo( "Acme Medical Supply");

    }

}
