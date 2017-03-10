var app=angular.module('myApp',['ngRoute', 'ngCookies']);

app.config(function($routeProvider){
	
	$routeProvider
	
	.when('/',{ 
		templateUrl : 'n_common/home.html',
		controller : 'HomeController'
	})

	
	.when('/login',{ 
		templateUrl : 'n_user/login.html',
		controller : 'UserController'
	})
	
	.when('/logout',{
	templateUrl : 'n_common/home.html',
	controller : 'HomeController'
    })
    
    .when('/register',{
    templateUrl : 'n_user/register.html',
    controller : 'UserController'
    })

   .when('/myprofile',{
	templateUrl : 'n_user/my_profile.html',
	controller  : 'UserController'
    })
	
	.when('/blog_home',{ 
		templateUrl : 'n_blog/blog_home.html'
	})
	
	.when('/create_blog',{
	templateUrl : 'n_blog/create_blog.html',
	controller : 'BlogController'
	})
	
   .when('/list_blog',{
	templateUrl : 'n_blog/list_blog.html',
		controller : 'BlogController'	
   })

   .when('/view_blog',{
	templateUrl : 'n_blog/view_blog.html',
	controller : 'BlogController'
	})

	.when('/chat_home',{ 
		templateUrl : 'n_chat/chat_home.html'
	})
	
	.when('/friend_home',{ 
		templateUrl : 'n_friend/friend_home.html'
	})
	
	.when('/job_home',{ 
		templateUrl : 'n_job/job_home.html'
	})
	
	.when('/manage_users',{  //when() method takes a pathand a route as parameters.
		templateUrl : 'n_admin/manage_users.html',
		controller : 'UserController'
	})
	
	.when('/manage_jobs',{ //when() method takes a panthad a route as parameters.
		templateUrl  :  'n_admin/manage_jobs.html',
		controller   :  'UserController'
	})
	
	.when('/manage_friends',{//when() method takes a panthad a route as parameters.
		templateUrl  : 'n_admin/manage_friends.html',
		controller   :  'UserController'
	})
	
	.when('/adminhome',{
	templateUrl : 'n_admin/adminhome.html'
    })

    .when('/view_applied_jobs',{
	templateUrl : 'n_job/view_applied_jobs.html',
	controller : 'JobController'
	})
	
   .when('/post_job',{
	templateUrl : 'n_job/post_job.html',
		controller : 'JobController'	
    })
    
   .when('/view_job_details',{
	templateUrl : 'n_job/view_job_details.html',
	controller : 'JobController'
	})
	
   .when('/search_job',{
	templateUrl : 'n_job/search_job.html',
	controller : 'JobController'	
    })
	
     .when('/add_friend',{
	templateUrl : 'n_friend/add_friend.html',
	controller : 'FriendController'
	})
	
	.when('/search_friend',{
		templateUrl : 'n_friend/search_friend.html',
		controller : 'FriendController'
	})

	.when('/view_friend',{
		templateUrl : 'n_friend/view_friend.html',
		controller : 'FriendController'
	})
	
	 .when('/chat',{
	templateUrl : 'n_chat/chat.html',
	controller : 'ChatController'
   })
   
    .when('/chat_forum',{
	templateUrl : 'n_chat_forum/chat_forum.html',
	controller : 'ChatForumController'
   })
   
    .when('/chat_forum_window',{
	templateUrl : 'n_chat_forum/chat_forum_window.html',
	controller : 'ChatForumController'
   })
   
   
   .when('/forum_home',{
		templateUrl : 'n_forum/forum_home.html'
		
	})

	.when('/create_forum',{
		templateUrl : 'n_forum/create_forum.html',
		controller : 'ForumController'
	})
   
	.when('/list_forum',{
		templateUrl : 'n_forum/list_forum.html',
		controller : 'ForumController'
	})
	
	.otherwise({redirectTo: '/'});
})

	app.run(function($rootScope, $location, $cookieStore, $http){
	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		  //http://localhost:8081/Collaboration/addjob
	         //redirect to login page if not logged in and trying to access a restricted page
		  
		 var restrictedPage=$.inArray($location.path(),['/admin'])=== 1;
		// -1 ----> non-restricted pages are more and for restricted pages ----> 1 ;
		 console.log("restrictedPage:" +restrictedPage)
	     var loggedIn = $rootScope.currentUser.id;
	     
		 console.log("loggedIn:"+loggedIn)
/*		 if(restrictedPage & !loggedIn){
			console.log("Navigating to login page:")
			alert("You are not logged and so youcant apply for the job")
			$location.path('/login');
		 }
	 });
*/	 if (!loggedIn) 
     {
    	 
    	 if(restrictedPage) {
    		 console.log("Navigating to login page:")
    		 
    		 $location.path('/login');
    	 }
    	  
     }
     else //logged in
    	 {
    	 
    	 var role = $rootScope.currentUser.role;
    	 var userRestrictedPage = $.inArray($location.path(), ['/post_job','/adminhome']) ===0;
    	 
    	 if (userRestrictedPage && role!='admin')
    		 {
    		 alert("You cannot do this operation as you are not logged in as:"+role)
    		 $location.path('/login');
    		 }
    	 }
    	  
});
		
	   //keep user logged in after page refresh

	 $rootScope.currentUser = $cookieStore.get('currentUser') ||{};
	 if($rootScope.currentUser){
		 $http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;
		 }
});

