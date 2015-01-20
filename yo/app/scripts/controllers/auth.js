'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:NavCtrl
 * @description
 * # NavCtrl
 * Controller of the graphOfContentApp
 * Controls Tab Navigation Bar Highlighting
 */

angular.module('graphOfContentApp')
.controller('LogonCtrl', function($scope, $http, $location, authURL, authSrv){
	
	$scope.email;
	$scope.password;
	$scope.loggedIn = authSrv.loggedIn;
	
	$scope.message;
	
	$scope.logon = function(){
		var credentials = {email: $scope.email, password: $scope.password}
		$http.post(authURL+"/login", credentials).then(function(response) {
			if(response.data === null) {
				$scope.message = "Wrong email or password!";
			} else {
				  $http.defaults.headers.common["X-AUTH-TOKEN"] = response.data.authToken;
				  authSrv.setEmail($scope.email);
				  authSrv.setLoggedIn(true);
				  $scope.message = "";
			}
		});
	};
	
	
	var interceptor = function() {
		  // Die Promise enthält eine Response; wir müssen wieder eine Promise zurückliefern
		  return function(promise) {
		    return promise.then(
		      function(response) { return response;}, // alles ok, dabei belassen wir es
		      function(response) {
		        if (response.status == 401) {
		          $location.path("/");
		          authSrv.setLoggedIn(false);
		        }
		        return $q.reject(response);
		      }
		    );
		  };
		};
	$http.interceptors.push(interceptor);
});