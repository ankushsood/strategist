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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chikara.strategist.entity.Book;
import com.chikara.strategist.entity.Chapter;
import com.chikara.strategist.entity.Student;
import com.chikara.strategist.entity.Subject;

public class ChapterDAO extends JpaDao<Chapter, String>  implements IChapterDao{

	private String GET_BOOK_CHAPTERS = "SELECT new map(c.id as chapterId, c.chapterTitle as title, c.chapterSummary as summary, b.bookTitle as bookTitle, c.chapterJSON as chapterJSON) from Chapter c JOIN c.book b WHERE c.book.id = :bookId"; 

	public ChapterDAO() {
		super(Chapter.class);
	}

	@Autowired
	private NamedParameterJdbcTemplate sqlTemplate;
	
	private HibernateTemplate hibernateTemplate;
	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getChaptersForBook(String bookId) {
	
		Query query2 = getEntityManager().createQuery(GET_BOOK_CHAPTERS);
		query2.setParameter("bookId", bookId);
		return query2.getResultList();
		
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveChapter(String bookId, Chapter chapter) {
		
		chapter.setBook(getEntityManager().find(Book.class, bookId));
		save(chapter);
		
		
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void editChapter(String bookId, Chapter chapter) {
		Chapter dbChapter = getEntityManager().find(this.entityClass, chapter.getId());
		dbChapter.setChapterSummary(chapter.getChapterSummary());
		dbChapter.setChapterTitle(chapter.getChapterTitle());
	}


	@Override
	public void deleteChapter(String chapterId) {
		// TODO Auto-generated method stub
		
	}
}