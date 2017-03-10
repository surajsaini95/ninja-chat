'use strict';
 
app.controller('ForumController',['$scope','ForumService','$location','$rootScope',   
  function($scope,ForumService,$location,$rootScope){
console.log("ForumController...")
	var self = this;
    self.forum={id : '',category:'',title:'',description:'', errorCode:'',
			  errorMessage:''};
    self.forums=[];
 
    fetchAllForums();
    
    
    function fetchAllForums() 
       {
		ForumService.fetchAllForums()
	    .then(function(data)
	    {
	    	console.log("inside fetchAllForums");
	   		self.forums= data;
		 },function (errResponse) 
		 {
			console.error('Error while fetching the Forums');
		})
	}

 
    
    function createForum(forum)
    {
    	alert("inside createForum of ForumController.js")
    	  ForumService.createForum(forum)
          .then(
          
          	self.fetchAllForums,
          
          function(errResponse){
              console.error('Error while creating Forum');
          })
    }
    /*
 
    self.createForum = function (user){
        ForumService.createForum(user)
            .then(
            
            	self.fetchAllForums,
            
            function(errResponse){
                console.error('Error while creating Forum');
            }
        );
    };
 
    self.updateForum = function (user, id){
        ForumService.updateForum(user, id)
            .then(
            self.fetchAllForums,
            function(errResponse){
                console.error('Error while updating Forum');
            }
        );
    };
 */
    
    function deleteForum2(forum)
    {
    	console.log("inside DeleteForum2 with forumid= ", forum)
    	  ForumService.DeleteForum(forum)
          .then(
          
          	self.fetchAllForums,
          
          function(errResponse){
              console.error('Error while deleting Forum');
          })
    }
    
    
    self.deleteForum = function (forum)
    {
    	console.log('Deleting forum with forumid= ', forum);
         deleteForum2(forum);
         

    };
  
 
    self.submit = function (){
    {
    	alert("Inside Forum Controller");
       
            createForum(self.forum);
        }
//            
        	
        self.reset();
    };
 
    self.reset = function ()
    {
    	self.forum={id:'',category:'',title:'',description:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);