package com.healthesystems.catalog.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;


@RedisHash("products")
public class Catalog  {



    @Id
    private String id;

    @Indexed
    private String hcpc;

    @Indexed
    private String sku;

    @Indexed
    private String productName;


    public String getHcpc() {
        return hcpc;
    }

    public void setHcpc(String hcpc) {
        this.hcpc = hcpc;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



}