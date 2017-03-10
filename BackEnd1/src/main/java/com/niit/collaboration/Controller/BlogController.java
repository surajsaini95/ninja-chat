package com.niit.collaboration.Controller;    //tested

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

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@RestController
public class BlogController {

	@Autowired
	private BlogDAO blogDAO;
	
	@Autowired
	private Blog blog;
	
	@RequestMapping(value = "/blogs" , method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getblogs()
	{
		List<Blog> blogs = blogDAO.list();
		if(blogs == null)
		{
			blog = new Blog();
			 blog.setErrorCode("404");
       	  blog.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		 else
         {
       	  return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/blog/{id}" , method = RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("id")int id){
		
		Blog blog = blogDAO.get(id);
		if(blog == null)
		{
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog not found ");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);		
		}
		else
		{
			blog.setErrorCode("200");
		    blog.setErrorMessage("Blog found");
	
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	
			
		}
		
	}
	
	@RequestMapping(value = "/blog/" , method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog, HttpSession httpsession) {
		
   		String loggedInuserID = (String) httpsession.getAttribute("loggedInUserID");
	
		blog.setUserID(loggedInuserID);
		blog.setStatus("N");
		blog.setDateTime(null);
		blogDAO.save(blog);
		blog.setErrorCode("200");
		blog.setErrorMessage("Blog created successfully");
	
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@RequestMapping(value="/blog/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable int id) {
		
		
		if(blogDAO.get(id)==null) {
			System.out.println("blog not found for deleting");
			blog.setErrorCode("404");
			blog.setErrorMessage("blog not found");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		else
		{  System.out.println("blog deleted......");
			blog.setErrorCode("200");
			blog.setErrorMessage("blog deleted successfully");
				
		blogDAO.delete(id);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}

	
	
	@RequestMapping(value="/acceptblog/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<Blog> acceptBlog(@PathVariable("id") String id, @RequestBody Blog blog ) 
	   {
		   
		  blog = blogDAO.get(blog.getId());
		  if(blog==null)
		  {
			  blog= new Blog();
			  blog.setErrorMessage("Blog does not exist ");
			   return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		  }
		  
		  blog.setStatus("A");
		   
		  blogDAO.update(blog);
		  blog.setErrorCode("200");
		  blog.setErrorMessage("your blog is approved by admin");
		 
		   return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	   }
	
	@RequestMapping(value="/rejectblog/{id}" , method = RequestMethod.PUT)
	   public  ResponseEntity<Blog> rejectBlog(@PathVariable("id") String id, @RequestBody Blog blog ) 
	   {
		   
		  blog = blogDAO.get(blog.getId());
		  if(blog==null)
		  {
			  blog= new Blog();
			  blog.setErrorMessage("Blog does not exist ");
			   return new ResponseEntity<Blog>(blog, HttpStatus.NOT_FOUND);
		  }
		  
		  blog.setStatus("R");
		   
		  blogDAO.update(blog);
		  blog.setErrorCode("200");
		  blog.setErrorMessage("your blog is rejected by admin");
		 
		   return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	   }
	  

	   
	  
	
}
