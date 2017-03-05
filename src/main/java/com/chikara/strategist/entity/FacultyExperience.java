package com.chikara.strategist.entity;

import javax.persistence.Embeddable;

@Embeddable
public class FacultyExperience {

	private String programTitle;

	public void setProgramTitle(String programTitle) {
		this.programTitle = programTitle;
	}

	public String getProgramTitle() {
		return programTitle;
	}
}
