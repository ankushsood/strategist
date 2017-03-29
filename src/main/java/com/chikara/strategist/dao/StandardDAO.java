package com.chikara.strategist.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.Faculty;
import com.chikara.strategist.entity.Standard;

@Repository
public class StandardDAO {
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Standard> getStandard(String schoolId) {
		return (List<Standard>) hibernateTemplate.find("from Standard where SCHOOL_ID = ?", schoolId);
	}

	public void deleteStandard(String standardId) {
		Object record = hibernateTemplate.load(Standard.class, standardId);
		hibernateTemplate.delete(record);
	}

	public Standard saveStandard(Standard standard) {
		hibernateTemplate.save(standard);
		return standard;
	}

	public Standard updateStandard(Standard standard) {
		hibernateTemplate.update(standard);
		return standard;
	}
	
	@SuppressWarnings("unchecked")
	public List<Standard> getStandardByCode(String schoolId, String standardCode){
		String query = "from Standard where SCHOOL_ID = ? and classCode = ?";
		List<Standard> standardList = (List<Standard>) hibernateTemplate.find(query, schoolId, standardCode);
		return standardList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Standard> getStandardByCodeAndSection(String schoolId, String standardCode, String standardSection){
		String query = "from Standard where SCHOOL_ID = ? and classCode = ? and classSection = ?";
		List<Standard> standardList = (List<Standard>) hibernateTemplate.find(query, schoolId, standardCode, standardSection);
		return standardList;
	}
}
