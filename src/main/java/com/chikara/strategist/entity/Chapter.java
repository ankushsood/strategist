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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BOOK_CHAPTER")
public class Chapter implements com.chikara.strategist.entity.Entity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false)
	private String id;
	
	@Column(name = "CHAPTER_TITLE")
	private String chapterTitle;
	
	@Column(name = "CHAPTER_SUMMARY")
	private String chapterSummary;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
	@Lob
	@Column(name = "CHAPTER_JSON")
	private String chapterJSON;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updated;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getEntityId() {
		return this.id;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}

	public String getChapterSummary() {
		return chapterSummary;
	}

	public void setChapterSummary(String chapterSummary) {
		this.chapterSummary = chapterSummary;
	}

	public String getChapterJSON() {
		return chapterJSON;
	}

	public void setChapterJSON(String chapterJSON) {
		this.chapterJSON = chapterJSON;
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
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}