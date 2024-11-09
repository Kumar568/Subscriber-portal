package com.emudhra.emra.subscriber.entity.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.emudhra.emra.subscriber.entity.master.MasProductCategoryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="tbl_product_master")
public class ProductMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "product_name")
	private String productname;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_Category_Type_Id", referencedColumnName = "id")
	@JsonBackReference
	private MasProductCategoryType masProductCategoryType;
	
	@Column(name = "Is_Active")
	private int isActive;
	@Column(name = "created_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	@CreationTimestamp
	private LocalDateTime createddate;
	@Column(name = "createdBy")
	private long createdBy;
	@Column(name = "createdBy_Name")
	private String createdBy_Name;
	@Column(name = "created_Ip")
	private String createdIp;
	@Column(name = "updated_By")
	private long updatedBy;
	@Column(name = "updated_Date")
	private LocalDateTime updatedDate;
	@Column(name = "certificate_for")
	private int certificatefor;
	
	 @OneToMany(
		        mappedBy = "productMaster",
		        cascade = CascadeType.ALL   
		    )
		@JsonManagedReference
		    private List<MapCeritificateValidity> ceritificateValidity = new ArrayList<>();
	 
//	 
//		@OneToMany(mappedBy = "productMaster", cascade = CascadeType.ALL)
//		@JsonManagedReference
//		private List<VettingProfile> vettingProfile; 
//
//
//		
//	
//	public List<VettingProfile> getVettingProfile() {
//			return vettingProfile;
//		}
//		public void setVettingProfile(List<VettingProfile> vettingProfile) {
//			this.vettingProfile = vettingProfile;
//		}
	public List<MapCeritificateValidity> getCeritificateValidity() {
		return ceritificateValidity;
	}
	public void setCeritificateValidity(List<MapCeritificateValidity> ceritificateValidity) {
		this.ceritificateValidity = ceritificateValidity;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public MasProductCategoryType getMasProductCategoryType() {
		return masProductCategoryType;
	}
	public void setMasProductCategoryType(MasProductCategoryType masProductCategoryType) {
		this.masProductCategoryType = masProductCategoryType;
	}

	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public LocalDateTime getCreateddate() {
		return createddate;
	}
	public void setCreateddate(LocalDateTime createddate) {
		this.createddate = createddate;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedBy_Name() {
		return createdBy_Name;
	}
	public void setCreatedBy_Name(String createdBy_Name) {
		this.createdBy_Name = createdBy_Name;
	}
	public String getCreatedIp() {
		return createdIp;
	}
	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getCertificatefor() {
		return certificatefor;
	}
	public void setCertificatefor(int certificatefor) {
		this.certificatefor = certificatefor;
	}
	public ProductMaster() {
	//	super();
		// TODO Auto-generated constructor stub
	}
	public ProductMaster(long id, String productname, MasProductCategoryType masProductCategoryType, int isActive,
			LocalDateTime createddate, long createdBy, String createdBy_Name, String createdIp, long updatedBy,
			LocalDateTime updatedDate, int certificatefor, List<MapCeritificateValidity> ceritificateValidity) {
		super();
		this.id = id;
		this.productname = productname;
		this.masProductCategoryType = masProductCategoryType;
		this.isActive = isActive;
		this.createddate = createddate;
		this.createdBy = createdBy;
		this.createdBy_Name = createdBy_Name;
		this.createdIp = createdIp;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.certificatefor = certificatefor;
		this.ceritificateValidity = ceritificateValidity;
	}


	
	
}
