package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chikara.strategist.entity.Book;
import com.chikara.strategist.entity.Subject;

@Repository
public class BookDAO extends JpaDao<Book, String> implements IBookDao {
	
	private String GET_SUBJECT_BOOK = "SELECT new map(b.bookType as bookType,b.bookSummary as bookSummary, b.bookTitle as bookTitle, b.id as bookId)  from Book b	WHERE b.subject.id = :subjectId"; 
	public BookDAO() {
		super(Book.class);
	}

	@Autowired
	private NamedParameterJdbcTemplate sqlTemplate;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getBooksForSubject(String subjectId) {
		Query query2 = getEntityManager().createQuery(GET_SUBJECT_BOOK);
		query2.setParameter("subjectId", subjectId);
		return query2.getResultList();
	}

	
	@Override
	public void saveBookForSubject(String subjectId, Book book) {
		book.setSubject(getEntityManager().find(Subject.class, subjectId));
		save(book);
	}
}
