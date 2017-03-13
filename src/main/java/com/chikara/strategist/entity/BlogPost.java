package com.chikara.strategist.entity;

import com.chikara.strategist.JsonViews;
import org.codehaus.jackson.map.annotate.JsonView;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * JPA Annotated Pojo that represents a blog post.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
@javax.persistence.Entity
public class BlogPost implements Entity
{
    @Id
    @GeneratedValue
    private String id;

    @Column
    private Date date;

    @Column
    private String content;

    public BlogPost()
    {
        this.date = new Date();
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return String.format("BlogPost[%d, %s]", this.id, this.content);
    }

	@Override
	public String getEntityId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
