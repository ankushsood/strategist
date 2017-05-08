package com.chikara.strategist.dao;

import java.util.List;
import java.util.Map;

import com.chikara.strategist.entity.Chapter;

public interface IChapterDao extends Dao<Chapter, String>{

	List<Map<String, Object>> getChaptersForBook(String bookId);
	void saveChapter(String bookId, Chapter chapter);
	void editChapter(String bookId, Chapter chapter);
	void deleteChapter(String chapterId);
}
