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
  .config(function ($routeProvider, $locationProvider) {
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
        templateUrl: 'views/lecture.html',
        controller: 'LectureCtrl'
      })
      .when('/file', {
        templateUrl: 'views/file.html',
        controller: 'FileCtrl'
      })
      .otherwise({
        redirectTo: '/404.html'
      });
    $locationProvider.html5Mode(true);
  });
