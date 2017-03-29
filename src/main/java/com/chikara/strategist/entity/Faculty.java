package com.chikara.strategist.entity;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.chikara.strategist.constants.Gender;
/*
@SqlResultSetMapping(name="compositekey",
        entities=@EntityResult(entityClass=Faculty.class,
            fields = {
                    @FieldResult(name="id", column = "id"),
                    @FieldResult(name="imagePath", column = "image_path"),
                    @FieldResult(name="joiningDate", column = "joining_date"),
                    }),columns= {@ColumnResult (name = "FACULTY_NAME"),
                            @ColumnResult(name = "SUBJECT_LIST") }
                    
         )*/
/*
@NamedNativeQueries({ @NamedNativeQuery(name = "findFacultySummaryBySchoolId", 
										query = "SELECT f.id, f.image_path, CONCAT(f.first_name,' ', f.last_name) AS \"FACULTY_NAME\"," +
												" f.joining_date, GROUP_CONCAT(s.subject_name) AS SUBJECT_LIST FROM faculty f " +
												"INNER JOIN SUBJECT s ON(s.faculty_id = f.id)  WHERE f.school_id = ? GROUP BY f.id;",  
										resultClass = FacultyDTO.class)
										//,
										 //resultSetMapping="compositekey"),
										 
					 } )*/
@Entity
@Table(name = "FACULTY")
public class Faculty {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false)
	private String id;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
	private List<Standard> standardList;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
	private List<Subject> subjectList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
	private List<Homework> homeWorkList;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SCHOOL_ID")
	private School school;

	@Column(name = "STANDARD_TEACHER_ID")
	private String standardTeacherID;

	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	@Column(name = "ADDRESS", nullable = true)
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
	@Column(name = "GENDER")
	private Gender gender;
	private String facultyExpJson;
	private String facultyQualificationJson;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JOINING_DATE")
	private Date joiningDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATAED_DATE")
	private Date updated;

	@Transient
	private String FACULTY_NAME;
	@Transient
	private String SUBJECT_LIST;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Standard> getStandardList() {
		return standardList;
	}

	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Homework> getHomeWorkList() {
		return homeWorkList;
	}

	public void setHomeWorkList(List<Homework> homeWorkList) {
		this.homeWorkList = homeWorkList;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFacultyExpJson() {
		return facultyExpJson;
	}

	public void setFacultyExpJson(String facultyExpJson) {
		this.facultyExpJson = facultyExpJson;
	}

	public String getFacultyQualificationJson() {
		return facultyQualificationJson;
	}

	public void setFacultyQualificationJson(String facultyQualificationJson) {
		this.facultyQualificationJson = facultyQualificationJson;
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

	public void setSchool(School school) {
		this.school = school;
	}

	public School getSchool() {
		return school;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setStandardTeacherID(String standardTeacherID) {
		this.standardTeacherID = standardTeacherID;
	}

	public String getStandardTeacherID() {
		return standardTeacherID;
	}

	public void setFACULTY_NAME(String fACULTY_NAME) {
		FACULTY_NAME = fACULTY_NAME;
	}

	public String getFACULTY_NAME() {
		return FACULTY_NAME;
	}

	public void setSUBJECT_LIST(String sUBJECT_LIST) {
		SUBJECT_LIST = sUBJECT_LIST;
	}

	public String getSUBJECT_LIST() {
		return SUBJECT_LIST;
	}
}
