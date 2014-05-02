package cloud.migration.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table
public class Hall implements java.io.Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private int openTime;
	@Column
	private int closeTime;
	@Column
	private String introduction;
	
	

	public void setCourts(Set<Court> courts) {
		this.courts = courts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOpenTime() {
		return openTime;
	}

	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	public int getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(int closeTime) {
		this.closeTime = closeTime;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn
	private Admin admin;
	
	@OneToMany(targetEntity = Court.class, cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="hall")	
	private Set<Court> courts;

	public Hall(){
		
	}

	
	public void basicSetAdmin(Admin myAdmin) {
		if (this.admin != myAdmin) {
			if (myAdmin != null){
				if (this.admin != myAdmin) {
					Admin oldadmin = this.admin;
					this.admin = myAdmin;
					if (oldadmin != null)
						oldadmin.unsetHall();
				}
			}
		}	
	}
	
	

	public Admin getAdmin() {
		return this.admin;	
	}

	public Set<Court> getCourts() {
		if(this.courts == null) {
				this.courts = new HashSet<Court>();
		}
		return (Set<Court>) this.courts;	
	}
	
	
	public void addAllCourt(Set<Court> newCourt) {
		if (this.courts == null) {
			this.courts = new HashSet<Court>();
		}
		for (Court tmp : newCourt)
			tmp.setHall(this);
			
	}
	
	public void removeAllCourt(Set<Court> newCourt) {
		if(this.courts == null) {
			return;
		}
		
		this.courts.removeAll(newCourt);	
	}
	
	

	
	
	
	public void addCourt(Court newCourt) {
		if(this.courts == null) {
			this.courts = new HashSet<Court>();
		}
		
		if (this.courts.add(newCourt))
			newCourt.basicSetHall(this);	
	}
	
	
	

	
	
	public void setAdmin(Admin myAdmin) {
		this.basicSetAdmin(myAdmin);
		myAdmin.basicSetHall(this);
			
	}

	public void unsetAdmin() {
		if (this.admin == null)
			return;
		Admin oldadmin = this.admin;
		this.admin = null;
		oldadmin.unsetHall();	
	}
	
	

	public void removeCourt(Court oldCourt) {
		if(this.courts == null)
			return;
		
		if (this.courts.remove(oldCourt))
			oldCourt.unsetHall();
			
	}

	

	@Override
	public String toString() {
		return "Hall [id=" + id + ", name=" + name + ", openTime=" + openTime
				+ ", closeTime=" + closeTime + ", introduction=" + introduction+ "]";
	}

	public Hall(String name, int openTime, int closeTime, String introduction) {		
		this.name = name;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.introduction = introduction;
	}

	
}

