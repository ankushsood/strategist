package com.chikara.strategist.nosql.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.nosql.entity.StudentTimeline;

@Repository
public interface StudentTimelineRepository extends CassandraRepository<StudentTimeline> {
    //
	
	
	  @Query("SELECT * FROM studentTimeline WHERE user=?0 LIMIT ?1")
	   Iterable<StudentTimeline> findByStudentId(String user,Integer limit);
	 
	  
	  
	 
}