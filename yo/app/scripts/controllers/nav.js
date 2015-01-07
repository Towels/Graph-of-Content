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
.controller('NavCtrl', function($scope, $timeout, $mdSidenav, site){
  $scope.site = site;
  site.updateSite('Graph of Content', 'A spectacular Web app');

  $scope.toggleLeft = function() {
    $mdSidenav('left').toggle();
  };
  $scope.close = function() {
    $mdSidenav('left').close();
  };
});