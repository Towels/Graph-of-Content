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
.controller('GraphCtrl', function($scope, Graph, graphRenderer, $routeParams){
	Graph.get({id: $routeParams.id}, function(data) {
		graphRenderer.setGraphData(data);
	});
	
})
.factory('Graph', function($resource, graphURL) {
	return $resource(graphURL + '/:id');
});