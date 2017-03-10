package com.niit.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.model.Friends;
import com.niit.collaboration.model.User;

@SuppressWarnings("deprecation")
@EnableTransactionManagement
@Repository("friendsDAO")
public class FriendsDAOImpl implements FriendsDAO {
	
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(FriendsDAOImpl.class);
	
		@Autowired
		private SessionFactory sessionFactory;
		public FriendsDAOImpl(SessionFactory sessionFactory)
		{
			this.sessionFactory=sessionFactory;
		}
		
		private Integer getMaxId()
		{
			String hql = "select max{id} from friend";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			Integer maxID = (Integer) query.uniqueResult();
			return maxID;
		}
		
		@Transactional
		public boolean save(Friends friends){	
			
			try{
			  sessionFactory.getCurrentSession().save(friends);
		return true;
			}catch (Exception e ){
				//TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}	
		@Transactional
		public boolean update(Friends friends){
			System.out.println("Inside FriendsDAOImpl ID = "+friends.getId());
			try{
				sessionFactory.getCurrentSession().update(friends);
				System.out.println("friendRequest updated Impl ");
				
		return true;
			} catch (Exception e){
				System.out.println("friendRequest update failed Impl");
				e.printStackTrace();
		       return false;
			}
		}
		
	/*	@Transactional
		public boolean delete(Friends friends){
			try{
		       sessionFactory.getCurrentSession().delete(friends);
		return true;
			} catch (Exception e){
				//TODO Auto-generated catch block
		       e.printStackTrace();
		       return false;
			}
		}*/
		
		@Transactional
		public Friends get(String userID, String friendID) {
			String hql = "from Friends where userID=" + "'" + userID + "' and friendID= '" + friendID + "N'";
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			List<Friends> list = (List<Friends>) query.list();
			
			if(list != null && !list.isEmpty()) {
				return list.get(0);
			}
			return null;
			
		}
		
		
		@Transactional
		public Friends getFriendRequest(int ID) {
			
			System.out.println("Inside FriendsDAOImpl new with ID = "+ID);
			
			String hql = "from Friends where ID = " + "'" + ID +"'" ;
			
			/*try {
				Friends f = (Friends) sessionFactory.getCurrentSession().createQuery(hql);
				System.out.println("got friendRequest in impl with id = "+f.getId());
				return f;
				
			} catch (HibernateException e) {
				System.out.println("error while getFriendRequest in FriendsDAOImpl");
				return null;
			}
			*/
			@SuppressWarnings({ "rawtypes" })
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			@SuppressWarnings({ "unchecked" })
			List<Friends> list=query.list();
			if(list==null || list.isEmpty())
			{
				
				return null;
			}
			else
			{
				return list.get(0);
			}
			
			
		}
		
		
		@Transactional
		public boolean delete(String userID, String friendID) {
			Friends friend = new Friends();
			friend.setFriendID(friendID);
			friend.setUserID(userID);
			sessionFactory.getCurrentSession().delete(friend);
			return true;
			
		}

		@Transactional
		public List<Friends> getMyFriend(String userID) {
			String hql = "from Friends where userID=" + "'" + userID + "' and status = '" + "N'";
			@SuppressWarnings("rawtypes")
			Query query =sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friends> list = (List<Friends>) query.list();
			return list;
			
		}

		
		@Transactional
		public List<Friends> getMyAcceptedFriend(String userID) {
			String hql = "from Friends where ( userID=" + "'" + userID + "' or friendID=" + "'" + userID +"' ) and status = '" + "A'";
			@SuppressWarnings("rawtypes")
			Query query =sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friends> list = (List<Friends>) query.list();
			return list;
			
		}

		
		@Transactional
		public List<Friends> getNewFriendRequests(String userID) {
			String hql = "from Friends where userID=" + "'" + userID + "' and status = '" + "N'";
			@SuppressWarnings("rawtypes")
			Query query =sessionFactory.getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Friends> list = (List<Friends>) query.list();
			return list;
		}

		
		@Transactional
		public void setOnline(String userID) {
			Logger.debug("Starting of the method setOnline");
			String hql = "UPDATE Friend SET isOnline = 'Y' where userID ='" + "'";
			Logger.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOnline");
		}
		

		@Transactional
		public void setOffLine(String userID) {
			Logger.debug("Starting of the method setOffline");
			String hql = "UPDATE Friend SET isOnline = 'N' where userID = '" + userID + "'";
			Logger.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
			Logger.debug("Ending of the method setOffline");
		}

		@SuppressWarnings("unchecked")
		@Transactional
		public List<Friends> list()
		{
			
			String hql = "from Friends";
		@SuppressWarnings("rawtypes")
		Query query =sessionFactory.getCurrentSession().createQuery(hql);
		
		List<User> listUser = query.list();
		if(listUser == null  || listUser.isEmpty())
		{
			 return null;
			 
		}
		return query.list();
		}

		public boolean updateIsOnline(String userID, String friendID,char isOnline) {
			Logger.debug("Starting of the method updateIsOnline in FriendDAOImpl");
			
			Logger.debug("Ending of the method updateIsOnline in FriendDAOImpl");
			return false;
		}
}
