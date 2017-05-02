package com.chikara.strategist.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.time.FastDateFormat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.chikara.strategist.entity.Chapter;
import com.chikara.strategist.entity.Student;

public class ChapterDAO extends JpaDao<Chapter, String> {
	
	public ChapterDAO() {
		super(Chapter.class);
	}

	@Autowired
	private NamedParameterJdbcTemplate sqlTemplate;
	
	private HibernateTemplate hibernateTemplate;
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	public void deleteStudent(String id){
		Object record = hibernateTemplate.load(Student.class, id);
		hibernateTemplate.delete(record);
	}
	
	public Student saveStudent(Student student){
		hibernateTemplate.save(student);
		return student;
	}
	
	public Student updateStudent(Student student){
		hibernateTemplate.update(student);
		return student;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getChapterList(String bookId){
		Query query2 = getEntityManager().createQuery("");
		return query2.getResultList();
	}

	@Override
	public Chapter find(String id) {
		return null;
	}

	@Override
	public Chapter save(Chapter entity) {
		return null;
	}

	@Override
	public void delete(String id) {
		//hibernateTemplate.d
	}

	@Override
	public void delete(Chapter entity) {
		hibernateTemplate.delete(entity);
	}

}