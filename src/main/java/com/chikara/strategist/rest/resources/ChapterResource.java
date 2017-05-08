package com.chikara.strategist.rest.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chikara.strategist.dao.IBookDao;
import com.chikara.strategist.dao.IChapterDao;
import com.chikara.strategist.entity.Book;
import com.chikara.strategist.entity.Chapter;

/**
 * @author Ankush Sood <soodankush@gmail.com>
 */
@Path("/chapter")
@Component
public class ChapterResource
{
	
	@Autowired
	private IChapterDao chapterDao;

	@GET
	@Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getBooksForSubject(@PathParam("bookId") String bookId)
    {
		List<Map<String, Object>> chapters = chapterDao.getChaptersForBook(bookId);
    	return chapters;
    }

	@POST
	@Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> saveBookForSubject(@PathParam("bookId") String bookId, Chapter chapter)
    {
		chapterDao.saveChapter(bookId, chapter);
		return chapterDao.getChaptersForBook(bookId);
    }


	@PUT
	@Path("/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> updateBookForSubject(@PathParam("bookId") String bookId, Chapter chapter)
    {
		chapterDao.editChapter(bookId, chapter);
		return chapterDao.getChaptersForBook(bookId);
    }
}