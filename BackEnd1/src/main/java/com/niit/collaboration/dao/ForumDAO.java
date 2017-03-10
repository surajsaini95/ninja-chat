package com.niit.collaboration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.niit.collaboration.model.Forum;

@Repository
public interface ForumDAO {

	
	
		
	  public void delete(int forumid);
		public List<Forum> list();
		 public boolean save(Forum forum);
	    public Forum get(int forumid);

	}

