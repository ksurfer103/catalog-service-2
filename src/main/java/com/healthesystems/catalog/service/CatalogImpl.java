package com.healthesystems.catalog.service;

import com.healthesystems.catalog.model.Catalog;
import com.healthesystems.catalog.repository.CatalogRepository;
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
        return catalogRepository.save(catalog);
    }
}
