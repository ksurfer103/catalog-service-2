package com.healthesystems.catalog;

import com.healthesystems.catalog.model.*;
import com.healthesystems.catalog.repository.CatalogRepository;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import java.util.*;

/**
 * Created by apurdon on 9/27/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("local")
public class ModelTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CatalogRepository catalogRepository;

    private static final Logger logger = LoggerFactory.getLogger(ModelTests.class);
    List<ProductPrice> prices = new ArrayList<>() ;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    ProductCategory category;
    @Before
    public void init() {
        category = new ProductCategory("SERVICE", "Transportation service");
    }

    @Test
    public void testCustomerPricingXX() {

        // Product price

        prices.add(new ProductPrice(null, BigDecimal.valueOf(10.00),new Date(), PriceLocale.XX,"***********","LIBERTY"));
        Product p = new Product("1234999","9876","bandaid",prices, category, Discriminator.HCPC);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySkuAndProductPricesVendorAndProductPricesCustomer("1234999","***********","LIBERTY");
        logger.info("savedProduct: {}",savedProduct.getProductPrices());

        assertThat(savedProduct).isEqualTo(p);
        assertThat(savedProduct.getProductPrices().size()).isEqualTo(1);
        // parse some JSON
        String jsonString = savedProduct.toJSON();
        String jsonExp = "$.productPrices";
        List<Object> pps = JsonPath.read(jsonString, jsonExp);
        logger.info("what we get : {}", pps.get(0));
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

        prices.add(new ProductPrice(null, BigDecimal.valueOf(100.00),new Date(), PriceLocale.CA,"Acme Medical Supply","***********"));
        Product p = new Product("1234999","9876","bandaid",prices, category, Discriminator.HCPC);

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
        logger.info("what we get : {}", pps.get(0));
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


    @Test
    public void testPriceIsGreaterThanZero() {

        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Price must be greater than $0");
        new ProductPrice(null, BigDecimal.valueOf(0.00),new Date(), PriceLocale.CA,"Acme Medical Supply","***********");

    }

    @Test
    public void testPriceIsNotNull() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Price can not be null");
        new ProductPrice(null, null,new Date(), PriceLocale.CA,"Acme Medical Supply","***********");
    }

    @Test
    public void testVendorIsNotNull() {
        // Product price

        prices.add(new ProductPrice(null, BigDecimal.valueOf(10.00),new Date(), PriceLocale.XX,null,"ACME"));
        Product p = new Product("1234999","9876","bandaid",prices, category, Discriminator.HCPC);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySku("1234999");
        logger.info("savedProduct: {}",savedProduct.toJSON());

        // parse some JSON
        String jsonString = savedProduct.toJSON();
        String jsonExp = "$.productPrices";
        List<Object> pps = JsonPath.read(jsonString, jsonExp);
        logger.info("what we get : {}", pps.get(0));
        // first record test that the null vendor code has been transformed to X
        String vend = JsonPath.read(pps.get(0), "$.vendor");
        assertThat(vend).isEqualTo("XXXXXXXXXX");

    }

    @Test
    public void testCustomerIsNotNull() {
        // Product price

        prices.add(new ProductPrice(null, BigDecimal.valueOf(10.00),new Date(), PriceLocale.XX,"***********",null));
        Product p = new Product("1234999","9876","bandaid",prices, category, Discriminator.HCPC);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySku("1234999");
        logger.info("savedProduct: {}",savedProduct.toJSON());

           // parse some JSON
        String jsonString = savedProduct.toJSON();
        String jsonExp = "$.productPrices";
        List<Object> pps = JsonPath.read(jsonString, jsonExp);
        logger.info("what we get : {}", pps.get(0));
        // first record test that the null customer code has been transformed to X
        String cust = JsonPath.read(pps.get(0), "$.customer");
        assertThat(cust).isEqualTo("XXXXXXXXXX");

    }


    @Test
    public void testLocaleIsNotNull() {
        // Product price

        prices.add(new ProductPrice(null, BigDecimal.valueOf(10.00),new Date(), null,"***********",null));
        Product p = new Product("1234999","9876","bandaid",prices, category, Discriminator.HCPC);

        catalogRepository.save(p);

        // get product
        Product savedProduct = catalogRepository.findBySku("1234999");
        logger.info("savedProduct: {}",savedProduct.toJSON());

        // parse some JSON
        String jsonString = savedProduct.toJSON();
        String jsonExp = "$.productPrices";
        List<Object> pps = JsonPath.read(jsonString, jsonExp);
        logger.info("what we get : {}", pps.get(0));
        // first record test that the null customer code has been transformed to X
        String priceLocale = JsonPath.read(pps.get(0), "$.priceLocale");
        assertThat(priceLocale).isEqualTo("XX");
    }

}
