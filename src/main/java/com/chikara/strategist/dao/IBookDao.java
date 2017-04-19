package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import com.chikara.strategist.entity.Book;

public interface IBookDao extends Dao<Book, String>{

	List<Map<String, Object>> getBooksForSubject(String subjectId);
}
