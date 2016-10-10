package com.healthesystems.catalog.model;

import org.springframework.util.Assert;

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


    private static final long MIN_PRODUCT_PRICE = 1;


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
		this.setPrice(price);
		this.setVendor(vendor);
        this.setCustomer(customer);
		this.setEffectiveDate(effectiveDate);
		this.setPriceLocale(priceLocale);
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
		Assert.notNull(price, "Price can not be null");
		Assert.isTrue(price.doubleValue() > 0.00, "Price must be greater than $0");

		this.price = price;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
	    Assert.notNull(effectiveDate,"Effective date cannot be null");
        this.effectiveDate = effectiveDate;
	}

	public PriceLocale getPriceLocale() {
		return priceLocale;
	}
	public void setPriceLocale(PriceLocale priceLocale) {
        Assert.notNull(priceLocale,"Price Locale can not be null, it can be XX");
        this.priceLocale = priceLocale;
	}

	public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        Assert.notNull(vendor, "Vendor can not be null, it can be xxxxxxxxxxx");
        this.vendor = vendor;
    }

    public String getCustomer() {

        return customer;
    }

    public void setCustomer(String customer)
    {
        Assert.notNull(customer, "Customer can not be null, it can be xxxxxxxxxxx");
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
