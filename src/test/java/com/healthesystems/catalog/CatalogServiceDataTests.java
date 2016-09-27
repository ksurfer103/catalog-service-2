package com.healthesystems.catalog;

import com.healthesystems.catalog.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by apurdon on 9/27/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CatalogServiceDataTests {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveShouldPersistData() throws Exception {
        Product product = this.entityManager.persistFlushFind(new Product("1234", "9876","bigwheels"));
        assertThat(product.getProductName()).isEqualTo("bigwheels");
        assertThat(product.getSku()).isEqualTo("1234");
    }






}
