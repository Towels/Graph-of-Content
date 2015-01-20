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
    auth.loggedIn = true;
    auth.user = {
        email: 'oliverconzen@me.com'
    };
    auth.setEmail = function(email){
    	auth.user.email = email;
    };
    return auth;
})
.constant('rootURL', 'http://localhost:8080/resources')
.constant('authURL', 'http://localhost:8080/angular/resources/auth')
.constant('userURL', 'http://localhost:8080/resources/user')
.constant('fileURL', 'http://localhost:8080/resources/file')
.constant('lectureURL', 'http://localhost:8080/resources/lecture');