package com.chikara.strategist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class ProjectedProgressPlan {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="ID")
	private String id;

	private String projectProgressPlanJson;
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setProjectProgressPlanJson(String projectProgressPlanJson) {
		this.projectProgressPlanJson = projectProgressPlanJson;
	}

	public String getProjectProgressPlanJson() {
		return projectProgressPlanJson;
	}
}
