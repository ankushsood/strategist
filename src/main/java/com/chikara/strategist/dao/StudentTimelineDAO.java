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

	

	
	public static void main(String[] args) {
		//String ATM_SELECT = "asfa,asfasf,asfasdf,asf,,asd23432,fdasf,423,";
		String ATM_SELECT = "*";
		
		ATM_SELECT = ATM_SELECT.replaceAll(",,", ",");
		if(ATM_SELECT.matches("([aA0-zZ9]+,)+[aA0-zZ9]*")){
			ATM_SELECT = "ATMPROFILES.[TermId] IN ('" + ATM_SELECT.replaceAll(",", "','") + "')";
		}else if(ATM_SELECT.matches("([aA0-zZ9]+)")){
			ATM_SELECT = "((ATMPROFILES.[TermId]) = ('" + ATM_SELECT + "'))";
		}else  if(ATM_SELECT.matches("\\*")){
			ATM_SELECT = "1 = 1";
		}else{
			ATM_SELECT = "";
		}
		System.out.println(ATM_SELECT);
	}
}
