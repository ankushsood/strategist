package com.chikara.strategist.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SUBJECT_BOOK")
public class Book implements com.chikara.strategist.entity.Entity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="BOOK_ID", nullable=false)
	private String id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
	private List<Chapter> chapterList;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "SUBJECT_ID")
	private Subject subject;
	@Column(name="BOOK_TYPE")
	private String bookType;
	
	@Column(name="BOOK_SUMMARY")
	private String bookSummary;
	
	public String getBookSummary() {
		return bookSummary;
	}
	public void setBookSummary(String bookSummary) {
		this.bookSummary = bookSummary;
	}
	@Column(name="BOOK_TITLE")
	private String bookTitle;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	@Override
	public String getEntityId() {
		return this.id;
	}
}
