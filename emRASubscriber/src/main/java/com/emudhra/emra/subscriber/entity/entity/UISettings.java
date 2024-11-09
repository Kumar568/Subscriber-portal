package com.emudhra.emra.subscriber.entity.entity;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tbl_ui_settings")
public class UISettings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Productname is required")
	@Column(name = "product_name", nullable = false, length = 50)
	private String productname;

	@Column(name = "main_footer_content", nullable = true, length = 50)
	private String footercontent;
	
	@NotEmpty(message = "Productlogo is required")
	@Lob
	@Column(name = "product_logo", nullable = false)
	private byte[] productlogo;
	
	@NotEmpty(message = "Favourite icon is required")
	@Lob
	@Column(name = "fav_icon_image", nullable = false)
	private byte[] favouriteicon;

	@Lob
	@Column(name = "footer_logo", nullable = false)
	private byte[] footerlogo;

	@NotEmpty(message = "Footer left content is required")
	@Column(name = "email_footer_left_content", nullable = true, length = 200)
	private String footerleftcontent;
	
	@NotEmpty(message = "Footer right content is required")
	@Column(name = "email_footer_right_content", nullable = true, length = 200)
	private String footerrightcontent;

	@Column(name = "createdby", nullable = true, length = 5)
	private int createdby;

	@Column(name = "createdip", nullable = false, length = 25)
	private String createdip;

	@Column(name = "created_date", nullable = false)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime craeteddate;

	@Column(name = "updatedby", nullable = true, length = 5)
	private int updatedby;

	@Column(name = "updatedip", nullable = true, length = 25)
	private String updatedip;

	@Column(name = "updated_date", nullable = true)
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime updateddate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public byte[] getProductlogo() {
		return productlogo;
	}

	public void setProductlogo(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			this.productlogo = file.getBytes();
		}
	}

	public byte[] getFavouriteicon() {
		return favouriteicon;
	}

	public void setFavouriteicon(MultipartFile file) throws IOException {

		if (!file.isEmpty()) {
			this.favouriteicon = file.getBytes();
		}
	}

	public byte[] getFooterlogo() {
		return footerlogo;
	}

	public void setFooterlogo(byte[] file) throws IOException {

		this.footerlogo = file;

	}

	public String getFootercontent() {
		return footercontent;
	}

	public void setFootercontent(String footercontent) {
		this.footercontent = footercontent;
	}

	public String getFooterleftcontent() {
		return footerleftcontent;
	}

	public void setFooterleftcontent(String footerleftcontent) {
		this.footerleftcontent = footerleftcontent;
	}

	public String getFooterrightcontent() {
		return footerrightcontent;
	}

	public void setFooterrightcontent(String footerrightcontent) {
		this.footerrightcontent = footerrightcontent;
	}

	public String getCreatedip() {
		return createdip;
	}

	public void setCreatedip(String createdip) {
		this.createdip = createdip;
	}

	public LocalDateTime getCraeteddate() {
		return craeteddate;
	}

	public void setCraeteddate(LocalDateTime craeteddate) {
		this.craeteddate = craeteddate;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public String getUpdatedip() {
		return updatedip;
	}

	public void setUpdatedip(String updatedip) {
		this.updatedip = updatedip;
	}

	public LocalDateTime getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(LocalDateTime updateddate) {
		this.updateddate = updateddate;
	}

}
