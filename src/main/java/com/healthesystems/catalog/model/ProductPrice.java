package com.healthesystems.catalog.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class ProductPrice {


    private static final long MIN_PRODUCT_PRICE = 0;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_price_id")
	private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
	@Column(name="price_locale")
	private PriceLocale priceLocale;


    @Min(MIN_PRODUCT_PRICE)
    private BigDecimal price;

    @NotNull
    private Date effectiveDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id")
	private Product product;

    private String customer;

    private String vendor;


	public ProductPrice(Integer id, BigDecimal price,  Date effectiveDate,
			PriceLocale priceLocale, String vendor, String customer) {
		super();
		this.id = id;
		this.price = price;
		this.vendor = vendor;
        this.customer = customer;
		this.effectiveDate = effectiveDate;
		this.priceLocale = priceLocale;
	}

	public ProductPrice() {
	}

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

	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public PriceLocale getPriceLocale() {
		return priceLocale;
	}
	public void setPriceLocale(PriceLocale priceLocale) {
		this.priceLocale = priceLocale;
	}

	public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id=" + id +
                ", price=" + price +
                ", effectiveDate=" + effectiveDate +
                ", priceLocale=" + priceLocale +
                ", product=" + product +
                ", customer='" + customer + '\'' +
                ", vendor='" + vendor + '\'' +
                '}';
    }
}
