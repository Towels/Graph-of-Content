'use strict';
// TODO documentation
angular.module('graphOfContentApp').factory('sessionTimeOutHttpInterceptor',
		function($location, $q, authSrv) {
			return {
				'responseError': function(response) {
					if (response.status === 401) {
						$location.path("/");
						authSrv.setLoggedIn(false);
						authSrv.setEmail("");
						authSrv.setUuid("");
					}
					return $q.reject(response);
				}
			}
		});
