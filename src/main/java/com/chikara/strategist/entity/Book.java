package com.chikara.strategist.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.chikara.strategist.constants.BookType;

@Entity
@Table(name="SUBJECT_BOOK")
public class Book {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="BOOK_ID", nullable=false)
	private String id;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "SUBJECT_ID")
	private Subject subject;
	@Enumerated(EnumType.STRING)
	@Column(name="BOOK_TYPE")
	private BookType bookType;
	@Column(name="BOOK_TITLE")
	private String booktitle;
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
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
}
