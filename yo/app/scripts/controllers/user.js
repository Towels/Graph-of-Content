'use strict';
//TODO fix documentation
/**
 * @ngdoc function
 * @name graphOfContentApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the graphOfContentApp
 * Controls Tab Navigation Bar Highlightin
 */
angular.module('graphOfContentApp')
.controller('UserCtrl', function($scope, $routeParams, site){ // $resource
	var getByName = function(name){
		// TODO replace this with api shenanigans
		var active = true;
		var email = 'test@example.com';
		var dateCreated= new Date().getTime(); //in millis
		return {
			name: name,
			email: email,
			dateCreated: dateCreated,
			active: active,
			activeString: function(){
				if(active){
					return 'active';
				}
				else {
					return 'inactive';
				}
			}
		};
	};
	$scope.user = getByName($routeParams.name);
	
	$scope.exists = function(){
		if($scope.user.name !== undefined){
			site.updateSite('User > ' + $scope.user.name, 'Profile page of the user "' + $scope.user.name + '".');
			return true;
		}
		else{
			site.updateSite('User > Unknown User', 'User does not exist.');
			return false;
		}
	};

	//TODO Test code. Remove this:
	$scope.update = function() {
		if($scope.email !== null && $scope.email !== undefined){
			$scope.user.email = $scope.email;
		}
		if($scope.username !== null && $scope.username !== undefined){
			$scope.user.name = $scope.username;
		}
	};

});
