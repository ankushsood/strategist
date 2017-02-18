package com.chikara.strategist.dao.blogpost;

import com.chikara.strategist.dao.Dao;
import com.chikara.strategist.entity.BlogPost;

/**
 * Definition of a Data Access Object that can perform CRUD Operations for {@link BlogPost}s.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
public interface BlogPostDao extends Dao<BlogPost, Long>
{
}
