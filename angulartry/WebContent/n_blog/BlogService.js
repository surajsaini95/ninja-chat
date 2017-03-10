'use strict'

app.factory('BlogService',['$http', '$q','$rootScope', function($http,$q,$rootScope){
	console.log("BlogService...")
	
	var BASE_URL="http://localhost:8086/BackEnd1"
		return{
		fetchAllBlogs: function(){
			return $http.get(BASE_URL+'/blogs')
			.then(
					function(response){	
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching Blogs');
						return $q.reject(errResponse);
					}
					);
		},
		
		fetchAllBlogComments : function(){
			return $http.get(BASE_URL+'/blogcomments')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while fetching BlogComments');
						return $q.reject(errResponse);
					}
					);
		},

		
		createBlog: function(blog){
			return $http.post(BASE_URL+'/blog/',blog)
			.then(function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating blog');
				return $q.reject(errResponse);
			});
		},
		
		commentBlog: function(blogcomment,id,comment,ratingvalue){
			console.log("Your comment is "+blogcomment.bcomment)
			return $http.post(BASE_URL+'/blogcomment/'+id+'/'+comment+'/'+ratingvalue,blogcomment)
			.then(function(response){
				return response.data;
			},
			function(errResponse){
				console.error('Error while creating blogComment');
				return $q.reject(errResponse);
			});
		},
		
		getBlog: function(id){
			return $http.get(BASE_URL+'/blog/'+id)
			.then(
					function(response){
						$rootScope.selectedBlog = response.data
						return response.data;
					},
					function(errResponse){
						console.error("Error while getting blog");
						return $q.reject(errResponse);
					});
		},
		
		getBlogComment: function(blogID){
			return $http.get(BASE_URL+'/blogcomment/'+blogID)
			.then(
					function(response){
						/*$rootScope.selectedBlogComment = response.data*/
						console.log("comments in blogservice = "+response.data);
						return response.data;
					},
					function(errResponse){
						console.error("Error while getting blogcomment");
						return $q.reject(errResponse);
					});
		},
		
		deleteBlog: function(id){
			return $http.delete(BASE_URL+'/blog/'+id)
			.then(
					function(response){
						return response.data;
						
					},
					function(errResponse){
						console.error('Error while deleting blog');
						return $q.reject(errResponse);
					});
		},
		
	}
	}])
