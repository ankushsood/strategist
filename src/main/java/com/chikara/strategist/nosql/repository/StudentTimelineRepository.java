package com.chikara.strategist.nosql.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.nosql.entity.StudentTimeline;

@Repository
public interface StudentTimelineRepository extends CassandraRepository<StudentTimeline> {
    //
}