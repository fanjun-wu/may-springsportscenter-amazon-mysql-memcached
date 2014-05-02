package cloud.migration.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;





@Entity
@Table
public class Capability implements java.io.Serializable
{
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String resource;

	public Capability(String resource, String conditionCap,
			String discriptionCap) {
		
		this.resource = resource;
		this.conditionCap = conditionCap;
		this.discriptionCap = discriptionCap;
	}


	@Column
	private String conditionCap;
	@Column
	private String discriptionCap;
	 
	 

	//can't be EAGER
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "capability") 
	private Set<Court> court;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}





	public String getResource() {
		return resource;
	}





	public void setResource(String resource) {
		this.resource = resource;
	}





	public String getCondition() {
		return conditionCap;
	}





	public void setCondition(String condition) {
		this.conditionCap = condition;
	}





	public String getDiscription() {
		return discriptionCap;
	}





	public void setDiscription(String discription) {
		this.discriptionCap = discription;
	}



	public Capability(){
		
	}


	
	public void unsetId() {
		this.id = 0;	
	}

	

	public void setCourt(Set<Court> court) {
		this.court = court;
	}


	public Set<Court> getCourt() {
		if(this.court == null) {
				this.court = new HashSet<Court>();
		}
		return (Set<Court>) this.court;	
	}
	



	public void removeAllCourt(Set<Court> newCourt) {
		if(this.court == null) {
			return;
		}
		
		this.court.removeAll(newCourt);	
	}
	
 
	public void addCourt(Court newCourt) {
		if(this.court == null) {
			this.court = new HashSet<Court>();
		}
		
		if (this.court.add(newCourt))
			newCourt.addCapability(this);	
	}
	

	
	public void addAllCourt(Set<Court> newCourt) {
		if (this.court == null) {
			this.court = new HashSet<Court>();
		}
		for (Court tmp : newCourt)
			tmp.addCapability(this);
			
	}
	
	

	public void unsetResource() {
		this.resource = "";	
	}
	

	public void unsetCondition() {
		this.conditionCap = "";	
	}
	

	public void unsetDiscription() {
		this.discriptionCap = "";	
	}

	


	

public void removeCourt(Court oldCourt) {
		if(this.court == null)
			return;
		
		if (this.court.remove(oldCourt))
			oldCourt.removeCapability(this);
			
	}








public String getConditionCap() {
	return conditionCap;
}


public void setConditionCap(String conditionCap) {
	this.conditionCap = conditionCap;
}


public String getDiscriptionCap() {
	return discriptionCap;
}


public void setDiscriptionCap(String discriptionCap) {
	this.discriptionCap = discriptionCap;
}


@Override
public String toString() {
	return "Capability [id=" + id + ", resource=" + resource
			+ ", conditionCap=" + conditionCap + ", discriptionCap="
			+ discriptionCap + "]";
}

	
	
}
