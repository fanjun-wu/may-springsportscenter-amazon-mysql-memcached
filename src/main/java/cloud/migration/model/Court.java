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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;



@Entity
@Table
public class Court implements java.io.Serializable
{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@Column
	private String introduction;
	
	@OneToMany(mappedBy = "court",targetEntity = Reservation.class,fetch=FetchType.EAGER)
	private Set<Reservation> reservation;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void unsetIntroduction() {
		this.introduction = "";	
	}
	
	public void unsetId() {
		this.id = 0;	
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Hall hall;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	private Set<Capability> capability;
	
	
	public Court(){
		
	}

	public void basicSetHall(Hall myHall) {
		if (this.hall != myHall) {
			if (myHall != null){
				if (this.hall != myHall) {
					Hall oldhall = this.hall;
					this.hall = myHall;
					if (oldhall != null)
						oldhall.removeCourt(this);
				}
			}
		}	
	}
	
	
	
	
	public Hall getHall() {
		return this.hall;	
	}
	
	public Set<Capability> getCapability() {
		if(this.capability == null) {
				this.capability = new HashSet<Capability>();
		}
		return (Set<Capability>) this.capability;	
	}
	
	
	

	public void addAllCapability(Set<Capability> newCapability) {
		if (this.capability == null) {
			this.capability = new HashSet<Capability>();
		}
		for (Capability tmp : newCapability)
			tmp.addCourt(this);
			
	}
	
	
	public void removeAllCapability(Set<Capability> newCapability) {
		if(this.capability == null) {
			return;
		}
		
		this.capability.removeAll(newCapability);	
	}
	
	
	
	
	
	
	
	public void setHall(Hall myHall) {
		this.basicSetHall(myHall);
		myHall.addCourt(this);	
	}
	
	
	public void addCapability(Capability newCapability) {
		if(this.capability == null) {
			this.capability = new HashSet<Capability>();
		}
		
		if (this.capability.add(newCapability))
			newCapability.addCourt(this);	
	}
	
	
	
	
	
	
	
	public void unsetHall() {
		if (this.hall == null)
			return;
		Hall oldhall = this.hall;
		this.hall = null;
		oldhall.removeCourt(this);	
	}
	
public void removeCapability(Capability oldCapability) {
		if(this.capability == null)
			return;
		
		if (this.capability.remove(oldCapability))
			oldCapability.removeCourt(this);
			
	}
	
	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}

	public Set<Reservation> getReservation() {
		if(this.reservation == null) {
				this.reservation = new HashSet<Reservation>();
		}
		return (Set<Reservation>) this.reservation;	
	}

	public void addAllReservation(Set<Reservation> newReservation) {
		if (this.reservation == null) {
			this.reservation = new HashSet<Reservation>();
		}
		for (Reservation tmp : newReservation)
			tmp.setCourt(this);
			
	}

	public void removeAllReservation(Set<Reservation> newReservation) {
		if(this.reservation == null) {
			return;
		}
		
		this.reservation.removeAll(newReservation);	
	}
	

	public void addReservation(Reservation newReservation) {
		if(this.reservation == null) {
			this.reservation = new HashSet<Reservation>();
		}
		
		if (this.reservation.add(newReservation))
			newReservation.basicSetCourt(this);	
	}
	
	public void removeReservation(Reservation oldReservation) {
		if(this.reservation == null)
			return;
		
		if (this.reservation.remove(oldReservation))
			oldReservation.unsetCourt();
			
	}
	

	
	
	
	public Court(String name, String introduction) {		
		this.name = name;
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setCapability(Set<Capability> capability) {
		this.capability = capability;
	}

	
	
	
	
}

