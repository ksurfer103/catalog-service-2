package com.healthesystems.catalog.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Product {

    public static final String DEFAULT_DISCRIMINATOR = "MISC";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer id;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="discriminator")
    private Discriminator discriminator;

    private String procedureCode;

    private String sku;
    private String productName;


    //TODO: add Unit of Measure (look at existing system)



    public Product(String sku, String procedureCode, String productName, List<ProductPrice> productPrices, ProductCategory category, Discriminator discriminator) {
        this.setProcedureCode(procedureCode);
        this.productName = productName;
        this.sku = sku;
        this.setProductPrices(productPrices);
        this.setCategory(category);
        this.setDiscriminator(discriminator);
    }

    // TODO Catalog Reference Key
    private String catalogReferenceKey;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties("product")
    private List<ProductPrice> productPrices = new ArrayList<ProductPrice>();

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


    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCodeParam) {
        Assert.notNull(procedureCodeParam, "Procedure code cannot be null");
        this.procedureCode = procedureCodeParam;
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


    public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
	    for (ProductPrice productPrice : productPrices) {
	    	addProductPrice(productPrice);
		}
	}
	
	public void addProductPrice(ProductPrice productPrice){
		this.productPrices.add(productPrice);
		productPrice.setProduct(this);
	}

    public Discriminator getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(Discriminator discriminator) {

        if (discriminator==null){ discriminator=Discriminator.MISC;}
        this.discriminator = discriminator;
    }


    public String getCatalogReferenceKey() {
        return procedureCode + "-" + sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (discriminator != product.discriminator) return false;
        if (procedureCode != null ? !procedureCode.equals(product.procedureCode) : product.procedureCode != null)
            return false;
        if (sku != null ? !sku.equals(product.sku) : product.sku != null) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (catalogReferenceKey != null ? !catalogReferenceKey.equals(product.catalogReferenceKey) : product.catalogReferenceKey != null)
            return false;
        if (productPrices != null ? !productPrices.equals(product.productPrices) : product.productPrices != null)
            return false;
        return category != null ? category.equals(product.category) : product.category == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (discriminator != null ? discriminator.hashCode() : 0);
        result = 31 * result + (procedureCode != null ? procedureCode.hashCode() : 0);
        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (catalogReferenceKey != null ? catalogReferenceKey.hashCode() : 0);
        result = 31 * result + (productPrices != null ? productPrices.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", discriminator=" + discriminator +
                ", procedureCode='" + procedureCode + '\'' +
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