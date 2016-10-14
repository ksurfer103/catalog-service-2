package com.healthesystems.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthesystems.catalog.model.Product;
import com.healthesystems.catalog.repository.CatalogRepository;

/**
 * Created by apurdon on 8/22/16.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

   // @Autowired
    //MessageChannel toKafkaItemCreatedTopic;
 
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

    public List<Product> getProductByProcedureCode(String hcpc) {
        return  catalogRepository.findByProcedureCode(hcpc);
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
