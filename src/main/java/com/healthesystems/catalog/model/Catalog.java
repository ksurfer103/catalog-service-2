package com.healthesystems.catalog.model;



import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@RedisHash("products")
@Entity
public class Catalog  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;

    //@Indexed
    private String hcpc;

    //@Indexed
    private String sku;

    //@Indexed
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

    public Catalog(String hcpc, String sku, String name) {
        this.hcpc = hcpc;
        this.productName = name;
        this.sku = sku;
    }

}