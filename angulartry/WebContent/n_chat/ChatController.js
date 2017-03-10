	app.controller("ChatController" , function($scope, ChatService) {
	$scope.messages = [];
    $scope.message = "";
    $scope.name = "";
    $scope.max = 140;
    
    $scope.init = function(name)
    {
    	
        $scope.name = name; 
        alert("Name = "+$scope.name);
      
    };
    
    $scope.addMessage = function() {
    	console.log("addMessage inside chat controller");
    	console.log("message = "+$scope.message);
    	console.log($scope.name + " is sending messge");
    ChatService.send($scope.message,$scope.name);
    	$scope.message = "";
    	
    };
    
    ChatService.recieve().then(null , null, function(message) {
         console.log("recieve") 
         
       $scope.messages.push(message);  
    });
}); 