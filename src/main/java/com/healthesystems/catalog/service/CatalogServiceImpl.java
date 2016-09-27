package com.healthesystems.catalog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.repository.CatalogRepository;
import org.springframework.beans.factory.ObjectFactory;
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

   // @Autowired
    //MessageChannel toKafkaItemCreatedTopic;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ObjectFactory<Product> objectFactory;


    @Override
    public boolean isItemExist(Product product) {
        Product found = catalogRepository.findBySku(product.getSku());
        return (found != null);

    }

    @Override
    public List<Product> getProductByName(String productName) {
        return  catalogRepository.findByProductNameLike(productName);
    }

    @Override

    public List<Product> getProductByHcpc(String hcpc) {
        return  catalogRepository.findByHcpc(hcpc);
    }

    @Override
    public Product getProductBySku(String sku) {
        return  catalogRepository.findBySku(sku);
    }


    @Override
    public Product save(Product product) {
//        try {
//        			toKafkaItemCreatedTopic.send(new GenericMessage<>(objectMapper.writeValueAsString(product)));
//        		} catch (JsonProcessingException e) {
//
//                  /* Send Message to Error topic
//                    */
//        		}

        	return catalogRepository.save(product);
    }

}
