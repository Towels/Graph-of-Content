'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the graphOfContentApp
 */
angular.module('graphOfContentApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
