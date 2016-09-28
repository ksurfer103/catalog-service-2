package com.healthesystems.catalog;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.service.CatalogService;

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
	public void testRestEndpointSku() throws Exception {

        given(this.catalogService.getProductBySku("1234"))
                .willReturn(new Product("1234", "9876","bigwheels"));

        MvcResult result = this.mvc.perform(get("/products/sku").param("sku","1234").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().json("{\"hcpc\":\"9876\",\"sku\":\"1234\",\"productName\":\"bigwheels\"}"))
                .andReturn();

        logger.info("results: {}",result.getResponse().getContentAsString());
	}

    @Test
    public void testRestEndpointHcpc() throws Exception {
        List<Product> products = Arrays.asList(
                new Product("1234", "9876","bigwheels"),
                new Product("1234", "9876","bigwheels"));
        when(catalogService.getProductByHcpc("9876")).thenReturn(products);

        mvc.perform(get("/products/hcpc").param("hcpc","9876").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].sku", is("1234")))
               .andExpect(jsonPath("$[0].productName", is("bigwheels")));
//


        logger.info("results: ");
    }

    @Test
    public void testRestEndpointProductName() throws Exception {
        List<Product> products = Arrays.asList(
                new Product("1234", "9876","bigwheels"),
                new Product("1234", "9876","bigwheels"));
        when(catalogService.getProductByHcpc("9876")).thenReturn(products);

        mvc.perform(get("/products/productname").param("productName","bigwheels").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

//                .andExpect(jsonPath("$", hasSize(2)))
//               .andExpect(jsonPath("$[0].sku", is("1234")))
//                .andExpect(jsonPath("$[0].productName", is("bigwheels")));




        logger.info("results: productName ");
    }



    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void testInsertProduct() throws Exception {
        String url = "products" ;
        Product anObject = new Product("testSku","testHcpc","testProduct");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject );
        logger.info("post {}",requestJson );
       mvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isAccepted());
    }



}
