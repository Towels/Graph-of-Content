'use strict';
//TODO documentation
angular.module('graphOfContentApp')
.factory('site', function ($window) {
    var fancy = function (someString){
    	if(someString.indexOf('>') >= 0){
    		var s1 = someString.substring(0, someString.indexOf('>'));
    		var s2 = someString.substring(someString.indexOf('>')+1, someString.length);
    		someString = s1 + '<i class="fa fa-angle-right"></i>' + s2;
    	}
    	return someString;
    };
    var site = {
        title: '',
        desc: ''
    };
    site.updateSite = function (title, desc) {
        site.title = fancy(title);
        site.desc = desc; //TODO unused
        $window.document.title = title;
    };
    return site;
})//TODO move into proper location
.factory('authSrv', function(){
    //replace with proper api calls and stuff
    var auth = {};
    auth.loggedIn = false;
    auth.user = {
        email: '',
        uuid: ''
    };
    auth.setEmail = function(email){
    	auth.user.email = email;
    };
    auth.setUuid = function(uuid)  {
    	auth.user.uuid = uuid;
    };
    auth.setLoggedIn = function(loggedIn) {
    	auth.loggedIn = loggedIn;
    };
    return auth;
})
.constant('rootURL', 'http://localhost:8080/angular/resources')
.constant('authURL', 'http://localhost:8080/angular/resources/auth')
.constant('graphURL', 'http://localhost:8080/angular/resources/goc')
.constant('userURL', 'http://localhost:8080/angular/resources/user')
.constant('fileURL', 'http://localhost:8080/angular/resources/file')
.constant('lectureURL', 'http://localhost:8080/angular/resources/lecture');