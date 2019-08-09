package com;

import java.util.Date;

import javax.xml.bind.annotation.*;

//@XmlRootElement(name = "demo")
@XmlRootElement
public class Data {

	
	private int id;
	private String team;
	//public int age;
	//public Date dob;
	private String city;
	
	/*public Data(String name, int ssn, int age, Date dob, String file) {
		
		this.name = name;
		this.ssn = ssn;
		this.age = age;
		this.dob = dob;
		this.file = file;
		
		toString();
		
	}
	
	public String toString() {
		return this.name+" "+this.ssn+" "+this.age+" "+this.dob+" "+this.file;
		
	}*/
	
	/*public Data(int ID, String Team, String City) {
		
		this.ID = ID;
		this.Team = Team;
		this.City = City;
		
		
		toString();
		
	}*/
	
	

	/*public String toString() {
		return this.ID+" "+this.Team+" "+this.City;
		
	}*/

	//@XmlElement(name = "id")
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	//@XmlElement(name = "team")
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	//@XmlElement(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", team=" + team + ", city=" + city + "]";
	}
	
	
	
}
