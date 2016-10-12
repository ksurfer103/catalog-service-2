package com.healthesystems.catalog.model;

import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * Created by apurdon on 10/11/16.
 */
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id")
    private Integer id;


    public Integer getId() {
        return id;
    }

    public ProductCategory(String categoryKey, String description) {

        this.setCategoryKey(categoryKey);
        this.setDescription(description);
    }

    public ProductCategory() {};

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        Assert.notNull(categoryKey,"ProductCategory key cannot be null");
        this.categoryKey = categoryKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Assert.notNull(description,"Descriptor key cannot be null");
        this.description = description;
    }

    private String categoryKey;

    private String description;


}
