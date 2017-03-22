package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import com.chikara.strategist.entity.Student;
import com.chikara.strategist.nosql.entity.StudentTimeline;

public interface IStudentTimelineDao 
{
    //User loadUserByUsername(String username) throws UsernameNotFoundException;

    //User findByName(String name);
	
	List<Map<String, Object>> getStudentsList();
}