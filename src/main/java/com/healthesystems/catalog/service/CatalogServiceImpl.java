package com.healthesystems.catalog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthesystems.catalog.model.Catalog;
import com.healthesystems.catalog.repository.CatalogRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by apurdon on 8/22/16.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    MessageChannel toKafkaItemCreatedTopic;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ObjectFactory<Catalog> objectFactory;


    @Override
    public boolean isItemExist(Catalog catalog) {
       Catalog found = catalogRepository.findByProductName(catalog.getProductName());
        return (found != null);

    }

    @Override
    public Catalog getProduct(String productName) {
        return  catalogRepository.findByProductName(productName);
    }

    @Override
    public List<Catalog> getProductByHcpc(String hcpc) {
        return  catalogRepository.findByHcpc(hcpc);
    }

    @Override
    public Catalog getProductBySku(String sku) {
        return  catalogRepository.findBySku(sku);
    }

    @Override
    public Catalog getCatalogById(String id) {
        return catalogRepository.findByProductId(id);
    }



    @Override
    public Catalog save(Catalog catalog) {
//        try {
//        			toKafkaItemCreatedTopic.send(new GenericMessage<>(objectMapper.writeValueAsString(catalog)));
//        		} catch (JsonProcessingException e) {
//
//                  /* Send Message to Error topic
//                    */
//        		}
        	return catalogRepository.save(catalog);
    }

}
