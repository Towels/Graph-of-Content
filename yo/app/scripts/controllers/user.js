'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the graphOfContentApp
 * Controls Tab Navigation Bar Highlighting
 */
angular.module('graphOfContentApp')
  .controller('UserCtrl', function($scope, site){
  	$scope.user = {
  		'name': 'miladiir',
  		'email': 'contact@oliverconzen.de'
  	};
    $scope.site = site;
    $scope.site.updateSite('User > ' + $scope.user.name, 'Profile page of the user "' + $scope.user.name + '".');
  });
