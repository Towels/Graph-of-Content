'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:TabCtrl
 * @description
 * # TabCtrl
 * Controller of the graphOfContentApp
 * Controls Tab Navigation Bar Highlighting
 */
angular.module('graphOfContentApp')
  .controller('TabCtrl', function($scope){

    var tab = 0;
    //set active tab
    $scope.setTab = function(newTab){
      tab = newTab;
    };
    //returns true if this is the active tab
    $scope.isTab = function(checkTab){
      return tab === checkTab;
    };
  });
