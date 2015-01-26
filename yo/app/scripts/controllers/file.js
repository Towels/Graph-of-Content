'use strict';

/**
 * @ngdoc function
 * @name graphOfContentApp.controller:FileCtrl
 * @description
 * # FileCtrl
 * Controller of the graphOfContentApp
 * Controls Tab Navigation Bar Highlighting
 */
angular.module('graphOfContentApp')
  .controller('FileCtrl', function($scope, File, site, $routeParams){
	  File.get({id: $routeParams.id}, function(data) {
			$scope.file = data;
			site.updateSite('File > ' + $scope.file.title);
	  });
  })
  .controller('AllFilesCtrl', function($scope, File, site, $location){
	  File.query(function(data){
		  $scope.files = data;
	  });
	  site.updateSite('Your files');
	  $scope.route = function(id){
			$location.path('/file/' + id);
	  };
  })
  .controller('UploadFileCtrl', function(){
	  
  })
  .controller('EditFileCtrl', function(){
	  
  })
.factory('File', function($resource, fileURL) {
	return $resource(fileURL + '/:id', null, {
		'update': {method: 'PUT'}
	});
});