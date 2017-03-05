package com.chikara.strategist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class CurrentProgressPlan {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;

	@Column(name = "CURRENT_PROGRESS")
	private String currentProgressJson;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATAED_DATE")
	private Date updated;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCurrentProgressJson(String currentProgressJson) {
		this.currentProgressJson = currentProgressJson;
	}

	public String getCurrentProgressJson() {
		return currentProgressJson;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getUpdated() {
		return updated;
	}
}
