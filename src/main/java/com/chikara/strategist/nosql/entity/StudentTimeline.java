package com.chikara.strategist.nosql.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;



@Table
public class StudentTimeline {
	    @PrimaryKeyColumn(
	      name = "id", 
	      ordinal = 1, 
	      type = PrimaryKeyType.CLUSTERED, 
	      ordering = Ordering.DESCENDING)
	    private UUID id;
	    
		@PrimaryKeyColumn(
	      name = "studentId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	    private String studentId;
		
		@Column (name = "title")
		private String title;
	    
		@Column(name = "description")
	    private String description;
	    
		public String getStudentId() {
			return studentId;
		}

		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Column(name = "eventCreatedBy")
	    private String eventCreatedBy;
		
		@Column(name = "eventCreatorProfileUrl")
	    private String eventCreatorProfileUrl;
		
		@Column(name = "eventCreationDateTime")
		private Date eventCreationDateTime;
		
		@Column(name = "eventType")
		private String eventType;
	    
		@Column(name = "eventCreatorImagePath")
		private String eventCreatorImagePath;

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}


		public String getEventCreatedBy() {
			return eventCreatedBy;
		}

		public void setEventCreatedBy(String eventCreatedBy) {
			this.eventCreatedBy = eventCreatedBy;
		}

		public String getEventCreatorProfileUrl() {
			return eventCreatorProfileUrl;
		}

		public void setEventCreatorProfileUrl(String eventCreatorProfileUrl) {
			this.eventCreatorProfileUrl = eventCreatorProfileUrl;
		}

		public Date getEventCreationDateTime() {
			return eventCreationDateTime;
		}

		public void setEventCreationDateTime(Date eventCreationDateTime) {
			this.eventCreationDateTime = eventCreationDateTime;
		}

		public String getEventType() {
			return eventType;
		}

		public void setEventType(String eventType) {
			this.eventType = eventType;
		}

		public String getEventCreatorImagePath() {
			return eventCreatorImagePath;
		}

		public void setEventCreatorImagePath(String eventCreatorImagePath) {
			this.eventCreatorImagePath = eventCreatorImagePath;
		}
}
