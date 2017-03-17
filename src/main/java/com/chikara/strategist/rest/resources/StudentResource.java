package com.chikara.strategist.rest.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.chikara.strategist.dao.IStudentDao;

/**
 * @author Ankush Sood <soodankush@gmail.com>
 */
@Path("/students")
@Component
public class StudentResource
{
	
	@Autowired
	private IStudentDao studentDao;
	
	
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

   
    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
    {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }
        return roles;
    }
}