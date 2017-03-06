package com.chikara.strategist.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.Faculty;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;

@Repository
public class FacultyDAO {
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Faculty> getFacultyList(String schoolId) {
		return (List<Faculty>) hibernateTemplate.find("from Faculty where SCHOOL_ID = ?",
				schoolId);
	}

	@SuppressWarnings("unchecked")
	public List<Faculty> getFacultySummarizedList(final String schoolId) {

		final String sQryToFind = "SELECT f.id, f.image_path, CONCAT(f.first_name,' ', f.last_name) AS \"FACULTY_NAME\","
				+ " f.joining_date, GROUP_CONCAT(s.subject_name) AS SUBJECT_LIST FROM faculty f "
				+ "INNER JOIN SUBJECT s ON(s.faculty_id = f.id)  WHERE f.school_id = ? GROUP BY f.id;";

		List<Faculty> facultyList = (List<Faculty>) hibernateTemplate
				.execute(new HibernateCallback<List<Faculty>>() {
					public List<Faculty> doInHibernate(Session session)
							throws HibernateException {
						List<Faculty> facultyList = new ArrayList<Faculty>();
						SQLQuery sq = session.createSQLQuery(sQryToFind);
						sq.setParameter(0, schoolId);
						List<Object[]> dataList = sq.list();
						for (Object[] facultyArr : dataList) {
							Faculty faculty = new Faculty();
							faculty.setImagePath((String)facultyArr[1]);
							faculty.setFACULTY_NAME((String)facultyArr[2]);
							faculty.setJoiningDate((Date)facultyArr[3]);
							faculty.setSUBJECT_LIST((String)facultyArr[4]);
							facultyList.add(faculty);
						}
						return facultyList;
					}	
				});

		return facultyList;

	}

	@SuppressWarnings("unchecked")
	public List<Faculty> getFacultyById(String schoolId, String facultyId) {
		return (List<Faculty>) hibernateTemplate.find(
				"from Faculty where SCHOOL_ID = ? and id = ?", schoolId,
				facultyId);
	}

	@SuppressWarnings("unchecked")
	public List<Faculty> getFacultyByName(String schoolId, String facultyId) {
		return (List<Faculty>) hibernateTemplate
				.find("from Faculty where SCHOOL_ID = ? and CONCAT(firstName, lastName) like ?");
	}

	public void deleteFaculty(String schoolId, String facultyId) {
		List<Faculty> facultyList = getFacultyById(schoolId, facultyId);
		hibernateTemplate.delete(facultyList.get(0));
	}

	public Faculty saveFaculty(Faculty faculty) {
		hibernateTemplate.save(faculty);
		return faculty;
	}

	public Faculty updateFaculty(Faculty faculty) {
		hibernateTemplate.update(faculty);
		return faculty;
	}

}