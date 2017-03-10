'use strict'

app.factory('ForumService', ['$http', '$q','$rootScope', function($http,$q,$rootScope){

	console.log("ForumService..")
	
	var BASE_URL="http://localhost:8086/BackEnd1";
	

	
	return {
	
	fetchAllForums: function(){
	
	
		
		return $http.get(BASE_URL+'/ForumDetails/')
			 .then(
					 function (response) {
			 	return response.data;
			 },function (errResponse) {
			 	console.error('Error while fetching Forums!');
			 	return $q.reject(errResponse);
		});

			 

	},
		
	createForum: function(forum)
		{
			
			console.log('inside forum service with '+forum)
			return $http.post(BASE_URL+'/ForumSave/',forum)
			.then(
					function(response){
						alert('forum created successfully');
						return response.data;
						
					},
					function(errResponse){
						console.error('Error while creating forum');
						return $q.reject(errResponse);
					});
		},
	
	 DeleteForum: function(forum)
	{
		console.log('inside Forum service for  with '+forum)
		return $http.post(BASE_URL+'/admin/del/'+forum) 
		.then(
				function(response){
					return response.data;
					
				},
				function(errResponse){
					console.error('Error while creating forum');
					return $q.reject(errResponse);
				});
	},
	
}
}])