package com.healthesystems.catalog.model;



import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@RedisHash("products")
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;

    //@Indexed
    private String hcpc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != null ? !productId.equals(product.productId) : product.productId != null) return false;
        if (!hcpc.equals(product.hcpc)) return false;
        if (!sku.equals(product.sku)) return false;
        return productName.equals(product.productName);

    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + hcpc.hashCode();
        result = 31 * result + sku.hashCode();
        result = 31 * result + productName.hashCode();
        return result;
    }

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

    public Product(@JsonProperty("sku") String sku, @JsonProperty("hcpc") String hcpc, @JsonProperty("productName") String productName) {
        this.hcpc = hcpc;
        this.productName = productName;
        this.sku = sku;
    }
    public Product() {

    }

}