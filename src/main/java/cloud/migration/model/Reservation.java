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



 
@Entity
@Table
public class Reservation implements java.io.Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String discription;
	@Column
	private String tips;
	 
	@ManyToOne(targetEntity = Subscriber.class,fetch=FetchType.EAGER)
	private Subscriber subscriber;
	 
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	private Set<TimeInterval> timeInterval;
	 
	@ManyToOne(targetEntity = Court.class,fetch=FetchType.EAGER)
	private Court court;
	 
	
 
	public void setTimeInterval(Set<TimeInterval> timeInterval) {
		this.timeInterval = timeInterval;
	}

	public Reservation(){
		
	}
 
	public void basicSetSubscriber(Subscriber mySubscriber) {
		if (this.subscriber != mySubscriber) {
			if (mySubscriber != null){
				if (this.subscriber != mySubscriber) {
					Subscriber oldsubscriber = this.subscriber;
					this.subscriber = mySubscriber;
					if (oldsubscriber != null)
						oldsubscriber.removeReservation(this);
				}
			}
		}	
	}
	
	 
	public void basicSetCourt(Court myCourt) {
		if (this.court != myCourt) {
			if (myCourt != null){
				if (this.court != myCourt) {
					Court oldcourt = this.court;
					this.court = myCourt;
					if (oldcourt != null)
						oldcourt.removeReservation(this);
				}
			}
		}	
	}
	
	
	
	
	
	  
	public int getId() {
		return id;
	}






	public String getDiscription() {
		return discription;
	}






	public void setDiscription(String discription) {
		this.discription = discription;
	}






	public String getTips() {
		return tips;
	}






	public void setTips(String tips) {
		this.tips = tips;
	}






	public void setId(int id) {
		this.id = id;
	}


	public void unsetId() {
		this.id = 0;	
	}



	
	
 
	public Subscriber getSubscriber() {
		return this.subscriber;	
	}
	 
	public Set<TimeInterval> getTimeInterval() {
		if(this.timeInterval == null) {
				this.timeInterval = new HashSet<TimeInterval>();
		}
		return (Set<TimeInterval>) this.timeInterval;	
	}
	 
	public Court getCourt() {
		return this.court;	
	}
	
	
	public void addAllTimeInterval(Set<TimeInterval> newTimeInterval) {
		if (this.timeInterval == null) {
			this.timeInterval = new HashSet<TimeInterval>();
		}
		for (TimeInterval tmp : newTimeInterval)
			tmp.addReservation(this);
			
	}
	 
	public void removeAllTimeInterval(Set<TimeInterval> newTimeInterval) {
		if(this.timeInterval == null) {
			return;
		}
		
		this.timeInterval.removeAll(newTimeInterval);	
	}
	
 
	public void setSubscriber(Subscriber mySubscriber) {
		this.basicSetSubscriber(mySubscriber);
		mySubscriber.addReservation(this);	
	}
	
	 
	public void addTimeInterval(TimeInterval newTimeInterval) {
		if(this.timeInterval == null) {
			this.timeInterval = new HashSet<TimeInterval>();
		}
		
		if (this.timeInterval.add(newTimeInterval))
			newTimeInterval.addReservation(this);	
	}
	 
	public void setCourt(Court myCourt) {
		this.basicSetCourt(myCourt);
		myCourt.addReservation(this);	
	}	
	
	 
	public void unsetDiscription() {
		this.discription = "";	
	}
	
	 
	public void unsetTips() {
		this.tips = "";	
	}
	
	 
	public void unsetSubscriber() {
		if (this.subscriber == null)
			return;
		Subscriber oldsubscriber = this.subscriber;
		this.subscriber = null;
		oldsubscriber.removeReservation(this);	
	}
	
	 

	public void removeTimeInterval(TimeInterval oldTimeInterval) {
		if(this.timeInterval == null)
			return;
		
		if (this.timeInterval.remove(oldTimeInterval))
			oldTimeInterval.removeReservation(this);
			
	}
	
 
	public void unsetCourt() {
		if (this.court == null)
			return;
		Court oldcourt = this.court;
		this.court = null;
		oldcourt.removeReservation(this);	
	}
	

	@Override
	public String toString() {
		return "time: "+timeInterval.toString()+", court: "+court.getName();
	}

	
	
	public Reservation(String discription, String tips) {		
		this.discription = discription;
		this.tips = tips;
	}

	
	
	
	
}

