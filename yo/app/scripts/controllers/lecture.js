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
.controller('AllLecturesCtrl', function($scope, $resource, Lecture, site){
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
.factory('Lecture', function($resource, lectureURL) {
	return $resource(lectureURL + '/:id');
});