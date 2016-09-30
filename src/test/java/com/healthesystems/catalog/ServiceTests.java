package com.healthesystems.catalog;

import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.model.ProductPrice;
import com.healthesystems.catalog.model.ProductPriceType;
import com.healthesystems.catalog.repository.CatalogRepository;
import com.healthesystems.catalog.service.CatalogService;
import com.healthesystems.catalog.service.CatalogServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by apurdon on 9/28/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class ServiceTests {

	/*
	 * using data supplied in the data.sql file
	 */

    private static final Logger logger = LoggerFactory.getLogger(ServiceTests.class);

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogRepository catalogRepository ;


    @Test
    public void testServiceLocal(){
    	Product product = catalogService.getProductBySku("5");
    	logger.info("Product: {}", product.toString());
    	Assert.assertEquals("WSD", product.getHcpc());
    	
    }
    @Test
    public void testServiceForData() {
        List<Product> products = this.catalogRepository.findByProductNameLike("special wheelchair");
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).getSku()).isEqualTo("1234");

        List<Product> prods = this.catalogRepository.findByProductNameLike("Super special wheelchair");
        assertThat(prods.size()).isEqualTo(1);
        assertThat(prods.get(0).getSku()).isEqualTo("1236");
    }

    @Test
    public void testServiceBySku() {
        Product p = catalogService.getProductBySku("1234");
        logger.info("Product: {}", p.toString());
        assertThat(p.getProductName()).isEqualTo("special wheelchair");
    }

    @Test
    public void testServiceByProductName() {
        List <Product> p = catalogService.getProductByName("special wheelchair");
        assertThat(p.size()).isEqualTo(2);
    }
    
    @Test
    public void testCreateNewProduct(){
    	
    	Set<ProductPrice> prices = new HashSet<ProductPrice>();
    	prices.add(new ProductPrice(null,BigDecimal.valueOf(1000.00),ProductPriceType.Vendor,new Date(),"Progressive"));
    	prices.add(new ProductPrice(null,BigDecimal.valueOf(1000.00),ProductPriceType.StateOfVenue,new Date(),"CA"));
    	Product newProduct = new Product("123456789","123456789","Super-duber special wheelchair", prices);
    	
    	catalogService.save(newProduct);
    	Product savedProduct = catalogService.getProductBySku(newProduct.getSku());
    	
    	assertThat(savedProduct.getProductName()).isEqualTo("Super-duber special wheelchair");
    	
    }




}
