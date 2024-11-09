package com.emudhra.emra.subscriber.entity.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="tbl_map_ceritificate_validity")
public class MapCeritificateValidity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)

	@JoinColumn(name = "product_id")
	@JsonBackReference
	private ProductMaster productMaster;

	@ManyToOne(fetch = FetchType.EAGER)

	@JoinColumn(name = "validity_id")
	@JsonBackReference
	private ValidityMaster validityMaster;

	@Column(name = "is_active")
	private int isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductMaster getProductMaster() {
		return productMaster;
	}

	public void setProductMaster(ProductMaster productMaster) {
		this.productMaster = productMaster;
	}

	public ValidityMaster getValidityMaster() {
		return validityMaster;
	}

	public void setValidityMaster(ValidityMaster validityMaster) {
		this.validityMaster = validityMaster;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public MapCeritificateValidity() {
		//super();
		// TODO Auto-generated constructor stub
	}

	public MapCeritificateValidity(Long id, ProductMaster productMaster, ValidityMaster validityMaster, int isActive) {
		//super();
		this.id = id;
		this.productMaster = productMaster;
		this.validityMaster = validityMaster;
		this.isActive = isActive;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		MapCeritificateValidity that = (MapCeritificateValidity) o;
		return Objects.equals(productMaster, that.productMaster) && Objects.equals(validityMaster, that.validityMaster);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productMaster, validityMaster);
	}

}
