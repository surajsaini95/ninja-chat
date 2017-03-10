package com.niit.collaboration.Controller;   //tested

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogCommentDAO;
import com.niit.collaboration.model.BlogComment;

@RestController
public class BlogCommentController {

	@Autowired
	private BlogCommentDAO blogCommentDAO;
	
	@Autowired
	private BlogComment blogComment;
	
	@RequestMapping(value = "/blogcomments" , method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getblogcomments()
	{
		List<BlogComment> blogcomment = blogCommentDAO.list();
		if(blogcomment == null)
		{
			blogComment = new BlogComment();
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		 else
		 {
			 System.out.println("Comments are = "+blogcomment);
       	  return new ResponseEntity<List<BlogComment>>(blogcomment,HttpStatus.OK);
         }
	}

	@RequestMapping(value = "/blogcomment/{id}" , method = RequestMethod.GET)
	public  ResponseEntity<List<BlogComment>> getBlog(@PathVariable("id")int id){
		
		List<BlogComment> blogcomment = blogCommentDAO.get(id);
		//System.out.println(blogcoment.getBlogcomment());
		if(blogcomment == null)
		{
			//blogcomment = new BlogComment();
			//blogcomment.setErrorCode("404");
			//blogcomment.setErrorMessage("Blog not found with the id"+ id);
		}
		else
		{
			//System.out.println("Comments are = "+);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogcomment,HttpStatus.OK);
	}

	
      	@RequestMapping(value = "/blogcomment/{id}/{comment}/{rating}" , method = RequestMethod.POST)
	public ResponseEntity<BlogComment> createBlog(@RequestBody BlogComment blogcomment, @PathVariable("id") int id,
			                @PathVariable("comment") String comment, @PathVariable("rating") String rating, HttpSession httpsession)
      	
      	{
		System.out.println("Comment from the front end "+blogComment.getBlogcomment());
   		String loggedInuserID = (String) httpsession.getAttribute("loggedInUserID");
	
   		blogComment.setUserID(loggedInuserID);  	
   		blogComment.setBlogID(id);
   		blogComment.setBlogcomment(comment);
   		blogComment.setRating(rating);
   		blogComment.setDateTime(null);
		blogCommentDAO.save(blogComment);
		
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}

}
