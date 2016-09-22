package com.healthesystems.catalog;

import com.healthesystems.catalog.controller.CatalogController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceApplicationTests {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(new CatalogController()).build();
	}

	@Test
	public void testRestEndpoint() throws Exception {
        this.mockMvc.perform(get("/products/scoop").accept(MediaType.parseMediaType("application/json;charset=utf8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=utf8"));

	}

}
