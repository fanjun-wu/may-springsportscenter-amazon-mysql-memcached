package cloud.migration.model;
import java.util.Date;
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

import org.hibernate.annotations.Type;


 
@Entity
@Table
public class TimeInterval implements java.io.Serializable
{
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int startTime;
	
	@Column
	private Date date;
	 
	@ManyToMany(mappedBy = "timeInterval",fetch=FetchType.EAGER)	
	private Set<Reservation> reservation;
	 	
	public TimeInterval(){
		
	}
 
	
	public TimeInterval(int startTime, Date date) {		
		this.startTime = startTime;
		this.date = date;
	}


	public TimeInterval(int start)
	{
		startTime=start;		
	}


	public Set<Reservation> getReservation() {
		if(this.reservation == null) {
				this.reservation = new HashSet<Reservation>();
		}
		return (Set<Reservation>) this.reservation;	
	}
	
	 
	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}


	public void addAllReservation(Set<Reservation> newReservation) {
		if (this.reservation == null) {
			this.reservation = new HashSet<Reservation>();
		}
		for (Reservation tmp : newReservation)
			tmp.addTimeInterval(this);
			
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
			newReservation.addTimeInterval(this);	
	}
	 
	public void removeReservation(Reservation oldReservation) {
		if(this.reservation == null)
			return;
		
		if (this.reservation.remove(oldReservation))
			oldReservation.removeTimeInterval(this);
			
	}
	
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void unsetStartTime() {
		this.startTime = 0;	
	}
	
	 
	public void unsetDate() {
		this.date = new Date();	
	}
	 

	
	 
	public void unsetId() {
		this.id = 0;	
	}


	
	@Override
	public String toString() {
		return "from "+startTime+"h to "+(startTime+1)+"h, "+date.toString().split(" ")[0];
	
	}
	

	
}

