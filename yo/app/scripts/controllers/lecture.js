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
  .controller('LectureCtrl', function($scope, $resource, api){
    $scope.lecture = $resource(api.root + api.lecture + ':id',
    	{id: 1}
	);
	console.log(api.root + api.lecture);
	console.log($scope.lecture);
  });
