package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.Subject;

@Repository
public class SubjectDAO extends JpaDao<Subject, String> implements ISubjectDao{

	private String GET_SUBJECT_LIST = "SELECT new map(s.id as id, s.subjectTitle as subjectTitle) FROM Subject s WHERE standard.id = :standardId order by s.subjectTitle";


	public SubjectDAO() {
		super(Subject.class);
	}

	@Override
	public List<Map<String, Object>> getSubjectList() {
		Query query2 = getEntityManager().createQuery(GET_SUBJECT_LIST);
		return query2.getResultList();
	}

	public List<Map<String, Object>> getSubjectByStandard(String standardId) {
		Query query2 = getEntityManager().createQuery(GET_SUBJECT_LIST);
		query2.setParameter("standardId", standardId);
		return query2.getResultList();
	}
	
	@Override
	public Subject getSubjectById(String id) {
		return getEntityManager().find(Subject.class, id);
		
	}
}
