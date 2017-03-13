(function() {
"use strict";

angular.module('admin.student.services', ['ngResource'])
.factory('addStudentService', AddStudentService)
.factory('listStudentService', ListStudentService)


AddStudentService.$inject=['$resource']
ListStudentService.$inject=['$resource']

function ListStudentService($resource) {
	
	return $resource('rest/students/:action', {},
		{
			listStu: {
				method: 'POST',
				params: {'action' : 'students', 'token' : 'asdf'},
				headers : {'Content-Type': 'application/json'}
			}
		}
	);
}











})();
