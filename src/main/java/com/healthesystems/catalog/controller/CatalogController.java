package com.healthesystems.catalog.controller;
import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CatalogController {

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    CatalogService catalogService;


    @RequestMapping(path = "/products/sku", method= RequestMethod.GET)
    public Product getBySku(@RequestParam("sku") String sku){
        logger.info("get product by sku: {}",sku);
        return catalogService.getProductBySku(sku);
    }
    @RequestMapping(path="/products/productname",method= RequestMethod.GET)
    public List<Product> getProductByProductName(@RequestParam("productName") String productName) {
        logger.info("get product by name: {}",productName);
        return catalogService.getProductByName(productName);
    }

    @RequestMapping(path="/products/procedure-code",method= RequestMethod.GET)
    public List<Product> getProductByHcpc(@RequestParam("procedure-code") String hcpc) {
        logger.info("get product by HCPC: {}", hcpc);
        return catalogService.getProductByProcedureCode(hcpc);
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        if (catalogService.isItemExist(product)) {
            logger.info("A product with name " + product.getProductName() + " already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        catalogService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
