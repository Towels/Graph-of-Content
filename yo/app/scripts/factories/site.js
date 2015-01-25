'use strict';
//TODO documentation
angular.module('graphOfContentApp')
.factory('site', function ($window) {
    var fancy = function (someString){
    	if(someString.indexOf('>') >= 0){
    		var s1 = someString.substring(0, someString.indexOf('>'));
    		var s2 = fancy(someString.substring(someString.indexOf('>')+1, someString.length));
    		someString = s1 + '<i class="fa fa-angle-right"></i>' + s2;
    	}
    	return someString;
    };
    var site = {
        title: '',
        button: ''
    };
    site.updateSite = function (title) {
        site.title = fancy(title);
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
.constant('rootURL', 'http://192.168.178.41/resources')
.constant('authURL', 'http://192.168.178.41/resources/auth')
.constant('graphURL', 'http://192.168.178.41/resources/goc')
.constant('userURL', 'http://192.168.178.41/resources/user')
.constant('fileURL', 'http://192.168.178.41/resources/file')
.constant('lectureURL', 'http://192.168.178.41/resources/lecture');