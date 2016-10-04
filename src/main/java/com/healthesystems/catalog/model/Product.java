package com.healthesystems.catalog.model;



import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer id;
    private String hcpc;
    private String sku;
    private String productName;

    //TODO: add Unit of Measure (look at existing system)

    //TODO: ADD CATEGORY, What is the category used for.

    public Product(String sku,String hcpc,String productName,Set<ProductPrice> productPrices) {
        this.hcpc = hcpc;
        this.productName = productName;
        this.sku = sku;
        setProductPrices(productPrices);
    }


    private String catalogReferenceKey;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("product")
    private Set<ProductPrice> productPrices = new HashSet<ProductPrice>();

    public Product() { }


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


    public Set<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(Set<ProductPrice> productPrices) {
	    for (ProductPrice productPrice : productPrices) {
	    	addProductPrice(productPrice);
		}
	}
	
	public void addProductPrice(ProductPrice productPrice){
		this.productPrices.add(productPrice);
		productPrice.setProduct(this);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (!hcpc.equals(product.hcpc)) return false;
        if (!sku.equals(product.sku)) return false;
        return productName.equals(product.productName);

    }

    public String getCatalogReferenceKey() {
        return hcpc + "-" + sku;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + hcpc.hashCode();
        result = 31 * result + sku.hashCode();
        result = 31 * result + productName.hashCode();
        return result;
    }

    
    @Override
	public String toString() {
		return "Product [productId=" + id + ", hcpc=" + hcpc + ", sku=" + sku + ", productName=" + productName
				+ "]";
	}

    
}