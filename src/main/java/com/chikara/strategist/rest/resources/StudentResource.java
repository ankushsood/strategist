package com.chikara.strategist.rest.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.chikara.strategist.dao.IStudentDao;
import com.chikara.strategist.entity.Student;
import com.chikara.strategist.nosql.entity.StudentTimeline;
import com.chikara.strategist.nosql.repository.StudentTimelineRepository;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author Ankush Sood <soodankush@gmail.com>
 */
@Path("/students")
@Component
public class StudentResource
{
	
	@Autowired
	private IStudentDao studentDao;
	
	@Autowired
	private StudentTimelineRepository  studentTimelineRepository;
	
/*	@Autowired
    private UserService userService;
/*
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;
*/
 
	

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getStudents()
    {
		List<Map<String, Object>> students = studentDao.getStudentsList();
    	return students;
    }

   @GET
   @Path("/student/{studentUUID}")
   @Produces(MediaType.APPLICATION_JSON)
   public Student getStudent(@PathParam("studentUUID") String studentUUID){
	   return studentDao.getStudentById(studentUUID);
   }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StudentTimeline addStudentTimeline(StudentTimeline studentTimeline){
		studentTimeline.setEventCreationDateTime(new Date());
		return studentTimelineRepository.save(studentTimeline);
	}
	
	
	
	
    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }
        return roles;
    }
}