package cloud.migration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin extends Person implements java.io.Serializable
{
	
	@Column
	private String privilege;
	
	
	
					
	@OneToOne(mappedBy="admin",targetEntity = Hall.class,fetch=FetchType.EAGER)
	private Hall hall;

	
	public String getPrivilege() {
		return privilege;
	}


	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}


	
	public void unsetPrivilege() {
		this.privilege = "";	
	}
	

	public Admin( String name, String email, String telephone,
			String gender, int age,String privilege) {
		super( name, email, telephone, gender, age);
		this.privilege=privilege;
		// TODO Auto-generated constructor stub
	}
	
	public Admin(){
		
	}


	
	
	public void basicSetHall(Hall myHall) {
		if (this.hall != myHall) {
			if (myHall != null){
				if (this.hall != myHall) {
					Hall oldhall = this.hall;
					this.hall = myHall;
					if (oldhall != null)
						oldhall.unsetAdmin();
				}
			}
		}	
	}
	


	

	public Hall getHall() {
		return this.hall;	
	}
	
	
	
	
	public void setHall(Hall myHall) {
		this.basicSetHall(myHall);
		myHall.basicSetAdmin(this);
			
	}
	

	
	
	public void unsetHall() {
		if (this.hall == null)
			return;
		Hall oldhall = this.hall;
		this.hall = null;
		oldhall.unsetAdmin();	
	}


	@Override
	public String toString() {
		return getName()+", "+getAge()+", "+getEmail()+", "+getTelephone()+", "+getGender()+", "+getPrivilege();
	}
	
	
}

