package com.healthesystems.catalog;

import com.healthesystems.catalog.model.Catalog;
import com.healthesystems.catalog.repository.CatalogRepository;
import com.healthesystems.catalog.service.CatalogService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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


//	@Before
//	public void setup() {
//        Catalog cat =  new Catalog();
//        cat.setHcpc("1234");
//
//        cat.setProductName("massive stick");
//        cat.setSku("9876");
//
//        catalogService.save(cat);
//	}


	@Test
    @Ignore
    public void testSaveCatalog() {
        Catalog catalog = catalogService.getProductBySku("9876");
        assertEquals("1234",catalog.getHcpc());

    }

    @Test
	public void testRestEndpoint() throws Exception {

        given(this.catalogService.getProduct("bigwheels"))
                .willReturn(new Catalog("1234", "9876","bigwheels"));
        this.mvc.perform(get("/products/sku").param("sku","1234").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()).andExpect(content().string("1234 9876 bigwheels"));

//      Object returnValue =  this.mockMvc.perform(get("/products/sku")
//              .param("sku","9876")
//              .accept(MediaType.parseMediaType("application/json;charset=utf8")))
//              .andExpect(status().isOk())
//              .andReturn();

 //       assertEquals("1234",catalog.getHcpc());
	}

//	@Test
//    @Ignore
//    public void testRedisCatalogRepository() throws Exception {
//        final CatalogRepository repo = RepositoryFactoryBuilder.builder().mock(CatalogRepository.class);
//        // add a record
//        Catalog cat =  new Catalog();
//        cat.setHcpc("1234");
//
//        cat.setProductName("massive stick");
//        cat.setSku("9876");
//
//        repo.save(cat);
//
//        // let us get it back
//        Assert.assertEquals("sku must be 9876","9876", repo.findBySku("9876").getSku());
//
//
//    }

}
