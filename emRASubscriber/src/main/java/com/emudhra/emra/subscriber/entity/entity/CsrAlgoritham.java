package com.emudhra.emra.subscriber.entity.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_mas_csralgorithm")
public class CsrAlgoritham {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	
	@Column(name = "isactive", nullable = false)
	private int isActive;

	
	@OneToMany(mappedBy = "csrAlgoritham", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderDetails> orderDetails; 

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getIsActive() {
		return isActive;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	
	
}
