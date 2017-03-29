package com.chikara.strategist.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.School;

@Repository
public class CurrentProgressPlanDAO {
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<School> getSchool() {
		return (List<School>) hibernateTemplate.find("from School");
	}
	public void deleteSchool(int id){
		Object record = hibernateTemplate.load(School.class, id);
		hibernateTemplate.delete(record);
	}
	
	public School saveSchool(School school){
		hibernateTemplate.save(school);
		
		return school;
	}
	public School updateSchool(School school){
		hibernateTemplate.update(school);
		return school;
	}
}
