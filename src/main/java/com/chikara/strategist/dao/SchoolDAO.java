package com.chikara.strategist.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.School;

@Repository
public class SchoolDAO {
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<School> getSchool() {
		return (List<School>) hibernateTemplate.find("from School");
	}

	public List<School> getSchoolById(String schoolId) {
		List<School> school = (List<School>) hibernateTemplate.find("from School where id = ?", schoolId);
		return school;
	}

	@SuppressWarnings("unchecked")
	public List<School> getSchoolByTitle(String schoolTitle) {
		String query = "from School where str(lower(schoolTitle)) like ?";
		List<School> schoolList = (List<School>) hibernateTemplate.find(query,
				schoolTitle.toLowerCase());
		return schoolList;
	}

	public void deleteSchool(String schoolId) {
		Object record = hibernateTemplate.get(School.class, schoolId);
		hibernateTemplate.delete(record);
	}

	public School saveSchool(School school) {
		hibernateTemplate.save(school);
		return school;
	}

	public School updateSchool(School school) {
		hibernateTemplate.update(school);
		return school;
	}
}