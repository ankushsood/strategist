package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import com.chikara.strategist.entity.Subject;

public interface ISubjectDao extends Dao<Subject, String>
{
    //User loadUserByUsername(String username) throws UsernameNotFoundException;

    //User findByName(String name);
	
	List<Map<String, Object>> getSubjectList();
	
	Subject getSubjectById(String subjectUUID);
	
	List<Map<String, Object>> getSubjectByStandard(String standardId);

}