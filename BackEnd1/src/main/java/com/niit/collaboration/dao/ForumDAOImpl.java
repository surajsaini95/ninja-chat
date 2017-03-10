package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Forum;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("forumDAO")

public class ForumDAOImpl implements ForumDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}


	@Transactional
	public boolean save(Forum forum){	
		
		try{
		  sessionFactory.getCurrentSession().save(forum);
	return true;
		}catch (Exception e ){
			//TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
	
	@Transactional
	public void delete(int ForumId) 
	{
		System.out.println("Before Delete"+ForumId);
		Forum forumToDelete = new Forum();
		forumToDelete.setId(ForumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);
		System.out.println("After Delete"+ForumId);
	}

	
	
	
	@Transactional
	public Forum get(int forumid) 
	{
		String hql = "from Forum  where id=" + "'" + forumid + "'";
		// from user where id = '101'
		System.out.println("in ForumDAOIMPL "+forumid);
		@SuppressWarnings({ })
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
System.out.println("After Proessing Query in ForumDAOImpl ");
		@SuppressWarnings({ "unchecked" })
		List<Forum> listForum = (List<Forum>) query.list();
		
		System.out.println("After retriving list");
		
		if (listForum != null && !listForum.isEmpty()) {
			return listForum.get(0);
		}
		return null;
	}
	
	public List<Forum> list() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")

		List<Forum> forumDetailsList = session.createQuery("from Forum").list();
		System.out.println("--------->>>>>> Query Fired");
		session.close();
		System.out.println("--------->>>>>> Returning Forum Details");
		return forumDetailsList;
	}


}
