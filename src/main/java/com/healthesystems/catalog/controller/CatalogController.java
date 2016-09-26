package com.healthesystems.catalog.controller;
import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CatalogController {

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    CatalogService catalogService;


    @RequestMapping(path = "/sku", method= RequestMethod.GET)
    public Product getBySku(@RequestParam("sku") String sku){
        return catalogService.getProductBySku(sku);
    }

    @RequestMapping(method= RequestMethod.GET)
    public Product getProductBySku(@RequestParam("sku") Optional <String> sku, @RequestParam("hcpc") Optional <String> hcpc,
                                   @RequestParam("name") Optional <String> name) {

        if (sku.isPresent()) return catalogService.getProductBySku(sku.get());
        if (hcpc.isPresent()) return catalogService.getProductByHcpc(hcpc.get()).get(0);
        return catalogService.getProduct(name.get());
    }


    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Void> createCatalog(@RequestBody Product product) {

        System.out.println("Product-" + product.getProductName());
        if (catalogService.isItemExist(product)) {
            System.out.println("A product with name " + product.getProductName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        catalogService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
