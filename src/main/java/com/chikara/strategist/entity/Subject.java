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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SUBJECT")
public class Subject{
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false)
	private String id;

	@JsonManagedReference()
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Book> bookList;


	@ManyToMany(cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Student> student;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FACULTY_ID")
	@JsonBackReference
	private Faculty faculty;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "STANDARD_ID")
	@JsonBackReference
	private Standard standard;

	@Column(name = "SUBJECT_NAME", nullable = false)
	private String subjectTitle;

	@Column(name = "SUBJECT_DESCRIPTION", nullable = false)
	private String subjectDescription;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Exam> examList;
	
	@OneToOne
	@JsonBackReference
	private ProjectedProgressPlan projectedProgressPlan;
	
	@OneToOne
	@JsonBackReference
	private CurrentProgressPlan currentProgressPlan;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setProjectedProgressPlan(ProjectedProgressPlan projectedProgressPlan) {
		this.projectedProgressPlan = projectedProgressPlan;
	}

	public ProjectedProgressPlan getProjectedProgressPlan() {
		return projectedProgressPlan;
	}

	public void setCurrentProgressPlan(CurrentProgressPlan currentProgressPlan) {
		this.currentProgressPlan = currentProgressPlan;
	}

	public CurrentProgressPlan getCurrentProgressPlan() {
		return currentProgressPlan;
	}

	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	public List<Exam> getExamList() {
		return examList;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Student> getStudent() {
		return student;
	}

}
