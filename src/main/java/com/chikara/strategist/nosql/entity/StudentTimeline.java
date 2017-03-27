package com.chikara.strategist.nosql.entity;

import java.util.Date;
import java.util.UUID;


import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;



@Table (value="studentTimeline")
public class StudentTimeline {
	    @PrimaryKeyColumn(
	      name = "id", 
	      ordinal = 1, 
	      type = PrimaryKeyType.CLUSTERED, 
	      ordering = Ordering.DESCENDING)
	    private UUID id = UUIDs.timeBased();
	    
		@PrimaryKeyColumn(
	      name = "studentId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	    private String studentId;
		
		@Column (value = "title")
		private String title;
	    
		@Column(value = "description")
	    private String description;
	    
		@Column(value = "badgeString")
	    private String badgeString;
		
		@Column(value = "eventCreatedBy")
	    private String eventCreatedBy;
		
		@Column(value = "eventCreatorProfileUrl")
	    private String eventCreatorProfileUrl;
		
		@Column(value = "eventCreationDateTime")
		private Date eventCreationDateTime;
		
		@Column(value = "eventType")
		private String eventType;
	    
		@Column(value = "eventCreatorImagePath")
		private String eventCreatorImagePath;
		
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
