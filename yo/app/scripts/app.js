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
    'angular-md5'
  ])
  .config(function ($routeProvider, $locationProvider, $httpProvider) {
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
      .when('/lecture/id-:id', {
        templateUrl: 'views/lecture.html',
        controller: 'LectureCtrl'
      })
      .when('/lecture/new', {
        templateUrl: 'views/createLecture.html',
        controller: 'CreateLectureCtrl'
      })
      .when('/file', {
        templateUrl: 'views/file.html',
        controller: 'FileCtrl'
      })
      .when('/graph', {
        templateUrl: 'views/graph.html',
        controller: 'GraphCtrl'
      })
      .otherwise({
        redirectTo: '/404.html'
      });
    $httpProvider.interceptors.push('sessionTimeOutHttpInterceptor');
  });
