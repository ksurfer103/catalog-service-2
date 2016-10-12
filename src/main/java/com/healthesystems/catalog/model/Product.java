package com.healthesystems.catalog.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer id;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="hcpc_discriminator")
    private HcpcDiscriminator hcpcDiscriminator;

    private String hcpcProcedureCode;

    private String sku;
    private String productName;

    //TODO: add Unit of Measure (look at existing system)

    //TODO: ADD CATEGORY, What is the category used for.

    public Product(String sku, String hcpcProcedureCode, String productName, Set<ProductPrice> productPrices, ProductCategory category, HcpcDiscriminator hcpcDiscriminator) {
        this.hcpcProcedureCode = hcpcProcedureCode;
        this.productName = productName;
        this.sku = sku;
        this.setProductPrices(productPrices);
        this.setCategory(category);
        this.setHcpcDiscriminator(hcpcDiscriminator);
    }

    // TODO Catalog Reference Key
    private String catalogReferenceKey;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("product")
    private Set<ProductPrice> productPrices = new HashSet<ProductPrice>();

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="category_id")
    private ProductCategory category;


    public Product() { }


    public String getHcpcProcedureCode() {
        return hcpcProcedureCode;
    }

    public void setHcpcProcedureCode(String hcpcProcedureCode) {
        this.hcpcProcedureCode = hcpcProcedureCode;
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

    public HcpcDiscriminator getHcpcDiscriminator() {
        return hcpcDiscriminator;
    }

    public void setHcpcDiscriminator(HcpcDiscriminator hcpcDiscriminator) {
        Assert.notNull(hcpcDiscriminator,"Must either be HCPC or CPT");
//TODO assert on a enum is equal        Assert.isTrue(hcpc)
        this.hcpcDiscriminator = hcpcDiscriminator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (!hcpcProcedureCode.equals(product.hcpcProcedureCode)) return false;
        if (!sku.equals(product.sku)) return false;
        return productName.equals(product.productName);

    }

    public String getCatalogReferenceKey() {
        return hcpcProcedureCode + "-" + sku;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + hcpcProcedureCode.hashCode();
        result = 31 * result + sku.hashCode();
        result = 31 * result + productName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", hcpcProcedureCode='" + hcpcProcedureCode + '\'' +
                ", sku='" + sku + '\'' +
                ", productName='" + productName + '\'' +
                ", catalogReferenceKey='" + catalogReferenceKey + '\'' +
                ", productPrices=" + productPrices +
                ", category=" + category +
                '}';
    }

    public String toJSON()  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            return ow.writeValueAsString(this);
        }
        catch (Exception e) {
            return "JSON Parse Error";
        }
    }

    
}