package com.healthesystems.catalog;

import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.repository.CatalogRepository;
import com.healthesystems.catalog.service.CatalogService;
import com.healthesystems.catalog.service.CatalogServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by apurdon on 9/28/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

//    @Mock
//    private CatalogService catalogService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogRepository catalogRepository ;



    @Before
    public void init() {
        Product one = new Product("1234", "9876","special wheelchair");
       Product two = new Product("1235", "9876","special wheelchair");
        Product three = new Product("1236", "9876","Super special wheelchair");


        catalogRepository.save(one);
        catalogRepository.save(two);
        catalogRepository.save(three);


    }

    @Test
    public void testServiceForData() {
        List<Product> products = this.catalogRepository.findByProductNameLike("special wheelchair");
        assertThat(products.size()).isEqualTo(2);
        //assertThat(products.get(0).getSku()).isEqualTo("1234");


    }

    @Test
    public void testServiceBySku() {

        Product p = catalogService.getProductBySku("1234");
        assertThat(p.getProductName()).isEqualTo("special wheelchair");
    }

}
