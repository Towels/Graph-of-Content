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
.controller('AllLecturesCtrl', function($scope, Lecture, site){
	Lecture.query(function(data) {
		$scope.lectures = data;
	});
	site.updateSite('Your Lectures', 'All Lectures');

})
.controller('LectureCtrl', function($scope, $routeParams, Lecture, site){
	Lecture.get({id: $routeParams.id}, function(data) {
		$scope.lecture = data;
		site.updateSite('Lecture > ' + $scope.lecture.name, '');
	});
})
.controller('CreateLectureCtrl', function($scope, Lecture, site, $location){
	$scope.visibility = 'PRIVATE';
	$scope.submit = function(){
		if($scope.name !== undefined && 
			$scope.university !== undefined && 
			$scope.professor !== undefined){
			var lecture = new Lecture({
				name: $scope.name,
				professor: $scope.professor,
				university: $scope.university,
				visibility: $scope.visibility
			});
			lecture.$save(function(response){
				$location.path('/lecture/id-' + response.id);
			});
			
		}};
		site.updateSite('Lecture > New Lecture','');
})
.factory('Lecture', function($resource, lectureURL) {
	return $resource(lectureURL + '/:id');
});