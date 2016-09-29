package com.healthesystems.catalog.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductPrice {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_price_id")
	private Integer id;
    private BigDecimal price;
    @Column(name="product_price_type")
    @Enumerated(EnumType.STRING)
	private ProductPriceType priceType;
	private Date effectiveDate;
	private String priceLocale;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ProductPriceType getPriceType() {
		return priceType;
	}
	public void setPriceType(ProductPriceType priceType) {
		this.priceType = priceType;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getPriceLocale() {
		return priceLocale;
	}
	public void setPriceLocale(String priceLocale) {
		this.priceLocale = priceLocale;
	}
	
	
}
