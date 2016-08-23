package com.healthesystems.catalog.controller;
import com.healthesystems.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthesystems.catalog.model.Catalog;
import com.healthesystems.catalog.repository.CatalogRepository;

import java.util.List;

@RestController
@RequestMapping("/item")
public class CatalogController {

//    @Autowired
//    CatalogRepository catalogRepository;
    @Autowired
    CatalogService catalogService;



    @RequestMapping(value="/{item}", method= RequestMethod.GET)
    public Catalog getCatalogByItemName(@PathVariable("item") String item){
       return catalogService.getItem(item).get(0);
    }


    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Void> createCatalog(@RequestBody Catalog catalog) {


        if (catalogService.isItemExist(catalog)) {
            System.out.println("A catalog with name " + catalog.getItemName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        catalogService.save(catalog);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);

    }

}
