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
@Table(name ="tbl_mas_verificationtype")

public class MasVerficationType {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "name")
	    private String name;

	    @Column(name = "is_active")
	    private int isActive;

	 
	    /*
		 * Funtionality changes as listing all the types and modes at the same time. so
		 * commenting below relationship
		 */
//	    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
//	    @JoinTable(name = "tbl_mas_map_verification_type_mode1", joinColumns =
//	    @JoinColumn(name = "type_id"),
//	    inverseJoinColumns =  @JoinColumn(name = "mode_id"))
//	    private Set<MasVerificationMode> masVerficationModes = new HashSet<>();

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

		public MasVerficationType(Integer id, String name, int isActive,
				Set<MasVerificationMode> masVerficationModes) {
		//	super();
			this.id = id;
			this.name = name;
			this.isActive = isActive;
		}

		public MasVerficationType() {
		//	super();
			// TODO Auto-generated constructor stub
		}
		
		public MasVerficationType(Integer id) {
			super();
			this.id = id;
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
			MasVerficationType other = (MasVerficationType) obj;
			return Objects.equals(id, other.id);
		}

		
	

}
