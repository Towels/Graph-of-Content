'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the graphOfContentApp
 */
angular.module('graphOfContentApp')
  .controller('MainCtrl', function ($scope, site) {
    $scope.site = site;
   	$scope.site.updateSite('Welcome');
  });
