package com.emudhra.emra.subscriber.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
@Entity
@Table(name = "tbl_mas_product_category_type")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasProductCategoryType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "product_category_id")
	private int productcategoryid;
	@Column(name = "display_id")
	private long displayid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductcategoryid() {
		return productcategoryid;
	}
	public void setProductcategoryid(int productcategoryid) {
		this.productcategoryid = productcategoryid;
	}
	public long getDisplayid() {
		return displayid;
	}
	public void setDisplayid(long displayid) {
		this.displayid = displayid;
	}
	public MasProductCategoryType(int id, String name, int productcategoryid, long displayid) {
		//super();
		this.id = id;
		this.name = name;
		this.productcategoryid = productcategoryid;
		this.displayid = displayid;
	}
	public MasProductCategoryType() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
	

}
