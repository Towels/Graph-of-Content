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
.controller('NavCtrl', function($scope, $timeout, $mdSidenav, site, authSrv){
  $scope.site = site;
  site.updateSite('Graph of Content', 'A spectacular Web app');
  
  $scope.loggedIn;
  
  $scope.$watch(function() {
	  return authSrv.loggedIn;
  },function(newValue, oldValue) {
	  $scope.loggedIn = newValue;
  });
  
  $scope.toggleLeft = function() {
    $mdSidenav('left').toggle();
  };
  $scope.close = function() {
    $mdSidenav('left').close();
  };
})//TODO move this into proper place
.directive('resize', function ($window) {
	return function (scope) {
		var w = $window;
		scope.getWindowDimensions = function () {
			return { h: w.innerHeight};
		};
		scope.$watch(scope.getWindowDimensions, function (newValue) {
			scope.windowHeight = newValue.h;
			var viewPadding = 64 + ( 2 * 20 ); // toolbar height + 2* whiteframe padding 
            var framePadding = viewPadding + ( 2 * 20 ); // additional 2* whiteframe padding

            scope.viewStyle = function () {
				return { 
                    height: (newValue.h - viewPadding) + 'px'
                };
			};
			scope.frameStyle = function () {
				return { 
                    height: (newValue.h - framePadding) + 'px'
                };
			};
            
		}, true);
		angular.element($window).bind('resize', function () {
			scope.$apply();
		});
	};
});