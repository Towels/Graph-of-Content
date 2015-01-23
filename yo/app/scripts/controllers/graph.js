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
	/*$http.get(graphURL+'/1').then(function(response) {
		graphRenderer.setGraphData(response.data);
	});*/
	var g = {
		      nodes: [],
		      edges: []
		    };

	graphRenderer.setGraphData(g);
	
	$scope.addNode = function(){
		$scope.nodeTransfer = {
			id: 'm'+Math.random(),
			label: $scope.nodeLabel,
			x: $scope.nodeXPos,
			y: $scope.nodeYPos,
			type: $scope.nodeType,
			size: 1
		};
		
		g.nodes.push($scope.nodeTransfer);
		graphRenderer.addNode($scope.nodeTransfer);
		
	};
	
	
	
});

	
