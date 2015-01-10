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
  .controller('LectureCtrl', function($scope, lectureURL){
    $scope.api = lectureURL;
  });
