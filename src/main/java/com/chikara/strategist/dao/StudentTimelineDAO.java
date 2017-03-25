package com.chikara.strategist.dao;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.CqlOperations;

import com.chikara.strategist.nosql.entity.StudentTimeline;
import com.chikara.strategist.nosql.repository.StudentTimelineRepository;
import com.datastax.driver.core.utils.UUIDs;
import com.google.common.collect.ImmutableSet;

public class StudentTimelineDAO {

	@Autowired
	StudentTimelineRepository repository;

	@Autowired
	private CqlOperations cassandraTemplate;
	
	public void create(StudentTimeline studentTimeline) {
		studentTimeline.setId(UUIDs.timeBased());
		repository.save(ImmutableSet.of(studentTimeline));
	}

	public void delete(UUID id) {
		//repository.save(ImmutableSet.of(studentTimeline));
	}
	
	public Iterable<StudentTimeline> list() {
		return repository.findAll();
	}


	public StudentTimeline find(UUID id) {
		//cassandraTemplate.query("")
		//MapId k = new MapIdFactory().id(id., UUID.class.getClassLoader());
		return repository.findOne(null);
	}
}
