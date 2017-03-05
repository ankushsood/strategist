package com.chikara.strategist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "STUDENT")
public class Student {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false)
	private String id;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "student")
	public List<Subject> subjectList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	public List<Homework> homeworkList;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "STANDARD_ID")
	private Standard standard;
	
	@Column(name = "FIRST_NAME")
	public String firstName;
	@Column(name = "LAST_NAME")
	public String lastName;
	@Column(name = "GUARDIAN_NAME")
	public String guardianName;
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "CITY")
	private String city;
	@Column(name = "STATE")
	private String state;
	@Column(name = "PINCODE")
	private Long pincode;
	@Column(name = "LANDLINE_NUMBER")
	private String landlineContactNumber;
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getLandlineContactNumber() {
		return landlineContactNumber;
	}

	public void setLandlineContactNumber(String landlineContactNumber) {
		this.landlineContactNumber = landlineContactNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Homework> getHomeworkList() {
		return homeworkList;
	}

	public void setHomeworkList(List<Homework> homeworkList) {
		this.homeworkList = homeworkList;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public Standard getStandard() {
		return standard;
	}

}
