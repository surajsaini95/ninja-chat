package com.niit.collaboration.Controller;

import java.util.List;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.collaboration.dao.ForumDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Forum;
@RestController
public class ForumController {
	
	@Autowired
	private ForumDAO forumDAO;
	
	
	@Autowired
	private Forum forum;

	
	
	
	@GetMapping("/ForumDetails/")
	public ResponseEntity<List<Forum>> listAllUserDetails(){
		List<Forum> forums = forumDAO.list();
		if(forums == null)
		{
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Forum>>(forums,HttpStatus.OK);
         }
	}
	
	
	@GetMapping("/forum/{username}")
	public ResponseEntity<Forum> getForum(@ModelAttribute("username") int id){
		
		Forum Forum= forumDAO.get(id);
		if (Forum == null){
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
			
		}
		return new ResponseEntity<Forum>(Forum, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/ForumSave/" , method = RequestMethod.POST)
public ResponseEntity<Forum> createForum(@RequestBody Forum forum, HttpSession httpsession) {
		
   	String loggedInuserID = (String) httpsession.getAttribute("loggedInUserID");
	
		//forum.setId(loggedInuserID);
		//blog.setStatus("N");
		System.out.println("before saving"+forum.getTitle());
		System.out.println(forum.getTitle());
		forumDAO.save(forum);
		System.out.println("after saving");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	

	

	
	@PostMapping("/admin/del/{user}")
	public ResponseEntity<Forum> deleteForum(@ModelAttribute("user")int id)
	{
		System.out.println(id);
		System.out.println("inside ForumController delete Forum");
		//int k=Integer.parseInt(id);
		Forum Forum= forumDAO.get(id);
		System.out.println("inside ForumController "+Forum.getTitle());
		if (Forum == null){
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
			
		}
		
		forumDAO.delete(id);
		return new ResponseEntity<Forum>(HttpStatus.NO_CONTENT);

	}

}