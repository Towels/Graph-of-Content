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
        'title': '',
        'desc': ''
    };
    site.updateSite = function (title, desc) {
        site.title = fancy(title);
        site.desc = desc;
        $window.document.title = title;
    };
    return site;
}) //TODO move this into own file
.factory('api', function() {
    return {
        root: 'http://localhost:8080/resources',
        user: '/user',
        lecture: '/lecture',
        file: '/file'
    };
});