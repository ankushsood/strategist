package com.chikara.strategist.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="HOMEWORK")
public class Homework {

	@Id
	@GeneratedValue( generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="_ID")
	private String id;
	
	/*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="STUDENT_ID", nullable=false)
	private Student student;*/
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="STANDARD_ID", nullable=false)
	private Standard standard;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="FACULTY_ID", nullable=false)
	private Faculty faculty;
	
	
	@Column(name="START_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="SUBMISSION_DATE", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date submissionDate;
	@Column(name="PRIORITY", nullable=false, length=1)
	private String priority;
	@Column(name="STATUS", nullable=false, length = 1)
	private String status;
	
	@Lob
	@Column(name = "HOMEWORK_JSON")
	private String homeworkEntriesJson;
	
	@Column(name = "SUBJECT_TITLE")
	private String subjectTitle;
	
	
	@Column(name="DESCRIPTION", nullable=false)
	@Lob
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_ON", insertable=false)
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON", insertable=false)
	private Date updateDate;
		public void setId(String id) {
			this.id = id;
		}
		public String getId() {
			return id;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getSubmissionDate() {
			return submissionDate;
		}
		public void setSubmissionDate(Date submissionDate) {
			this.submissionDate = submissionDate;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
		public Date getUpdateDate() {
			return updateDate;
		}
		
		public Standard getStandard() {
			return standard;
		}
		public void setStandard(Standard standard) {
			this.standard = standard;
		}
		
		@PrePersist
		public void onCreate(){
			updateDate = createDate = new Date();
		}
		@PreUpdate
		public void onUpdate(){
			updateDate = new Date();
		}
		/*public void setStudent(Student student) {
			this.student = student;
		}
		public Student getStudent() {
			return student;
		}*/
		public void setFaculty(Faculty faculty) {
			this.faculty = faculty;
		}
		public Faculty getFaculty() {
			return faculty;
		}
		public void setHomeworkEntriesJson(String homeworkEntriesJson) {
			this.homeworkEntriesJson = homeworkEntriesJson;
		}
		public String getHomeworkEntriesJson() {
			return homeworkEntriesJson;
		}
		public void setSubjectTitle(String subjectTitle) {
			this.subjectTitle = subjectTitle;
		}
		public String getSubjectTitle() {
			return subjectTitle;
		}
}
