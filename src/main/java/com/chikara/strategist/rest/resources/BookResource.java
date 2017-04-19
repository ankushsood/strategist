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

import com.chikara.strategist.dao.IBookDao;

/**
 * @author Ankush Sood <soodankush@gmail.com>
 */
@Path("/book")
@Component
public class BookResource
{
	
	@Autowired
	private IBookDao bookDao;

	@GET
	@Path("/getBooksForSubject/{subjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getBooksForSubject(@PathParam("subjectId") String subjectId)
    {
		List<Map<String, Object>> standards = bookDao.getBooksForSubject(subjectId);
    	return standards;
    }

}