package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.BlogComment;

@Repository("blogCommentDAO")
public interface BlogCommentDAO {

	public boolean save(BlogComment blogComment);
	public List<BlogComment> list();
	public List<BlogComment> get(int blogID); 

}
