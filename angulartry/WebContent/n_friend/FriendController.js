'use strict';

app.controller("FriendController", ['UserService','$scope','FriendService','$location','$rootScope',   
                                    function(UserService,$scope,FriendService,$location,$rootScope){
	console.log("FriendController...")
  	var self = this;
	self.friend ={
			id : '',
			userID : '',
			friendID : '',
			status : '',
			isOnline : '',
			errorMessage : '',
			errorCode : ''
	};
	self.friends = [];
	
	self.allFriends ={
			id : '',
			userID : '',
			friendID : '',
			status : '',
			isOnline : '',
			errorMessage : '',
			errorCode : ''
	};
	self.allFriends = [];
	
	self.user = {
			id : '',
			name :'',
			email : '',
			password : '',
			mob_no : '',
			dob : '',
			gender : '',
			role : '',
			errorMessage : '',
			errorCode : ''
	};
	self.users = [];
	
	self.sendFriendRequest=sendFriendRequest
	function sendFriendRequest(friendID)
	{
		console.log("->sendFriendRequest :"+friendID);
		FriendService.sendFriendRequest(friendID)
		.then
		(function(d){
			self.friend = d;
			alert("Friend request sent to "+friendID)
			 $location.path("/view_friend");
			
		},
		  function(errResponse){
			console.error('Error while sending friend request');
		}
		);
	}
	
	self.getMyFriends = function(){
		console.log("Getting my friends on view_friend page");
		FriendService.getMyFriends()
		.then(
		     function(d)	{
		    	 self.friends = d;
					alert("inside getMyFriends in controller")
		     },
		     function(errResponse){
					console.error('Error while sending friend request');
				}
		);
	};
	
	self.updateFriendRequest = function(friend, id){
		FriendService.updateFriendRequest(friend, id)
		.then(
				self.fetchAllFriends,
			     function(errResponse){
						console.error('Error while updating friend');
					} 		
		);
	};
	
	self.deleteFriend = function(id){
		FriendService.deleteFriend(id)
		.then(
				self.fetchAllFriends,
			     function(errResponse){
						console.error('Error while deleting friend');
					} 		
		);
	};
	
	self.fetchAllUsers = function(id){
		UserService.fetchAllUsers().then(function(d){
			self.users = d;
			alert("inside fetchAllUsers in controller")
		},
			     function(errResponse){
						console.error('Error while feteching users');
					} 		
		);
	};
	
	self.getAllFriendRequests = function(){
    	console.log("getAllFriendRequests")
		FriendService.getAllFriendRequests().then(function(d){
			self.allFriends = d;
		}, function(errResponse){
			console.error('Error while  getAllFriendRequests');
		});
	};
	
	self.acceptFriendRequest = function(ID)
    {
   	 console.log('inside FriendController acceptFriendRequest method with ID '+ ID)
         FriendService.acceptFriendRequest(ID)
                 .then( 
                         self.getAllFriendRequests, 
                         function(errResponse){
                              console.error('Error while acceptFriendRequest');
                         } 
             );
     };
     
     self.rejectFriendRequest = function(ID)
     {
   	  console.log('inside FriendController rejectFriendRequest with  ID '+ ID)
         FriendService.rejectFriendRequest(ID)
                 .then( 
                         self.getAllFriendRequests, 
                         function(errResponse){
                              console.error('Error while rejectFriendRequest');
                         } 
             );
     };
	
	self.fetchAllUsers();
	
	self.getMyFriends();
	
	self.getAllFriendRequests();
	
}])
  

	
	