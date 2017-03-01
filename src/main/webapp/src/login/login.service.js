(function() {
"use strict";

angular.module('login.services', ['ngResource'])
.factory('UserService', UserService);

UserService.$inject=['$resource']

function UserService($resource) {
	
	return $resource('rest/user/:action', {},
			{
				authenticate: {
					method: 'POST',
					params: {'action' : 'authenticate'},
					headers : {'Content-Type': 'application/x-www-form-urlencoded'}
				}
			}
		);
}











})();
