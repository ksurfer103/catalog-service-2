package com.healthesystems.catalog.controller;
import com.healthesystems.catalog.service.CatalogService;
import com.healthesystems.catalog.service.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthesystems.catalog.model.Catalog;
import com.healthesystems.catalog.repository.CatalogRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class CatalogController {

//    @Autowired
//    CatalogRepository catalogRepository;

    @Autowired
    CatalogService catalogService;



//    @RequestMapping(value="/{productName}", method= RequestMethod.GET)
//    public Catalog getCatalogByProductName(@PathVariable("productName") String productName){
//       return catalogService.getProduct(productName).get(0);
//    }

    @RequestMapping(path = "/sku", method= RequestMethod.GET)
    public Catalog getBySku(@RequestParam("sku") String sku){
        //Catalog catalog = new Catalog();
        //catalog.setHcpc("jkjkdf");

         //return catalog;
        return catalogService.getProductBySku(sku);
    }

    @RequestMapping(method= RequestMethod.GET)
    public Catalog getProductBySku(@RequestParam("sku") Optional <String> sku, @RequestParam("hcpc") Optional <String> hcpc,
                                   @RequestParam("name") Optional <String> name) {

        if (sku.isPresent()) return catalogService.getProductBySku(sku.get());
        if (hcpc.isPresent()) return catalogService.getProductByHcpc(hcpc.get()).get(0);
        return catalogService.getProduct(name.get());
    }





//    @RequestMapping(method= RequestMethod.GET)
//    public Catalog getProductName(@RequestParam("name") String name) {
//        return catalogService.getProduct(name).get(0);
//
//    }

//    @RequestMapping(value="/{sku}", method= RequestMethod.GET)
//    public Catalog getCatalogBySku(@PathVariable("sku") String sku){
//        return catalogService.getProductBySku(sku).get(0);
//    }
//
//    @RequestMapping(value="/{hcpc}", method= RequestMethod.GET)
//    public Catalog getCatalogByHcpc(@PathVariable("hcpc") String hcpc){
//        return catalogService.getProductByHcpc(hcpc).get(0);
//    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Void> createCatalog(@RequestBody Catalog catalog) {

        System.out.println("Catalog-" + catalog.getProductName());
        if (catalogService.isItemExist(catalog)) {
            System.out.println("A catalog with name " + catalog.getProductName() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        catalogService.save(catalog);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
