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
  .controller('UploadFileCtrl', function($scope, fileURL, File, $upload){
	  $scope.submit = function(){
		  var details = new File({
			  title: $scope.title,
			  description: $scope.description,
			  fileType: $scope.fileType
		  });
		  console.log($scope.file);
		  details.$save(function(response){
			  console.log(response.id);
			  var fileReader = new FileReader();
    fileReader.readAsArrayBuffer($scope.file[0]);
    fileReader.onload = function(e) {
        $upload.http({
            url: fileURL + '/' + response.id,
            headers: {'Content-Type': 'application/octet-stream'},
            data: e.target.result
        }).then(function(response) {
            //success;
        }, null, function(evt) {
            $scope.progress[index] = parseInt(100.0 * evt.loaded / evt.total);
        });
    };

		  });
	  };
	  
  })
  .controller('EditFileCtrl', function(){
	  
  })
.factory('File', function($resource, fileURL) {
	return $resource(fileURL + '/:id', null, {
		'update': {method: 'PUT'}
	});
});