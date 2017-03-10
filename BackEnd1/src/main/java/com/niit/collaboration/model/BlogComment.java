package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="c_blog_comment")
@Component
public class BlogComment extends BaseDomain{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private int blogID;
	private String userID;
	private String bcomment;
	private Date dateTime;
	private String rating;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	public String getBlogcomment() {
		return bcomment;
	}
	public void setBlogcomment(String blogcomment) {
		this.bcomment = blogcomment;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		if(dateTime==null)
		{
			dateTime = new Date( System.currentTimeMillis());
		}
		this.dateTime = dateTime;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	

}
