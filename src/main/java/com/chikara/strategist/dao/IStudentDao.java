package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import com.chikara.strategist.entity.Student;

public interface IStudentDao extends Dao<Student, String>
{
    //User loadUserByUsername(String username) throws UsernameNotFoundException;

    //User findByName(String name);
	
	List<Map<String, Object>> getStudentsList();
	
	Student getStudentById(String studentUUID);
}