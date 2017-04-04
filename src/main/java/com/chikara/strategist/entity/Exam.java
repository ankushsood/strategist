package com.chikara.strategist.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Exam {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="ID")
	private String id;
	private float totalMarks;
	private float marksObtained;
	Date examDate;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn( name = "SUBJECT_ID")
	private Subject subject;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(float totalMarks) {
		this.totalMarks = totalMarks;
	}
	public float getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(float marksObtained) {
		this.marksObtained = marksObtained;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
