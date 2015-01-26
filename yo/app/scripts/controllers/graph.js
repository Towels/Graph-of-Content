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
.controller('GraphCtrl', function($scope, graphRenderer, $routeParams, Graph, Node, Edge, File, $http, fileURL, $sce){
	$scope.editMode = false;
	
	var editorClickListener = function(e){
		if($scope.source != undefined && $scope.source != null && $scope.source != "") {
			$scope.target = e.data.node.id;
		}else {
			$scope.source = e.data.node.id;
		}	

	}
	
	$scope.isFileType = function(type) {
		return type == $scope.fileType;
	}
	var fileClickListener = function(e) {
		$http.get(fileURL+'/node/'+e.data.node.id)
			.success(function(data, status){
			if(status == 200){
				$scope.fileId = data.id;
				$scope.fileType = data.fileType;
				$scope.filePath = fileURL+"/"+$scope.fileId;
			}else {
				//No File for Node
			}
		});
	}
	graphRenderer.setClickNode(fileClickListener);
	
	$scope.toogleEditMode = function(){
		$scope.editMode = !$scope.editMode;
		if($scope.editMode) {
			graphRenderer.setClickNode(editorClickListener);
		} else {
			graphRenderer.setClickNode(fileClickListener);
		}
		$scope.update();
	}
	
	$scope.update = function(){
		Graph.get({id : $routeParams.id}, function(g){
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
	$scope.update();
	
	$scope.$watch(function(){
		return $scope.filePath;
	}, function () {
	        var clone = element
	                      .clone()
	                      .attr('src', attrs.embedSrc);
	        current.replaceWith(clone);
	        current = clone;
	      });
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

	
