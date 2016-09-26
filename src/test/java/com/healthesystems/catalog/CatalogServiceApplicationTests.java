package com.healthesystems.catalog;

import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.service.CatalogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CatalogServiceApplicationTests {




    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private CatalogService catalogService;

    private static final Logger logger = LoggerFactory.getLogger(CatalogServiceApplicationTests.class);


    @Test
	public void testRestEndpoint() throws Exception {

        given(this.catalogService.getProductBySku("1234"))
                .willReturn(new Product("1234", "9876","bigwheels"));

        MvcResult result = this.mvc.perform(get("/products/sku").param("sku","1234").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().json("{\"hcpc\":\"9876\",\"sku\":\"1234\",\"productName\":\"bigwheels\"}"))
                .andReturn();

        logger.info("results: {}",result.getResponse().getContentAsString());
	}


}
