'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the graphOfContentApp
 */
angular.module('graphOfContentApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
