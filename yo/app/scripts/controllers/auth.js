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
	
	$scope.credentials = {
		email: '',
		password: ''
	};
	
	$scope.$watch(function() {
		return authSrv.loggedIn;
	}, function(newValue){
		$scope.loggedIn = newValue;
	});
	
	$scope.message = '';
	
	$scope.login = function(){
		$http.post(authURL+'/login', $scope.credentials).then(function(response) {
			if(response.data === '') {
				$scope.message = 'Wrong email or password!';
			} else {
				  $http.defaults.headers.common['auth-token'] = response.data.authToken;
				  authSrv.setEmail(response.data.authId);
				  authSrv.setUuid(response.data.authToken);
				  authSrv.setLoggedIn(true);
				  $scope.message = '';
			}
		});
	};
	
	$scope.logout = function() {
		$http.post(authURL+'/logout', authSrv.user).then(
			function(response) {
				authSrv.setLoggedIn(false);
				authSrv.setEmail('');
				authSrv.setUuid('');
			},
			function(response){
				console.log('logout-Fehler');
			});
		
	};
});