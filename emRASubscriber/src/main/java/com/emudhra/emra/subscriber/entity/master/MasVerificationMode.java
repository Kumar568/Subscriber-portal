package com.emudhra.emra.subscriber.entity.master;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="tbl_mas_verificationmode")

public class MasVerificationMode {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "name")
	    private String name;
	    
	    @Column(name="is_active")
	    private int isActive;

		/*
		 * Funtionality changes as listing all the types and modes at the same time. so
		 * commenting below relationship
		 */
//	    @ManyToMany(mappedBy = "masVerficationModes",
//	    		cascade=CascadeType.ALL,
//	    		fetch = FetchType.LAZY)
//	    @JsonIgnore
//	    private Set<MasVerficationType> masVerficationTypes = new HashSet<>();

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	
		public MasVerificationMode(Integer id, String name, int isActive, Set<MasVerficationType> masVerficationTypes) {
//			super();
			this.id = id;
			this.name = name;
			this.isActive = isActive;
		}

		
		public MasVerificationMode(Integer id) {
			super();
			this.id = id;
		}

		public MasVerificationMode() {
			//super();
			// TODO Auto-generated constructor stub
		}

		public int getIsActive() {
			return isActive;
		}

		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MasVerificationMode other = (MasVerificationMode) obj;
			return Objects.equals(id, other.id);
		}

		

}
