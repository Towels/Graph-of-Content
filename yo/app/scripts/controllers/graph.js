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
.controller('GraphCtrl', function($scope, $http, graphURL, graphRenderer){
	$http.get(graphURL+'/get/1').then(function(response) {
		graphRenderer.setGraphData(response.data);
	});
	
});