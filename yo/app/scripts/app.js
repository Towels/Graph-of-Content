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
    'ngMaterial'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/user', {
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
        redirectTo: '/'
      });
  });
