package com.emudhra.emra.subscriber.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "tbl_mas_country")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class MasCountry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "country_alphacode", nullable = true, length = 10)
	private String countryAlphaCode;

//@OneToOne(mappedBy = "masCountry")
//@JsonManagedReference
//private Applications applications;

	

//	public Applications getApplications() {
//	return applications;
//}
//
//public void setApplications(Applications applications) {
//	this.applications = applications;
//}



	@Column(name = "isd_code",nullable = true, length = 10)
	private String ISDCode;

	@Column(name = "country_name", nullable = true, length = 60)
	private String countryName;


	@Column(name = "geography_id", nullable = true)
	private int geographyId;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryAlphaCode() {
		return countryAlphaCode;
	}

	public void setCountryAlphaCode(String countryAlphaCode) {
		this.countryAlphaCode = countryAlphaCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getGeographyId() {
		return geographyId;
	}

	public void setGeographyId(int geographyId) {
		this.geographyId = geographyId;
	}

	public String getISDCode() {
		return ISDCode;
	}

	public void setISDCode(String iSDCode) {
		ISDCode = iSDCode;
	}


}
