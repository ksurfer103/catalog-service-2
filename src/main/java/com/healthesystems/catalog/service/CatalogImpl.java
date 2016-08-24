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
public class CatalogImpl implements CatalogService {

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
        List<Catalog> found = catalogRepository.findByItemName(catalog.getItemName());
        return (!found.isEmpty());

    }

    @Override
    public List<Catalog> getItem(String itemName) {
        return  catalogRepository.findByItemName(itemName);
    }

    @Override
    public Catalog save(Catalog catalog) {
        try {
        			toKafkaItemCreatedTopic.send(new GenericMessage<>(objectMapper.writeValueAsString(catalog)));
        		} catch (JsonProcessingException e) {

                  /* Send Message to Error topic
                    */
        		}
        	return catalogRepository.save(catalog);
    }

}
