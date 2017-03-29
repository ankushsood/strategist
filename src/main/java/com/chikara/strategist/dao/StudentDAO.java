package com.chikara.strategist.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.chikara.strategist.entity.Student;

public class StudentDAO extends JpaDao<Student, String> implements IStudentDao{
	
	public StudentDAO() {
		super(Student.class);
	}

	@Autowired
	private NamedParameterJdbcTemplate sqlTemplate;
	
	private HibernateTemplate hibernateTemplate;
	
	private String GET_STUDENT_LIST = "SELECT s.id, CONCAT(first_name , ' ' ,  last_Name ) AS studentName , "
			+ "Guardian_name as guardianName, Image_Path as dp, Mobile_number as contactNo, c.CLASS_CODE as class, "
			+ "CLASS_SECTION as section FROM student s "
			+ "JOIN standard c ON(s.standard_id = c.id)";
	
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void getStudentByClassId() {
		//String hql = "select * from Student where class_id";
	}
	
	
	public Student getStudentById(String studentUUID) {
		/*Map<String, String> studentParams = new HashMap<String, String>();
		studentParams.put("id", studentUUID);
		return sqlTemplate.queryForObject("select * from Student where id = :id", studentParams, Student.class);
		*/
		return hibernateTemplate.load(Student.class, studentUUID);
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
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> getStudentsList(){
		return sqlTemplate.queryForList(GET_STUDENT_LIST, Collections.<String, Object>emptyMap());
	}

	@Override
	public Student find(String id) {
		return null;
	}

	@Override
	public Student save(Student entity) {
		return null;
	}

	@Override
	public void delete(String id) {
		//hibernateTemplate.d
	}

	@Override
	public void delete(Student entity) {
		hibernateTemplate.delete(entity);
	}

}