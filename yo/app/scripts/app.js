'use strict';

/**
 * @ngdoc overview
 * @name graphOfContentApp
 * @description
 * # graphOfContentApp
 *
 * Main module of the application.
 */
angular
  .module('graphOfContentApp', [
    'ngAnimate',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngMaterial',
    'angular-md5',
    'angularFileUpload'
  ])
  .config(function ($routeProvider, $locationProvider, $httpProvider, $sceProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/user/:name', {
        templateUrl: 'views/user.html',
        controller: 'UserCtrl'
      })
      .when('/lecture', {
        templateUrl: 'views/lectures.html',
        controller: 'AllLecturesCtrl'
      })
      .when('/lecture/new', {
        templateUrl: 'views/createLecture.html',
        controller: 'CreateLectureCtrl'
      })
      .when('/lecture/id-:id', {
        templateUrl: 'views/lecture.html',
        controller: 'LectureCtrl'
      })      
      .when('/lecture/id-:id/edit', {
        templateUrl: 'views/editLecture.html',
        controller: 'EditLectureCtrl'
      })
      .when('/lecture/id-:id/graph', {
        templateUrl: 'views/graph.html',
        controller: 'GraphCtrl'
      })
      .when('/file', {
        templateUrl: 'views/files.html',
        controller: 'AllFilesCtrl'
      })
      .when('/file/new', {
    	templateUrl: 'views/uploadFile.html',
    	controller: 'UploadFileCtrl'
      })
      .when('/graph', {
        templateUrl: 'views/graph.html',
        controller: 'GraphCtrl'
      })
      .when('/editor', {
        templateUrl: 'views/graphEditor.html',
        controller: 'EditorCtrl'
      })
      .otherwise({
        redirectTo: '/404.html'
      });
    $httpProvider.interceptors.push('sessionTimeOutHttpInterceptor');
    $sceProvider.enabled(false);
  });
