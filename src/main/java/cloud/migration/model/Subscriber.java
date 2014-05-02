package cloud.migration.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Subscriber extends Person implements java.io.Serializable
{


	@Column
	private String authenKey;
	
	@OneToMany(targetEntity = Reservation.class,mappedBy = "subscriber",fetch=FetchType.EAGER)
	private Set<Reservation> reservation;

	public Subscriber(){
		
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
			tmp.setSubscriber(this);
			
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
			newReservation.basicSetSubscriber(this);	
	}
	
		
	
	public void removeReservation(Reservation oldReservation) {
		if(this.reservation == null)
			return;
		
		if (this.reservation.remove(oldReservation))
			oldReservation.unsetSubscriber();
			
	}
	
	public Subscriber(String name, String email, String telephone,
			String gender, int age,String authenKey) {
		super(name, email, telephone, gender, age);
		this.authenKey=authenKey;
		// TODO Auto-generated constructor stub
	}


	public String getAuthenKey() {
		return authenKey;
	}


	public void setAuthenKey(String authenKey) {
		this.authenKey = authenKey;
	}


	public void unsetAuthenKey() {
		this.authenKey = "";	
	}
	
	
	
	@Override
	public String toString() {
		return "subscriber: "+getName()+", "+getEmail();
	}

	
}

