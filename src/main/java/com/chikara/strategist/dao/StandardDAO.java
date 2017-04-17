package com.chikara.strategist.dao;

import java.util.HashMap;
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
	
	private String GET_STANDARD_LIST = "SELECT new map(s.id as id, s.classCode as classCode, s.classSection as classSection) FROM Standard s order by s.classCode, classSection";

	private String GET_STANDARD_LIST_FOR_FACULTY = "SELECT GROUP_CONCAT(DISTINCT subjectlis1_.SUBJECT_NAME  SEPARATOR ', ')AS subjectTitleList, COUNT(studentlis2_.STANDARD_ID) AS studentCount, standard0_.ID AS standardId, " + 
		" concat(standard0_.CLASS_CODE ,'-', standard0_.CLASS_SECTION )AS standard  " +
		" FROM STANDARD standard0_  " +
		" INNER JOIN SUBJECT subjectlis1_ ON standard0_.ID=subjectlis1_.STANDARD_ID " + 
		" INNER JOIN STUDENT studentlis2_ ON standard0_.ID=studentlis2_.STANDARD_ID  " +
		" WHERE standard0_.STANDARD_TEACHER_ID=:standardTeacherID  " +
		" GROUP BY studentlis2_.STANDARD_ID, standard0_.CLASS_CODE , standard0_.CLASS_SECTION " + 
		" ORDER BY standard0_.CLASS_CODE";

	
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStandardList() {
		Query query2 = getEntityManager().createQuery(GET_STANDARD_LIST);
		return query2.getResultList();
	}
	
	@Override
	public 	List<Map<String, Object>> getStandardListForFaculty(String facultyId){

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("standardTeacherID", facultyId);
		
		return sqlTemplate.queryForList(GET_STANDARD_LIST_FOR_FACULTY, paramMap);
		
		
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
