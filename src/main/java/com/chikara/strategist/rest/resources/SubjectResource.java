package com.chikara.strategist.rest.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chikara.strategist.dao.ISubjectDao;

/**
 * @author Ankush Sood <soodankush@gmail.com>
 */
@Path("/subject")
@Component
public class SubjectResource
{
	
	@Autowired
	private ISubjectDao subjectDao;
	
	@GET
	@Path("/{standardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getSubjects(@PathParam("standardId") String standardId)
    {
		List<Map<String, Object>> students = subjectDao.getSubjectByStandard(standardId);
    	return students;
    }

}