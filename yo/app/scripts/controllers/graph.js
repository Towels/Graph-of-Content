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
.controller('GraphCtrl', function($scope, graphRenderer, $routeParams, Graph, Node, Edge){
	$scope.editMode = false;
	$scope.toogleEditMode = function(){
		$scope.editMode = !$scope.editMode;
	}
	$scope.update = function(){
		Graph.get({id : $routeParams.id}, function(g){
			console.log(g);
			graphRenderer.setGraphData(g);
		});
	};
	
	$scope.addNode = function(){
		var node = new Node();
		node.label = $scope.nodeLabel;
		node.x = $scope.nodeXPos;
		node.y = $scope.nodeYPos;
		node.type = $scope.nodeType;
		
		node.$save(function(){
			$scope.update();
		});
	};
	
	$scope.addEdge = function(){
		var edge = new Edge();
		edge.source = $scope.source;
		edge.target = $scope.target;
		$scope.source = $scope.target = '';
		edge.$save(function(){
			$scope.update();
		});
	}
	graphRenderer.setClickNode(function(e){
		if($scope.source != undefined && $scope.source != null && $scope.source != "") {
			$scope.target = e.data.node.id;
		}else {
			$scope.source = e.data.node.id;
		}	
		console.log('source:'+$scope.source+'target:'+$scope.target);
	});
	
	$scope.update();
})
.factory('Node', function($resource, graphURL, $routeParams) {
	return $resource(graphURL+'/'+$routeParams.id+'/node/:id', {id:'@id'}, {
		'update': {method: 'PUT'}
	});
})
.factory('Edge', function($resource, graphURL, $routeParams) {
	return $resource(graphURL+'/'+$routeParams.id+'/edge/:id', {id:'@id'}, {
		'update': {method: 'PUT'}
	});
})
.factory('Graph', function($resource, graphURL, $routeParams) {
	return $resource(graphURL+'/:id', {id:'@id'});
});

	
