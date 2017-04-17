package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import com.chikara.strategist.entity.Standard;

public interface IStandardDao extends Dao<Standard, String>
{
    //User loadUserByUsername(String username) throws UsernameNotFoundException;

    //User findByName(String name);
	
	List<Map<String, Object>> getStandardList();
	
	Map<String, Object> getStandardById(String standardUUID);
	
	List<Map<String, Object>> getStandardListForFaculty(String facultyId);
}