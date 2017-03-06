package com.chikara.strategist.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.School;
import com.chikara.strategist.entity.Student;

@Repository
public class StudentDAO {
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void getStudentByClassId() {
		String hql = "select * from Student where class_id";
	}
		
	@SuppressWarnings("unchecked")
	public List<Student> getStudent() {
		return (List<Student>) hibernateTemplate.find("from Student");
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
}
