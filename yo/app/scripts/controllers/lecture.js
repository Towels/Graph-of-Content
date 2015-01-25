'use strict';

/**
* @ngdoc function
* @name graphOfContentApp.controller:LectureCtrl
* @description
* # LectureCtrl
* Controller of the graphOfContentApp
* Controls Tab Navigation Bar Highlighting
*/
angular.module('graphOfContentApp')
.controller('AllLecturesCtrl', function($scope, Lecture, site, $location){
	Lecture.query(function(data) {
		$scope.lectures = data;
	});
	site.updateSite('Your Lectures');
	$scope.route = function(id){
		$location.path('/lecture/' + id);
	};

})
.controller('LectureCtrl', function($scope, $routeParams, Lecture, site, $location){
	Lecture.get({id: $routeParams.id}, function(data) {
		$scope.lecture = data;
		$scope.route = function(route){
			$location.path('/lecture/id-' + $routeParams.id + '/' + route);
		};
		$scope.centerGraph = function(){
			//TODO center graph
		};
		site.updateSite('Lecture > ' + $scope.lecture.name, '');
	});
})
.controller('CreateLectureCtrl', function($scope, Lecture, site, $location){
	$scope.visibility = 'PRIVATE';
	$scope.submit = function(){
		if($scope.name !== undefined && $scope.university !== undefined && $scope.professor !== undefined){
			var lecture = new Lecture({
				name: $scope.name,
				professor: $scope.professor,
				university: $scope.university,
				visibility: $scope.visibility
			});
			lecture.$save(function(response){
				$location.path('/lecture/id-' + response.id);
			});	
		}
	};
		site.updateSite('Lecture > New Lecture','');
})
.controller('EditLectureCtrl', function($scope, Lecture, site, $location, $routeParams){
	Lecture.get({id: $routeParams.id}, function(data) {
		$scope.lecture = data;
		site.updateSite('Lecture > ' + $scope.lecture.name + ' > Edit', '');
	});
	$scope.submit = function(){
		if($scope.lecture.name !== undefined && $scope.lecture.university !== undefined && $scope.lecture.professor !== undefined){
			Lecture.update({id: $scope.lecture.id}, $scope.lecture);
			$location.path('/lecture/id-' + $routeParams.id);	
		}
	};
})
.factory('Lecture', function($resource, lectureURL) {
	return $resource(lectureURL + '/:id', null, {
		'update': {method: 'PUT'}
	});
});