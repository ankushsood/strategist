package com.chikara.strategist.entity;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.chikara.strategist.constants.SubjectStream;

@Entity
@Table(name = "STANDARD", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"CLASS_CODE", "CLASS_SECTION", "SCHOOL_ID" }) })
public class Standard implements com.chikara.strategist.entity.Entity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false)
	private String id;

	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOL_ID")
	private School school;
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "standard", fetch = FetchType.LAZY)
	List<Subject> subjectList = new ArrayList<Subject>();
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "standard", fetch = FetchType.LAZY)
	List<Student> studentList = new ArrayList<Student>();

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Faculty> faculty;
	@Column(name = "remarks", length = 3000)
	private String remarks;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updated;

	@Column(name = "CLASS_CODE")
	private String classCode;

	@Column(name = "CLASS_SECTION")
	private String classSection;

	@Enumerated(EnumType.STRING)
	@Column(name = "STREAM")
	private SubjectStream stream;

	@Column(name = "STANDARD_TEACHER_ID")
	private String standardTeacherID;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassSection() {
		return classSection;
	}

	public void setClassSection(String classSection) {
		this.classSection = classSection;
	}

	public SubjectStream getStream() {
		return stream;
	}

	public void setStream(SubjectStream stream) {
		this.stream = stream;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public void setFaculty(List<Faculty> faculty) {
		this.faculty = faculty;
	}

	public List<Faculty> getFaculty() {
		return faculty;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	public void setStandardTeacherID(String standardTeacherID) {
		this.standardTeacherID = standardTeacherID;
	}

	public String getStandardTeacherID() {
		return standardTeacherID;
	}

	@Override
	public String getEntityId() {
		return this.id;
	}
}