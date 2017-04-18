package com.chikara.strategist.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chikara.strategist.entity.Standard;

@Repository
public class StandardDAO extends JpaDao<Standard, String> implements IStandardDao{
	public StandardDAO() {
		super(Standard.class);
	}

	@Autowired
	private NamedParameterJdbcTemplate sqlTemplate;
	
	private String GET_STANDARD_LIST = "SELECT s.id as id, s.classCode as classCode, s.classSection as classSection) FROM Standard s order by s.classCode, classSection";

	private String GET_STANDARD_SUBJECT_LIST = "SELECT count(DISTINCT sub.SUBJECT_NAME) as totalSubject, s.id as standardId, GROUP_CONCAT(DISTINCT CONCAT(sub.SUBJECT_NAME  , '~~' , sub.id) SEPARATOR ', ') AS subjectTitleList " 
			+ " FROM STANDARD s "
			+ " INNER JOIN SUBJECT sub ON s.ID=sub.STANDARD_ID " 
			+ " WHERE s.standard_teacher_id=:standardTeacherID "
			+ " GROUP BY s.CLASS_CODE , s.CLASS_SECTION";

	private String GET_STANDARD_LIST_FOR_FACULTY = "SELECT new map(s.id as id, s.classCode as classCode, s.classSection as classSection, count(stu.standard) as totalStudents) "
			+ " FROM Standard s "
			+ " JOIN s.studentList stu "
			+ " where s.standardTeacherID = :standardTeacherID group by stu.standard  order by s.classCode, classSection";
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStandardList() {
		Query query2 = getEntityManager().createQuery(GET_STANDARD_LIST);
		return query2.getResultList();
	}
	
	@Override
	public 	List<Map<String, Object>> getStandardListForFaculty(String facultyId){

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("standardTeacherID", facultyId);
		
		List<Map<String, Object>> standardSubjectList = sqlTemplate.queryForList(GET_STANDARD_SUBJECT_LIST, paramMap);
		
		Query query2 = getEntityManager().createQuery(GET_STANDARD_LIST_FOR_FACULTY);
		query2.setParameter("standardTeacherID", facultyId);
		List<Map<String, Object>> facultyStandardList = query2.getResultList();
		
		for (Map<String, Object> standardSubjectMap : standardSubjectList) {
			for (Map<String, Object> facultyStandardMap : facultyStandardList) {
				if(standardSubjectMap.get("standardId").equals(facultyStandardMap.get("id"))){
					facultyStandardMap.put("subjectTitleList", standardSubjectMap.get("subjectTitleList"));
					facultyStandardMap.put("totalSubject", standardSubjectMap.get("totalSubject"));
				}
			}
		}
			
		return facultyStandardList;		
	}


	/*public void deleteStandard(String standardId) {
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
	}*/

	@Override
	public Map<String, Object> getStandardById(String standardUUID) {
		// TODO Auto-generated method stub
		return null;
	}
}
