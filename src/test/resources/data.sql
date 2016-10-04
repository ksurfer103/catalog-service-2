    /*
    Integer id, BigDecimal price,  Date effectiveDate,
			String priceLocale, String vendor, String customer
     */

insert into product(product_id,sku,hcpc,product_Name)
values(1,'1234','9876','special wheelchair');

insert into product_price(product_id,price,customer,effective_date, price_locale,vendor)
values(1,'100.00','LIBERTY','2016-01-01 00:00:00.000','CA','HomeLink');

insert into product(product_id,sku,hcpc,product_Name)
values(2,'1235','9876','special wheelchair');

insert into product_price(product_id,price,customer,effective_date, price_locale,vendor)
values(2,'100.00','LIBERTY','2016-01-01 00:00:00.000','CA','HomeLink');

insert into product(product_id,sku,hcpc,product_Name)
values('3','1236','9876','Super special wheelchair');

insert into product_price(product_id,price,customer,effective_date, price_locale,vendor)
values(3,'100.00','LIBERTY','2016-01-01 00:00:00.000','CA','HomeLink');

insert into product(product_id,hcpc,sku,product_Name)
values(4,'ERD','4','ENTITY RELATIONAL DIAGRAM');

insert into product_price(product_id,price,customer,effective_date, price_locale,vendor)
values(4,'100.00','LIBERTY','2016-01-01 00:00:00.000','CA','HomeLink');

insert into product(product_id,hcpc,sku,product_Name)
values(5,'WSD','5','WEB SEQUENCE DIAGRAM');

insert into product_price(product_id,price,customer,effective_date, price_locale,vendor)
values(5,'100.00','LIBERTY','2016-01-01 00:00:00.000','CA','HomeLink');


