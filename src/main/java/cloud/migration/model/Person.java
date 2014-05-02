package cloud.migration.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;




@MappedSuperclass
public abstract class Person
{
	

	@Id
	@Column
	@JoinColumn(name ="pDetail_PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	@Column
	private String email;
	
	@Column
	private String telephone;
	@Column
	private String gender;
	@Column
	private int age;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public Person(String name, String email, String telephone,
			String gender, int age) {
		
		
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.age = age;
	}



	public Person(){
		
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email
				+ ", telephone=" + telephone + ", gender=" + gender + ", age="
				+ age + "]";
	}




 
}

