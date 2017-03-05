package com.chikara.strategist.entity;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.chikara.strategist.constants.SchoolType;
import com.chikara.strategist.constants.SubscriptionType;
import com.chikara.strategist.entity.Standard;

@Entity
@Table(name="SCHOOL")
public class School {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="ID", nullable=false)
	private String id;
	@Column(name="TITLE", length=3000)
	private String schoolTitle;
	@Enumerated(EnumType.STRING)
	@Column(name="TYPE")
	private SchoolType schoolType;
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "school")
	private List<Standard> standardList = new ArrayList<Standard>();
	@OneToMany(cascade= CascadeType.ALL, mappedBy = "school")
	private List<Faculty> facultyList = new ArrayList<Faculty>();
/*	@OneToMany(cascade= CascadeType.ALL, mappedBy = "school")
	private List<Student> studentList = new ArrayList<Student>();
*/	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updated;	
	
	@Column(name = "BACKGROUND_IMAGE_PATH")
	private String backgroundImagePath;
	@Column(name = "LOGO_PATH")
	private String logoPath;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SUBSCRIPTION_TYPE")
	private SubscriptionType subscriptionType;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date subscriptionStartDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchoolTitle() {
		return schoolTitle;
	}
	public void setSchoolTitle(String schoolTitle) {
		this.schoolTitle = schoolTitle;
	}
	public SchoolType getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(SchoolType schoolType) {
		this.schoolType = schoolType;
	}
	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}
	public List<Standard> getStandardList() {
		return standardList;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getBackgroundImagePath() {
		return backgroundImagePath;
	}
	public void setBackgroundImagePath(String backgroundImagePath) {
		this.backgroundImagePath = backgroundImagePath;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
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
	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public Date getSubscriptionStartDate() {
		return subscriptionStartDate;
	}
	public void setSubscriptionStartDate(Date subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
	}
	public void setFacultyList(List<Faculty> facultyList) {
		this.facultyList = facultyList;
	}
	public List<Faculty> getFacultyList() {
		return facultyList;
	}
/*	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public List<Student> getStudentList() {
		return studentList;
	}*/
}