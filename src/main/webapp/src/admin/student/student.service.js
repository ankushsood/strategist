(function() {
"use strict";

angular.module('admin')
.factory('ListStudentService', ListStudentService);


ListStudentService.$inject=['$resource']
console.log('111111111111111');
function ListStudentService($resource) {
	console.log('aaaaaaaaaaaaaaaaaaa');
	return $resource('rest/students/', {},
		{
			listStu: {
				method: 'GET',
				params: {'token' : 'asdf'},
				isArray : true,
				headers : {'Content-Type': 'application/json'}
			}
		}
	);
	
	
}











})();
