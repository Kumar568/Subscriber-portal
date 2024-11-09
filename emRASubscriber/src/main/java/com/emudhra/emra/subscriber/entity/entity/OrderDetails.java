package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.emudhra.emra.subscriber.entity.master.MasProductCategoryType;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	 @JoinColumn(name = "customer_id", referencedColumnName = "id" ,nullable = false)
    @JsonBackReference
    private Customers customers;

//	@Column(name = "csr_algoritham", nullable = false)
//    private Integer csrAlgoritham;
	@ManyToOne
    @JoinColumn(name = "csr_algoritham", referencedColumnName = "id" ,nullable = true)
    @JsonBackReference
    private CsrAlgoritham csrAlgoritham;
	
	@Column(name = "originator", nullable = false)
	private int originator;
	
	@Column(name = "product_id", nullable = false)
	private Long productId;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "validity", nullable = false)
	private int validity;
	
	@Column(name = "status", nullable = false)
	private int status;
	
	@ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id" ,nullable = false)
    @JsonBackReference
    private OrganizationDetails organizationDetails;
	
	@OneToOne
	@JoinColumn(name = "map_certificate_validity_id", referencedColumnName = "id", nullable = false)
	private MapCeritificateValidity mapCertificateValidity;


	@ManyToOne
    @JoinColumn(name = "mas_product_category_type_id", referencedColumnName = "id" ,nullable = false)
    @JsonBackReference
    private MasProductCategoryType masProductCategoryType;
	
	
	

	@Column(name = "created_date", nullable = false)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "createdip", nullable = true, length = 45)
	private String createdIp;

	@Column(name = "createdby", nullable = true)
	private int createdBy;
	
	@Column(name = "updated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updatedDate;
	
	@Column(name = "updatedip", nullable = true, length = 45)
	private String updatedIp;

	@Column(name = "updatedby", nullable = true)
	private int updatedBy;
	
	
	
	public Long getId() {
		return id;
	}


	public Customers getCustomers() {
		return customers;
	}



	public int getOriginator() {
		return originator;
	}


	


	public int getQuantity() {
		return quantity;
	}


	public int getValidity() {
		return validity;
	}


	public int getStatus() {
		return status;
	}


	public OrganizationDetails getOrganizationDetails() {
		return organizationDetails;
	}


	public MapCeritificateValidity getMapCertificateValidity() {
		return mapCertificateValidity;
	}


	public MasProductCategoryType getMasProductCategoryType() {
		return masProductCategoryType;
	}


	


	public void setId(Long id) {
		this.id = id;
	}


	public void setCustomers(Customers customers) {
		this.customers = customers;
	}




	public void setOriginator(int originator) {
		this.originator = originator;
	}




	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setValidity(int validity) {
		this.validity = validity;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setOrganizationDetails(OrganizationDetails organizationDetails) {
		this.organizationDetails = organizationDetails;
	}


	public void setMapCertificateValidity(MapCeritificateValidity mapCertificateValidity) {
		this.mapCertificateValidity = mapCertificateValidity;
	}


	public void setMasProductCategoryType(MasProductCategoryType masProductCategoryType) {
		this.masProductCategoryType = masProductCategoryType;
	}





	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public String getCreatedIp() {
		return createdIp;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}


	public String getUpdatedIp() {
		return updatedIp;
	}


	public int getUpdatedBy() {
		return updatedBy;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	public void setUpdatedIp(String updatedIp) {
		this.updatedIp = updatedIp;
	}


	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	public CsrAlgoritham getCsrAlgoritham() {
		return csrAlgoritham;
	}


	public void setCsrAlgoritham(CsrAlgoritham csrAlgoritham) {
		this.csrAlgoritham = csrAlgoritham;
	}
	
	
	
}
