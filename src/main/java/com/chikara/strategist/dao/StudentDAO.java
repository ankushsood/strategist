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

import com.chikara.strategist.entity.Student;

public class StudentDAO extends JpaDao<Student, String> implements IStudentDao{
	
	public StudentDAO() {
		super(Student.class);
	}

	@Autowired
	private NamedParameterJdbcTemplate sqlTemplate;
	
	private HibernateTemplate hibernateTemplate;
	
	private String GET_STUDENT_LIST = "SELECT new map(s.id as id, s.firstName as firstName, s.lastName as lastName, s.guardianName as guardianName, s.imagePath as imagePath,"
			+ " s.mobileNumber as mobileNumber, c.classCode as classCode, c.classSection as classSection ) FROM Student s JOIN s.standard c  order by s.firstName";
	
	private String GET_STUDENT_DETAILS = "SELECT new map(s.firstName as firstName, s.lastName as lastName, s.guardianName as guardianName, s.imagePath as imagePath, "
			+ "s.mobileNumber as mobileNumber, s.address as address, s.dateOfBirth as dateOfBirth, s.city as city, s.state as state, s.pincode as pincode, c.classCode as classCode, c.classSection as classSection"
			+ ", f.firstName as facultyFirstName, f.lastName as facultyLastName, c.classCode as classCode, c.classSection as classSection, f.imagePath as facultyImage, f.id as facultyId) "
				+ "from Student s JOIN s.standard c "
				+ " JOIN c.faculty f WHERE f.id = c.standardTeacherID"
				+ " and s.id = :id";
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void getStudentByClassId() {
		//String hql = "select * from Student where class_id";
	}
	
	@Transactional(readOnly = true)
	public Map<String, Object> getStudentById(String studentUUID) {
		
		TypedQuery<Map> query = getEntityManager().createQuery(GET_STUDENT_DETAILS, Map.class);
		query.setParameter("id", studentUUID);
		Map<String, Object> result = query.getSingleResult();
		Date dob = ((Date)result.get("dateOfBirth"));
		if(dob != null){
			FastDateFormat formatter = FastDateFormat.getInstance("dd-MMM-YYYY");
			result.put("dateOfBirth", formatter.format(dob));
		}
		return result;
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
	public List<Map<String, Object>> getStudentsList(){
		Query query2 = getEntityManager().createQuery(GET_STUDENT_LIST);
		return query2.getResultList();
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