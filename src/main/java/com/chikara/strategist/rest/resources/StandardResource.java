package com.chikara.strategist.rest.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chikara.strategist.dao.IStandardDao;

/**
 * @author Ankush Sood <soodankush@gmail.com>
 */
@Path("/standard")
@Component
public class StandardResource
{
	
	@Autowired
	private IStandardDao standardDao;


	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getStandards()
    {
		List<Map<String, Object>> students = standardDao.getStandardList();
    	return students;
    }
}