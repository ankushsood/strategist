(function() {
"use strict";

	angular.module('admin')
	.service('ListStudentService', ListStudentService)
	.service('GetStudentDetailsService', GetStudentDetailsService)
	.service('CreateStudentTimelineEventService', CreateStudentTimelineEventService);

	ListStudentService.$inject=['$resource']
	GetStudentDetailsService.$inject=['$resource']
	CreateStudentTimelineEventService.$inject=['$resource']

	function ListStudentService($resource) {
		return $resource('rest/students/', {},
			{
				listStu: {
					method: 'GET',
					isArray : true,
					headers : {'X-Access-Token' : 'asdf', 'Content-Type': 'application/json'}
				}
			}
		);
	}

	function GetStudentDetailsService($resource) {
		var service = this;
		return $resource('rest/students/student/:studentId', {studentId:'@studentId'},
		{
			studentDetails: {
				method: 'GET',
				isArray : false,
				headers : {'X-Access-Token' : 'asdf', 'Content-Type': 'application/json'}
			}
		});
	}
	
	function CreateStudentTimelineEventService($resource) {
		var service = this;
		return $resource('/rest/students/timeline/:studentId',{studentId:'@studentId'},
		{
			addStudentTimeline: {
				method: 'POST', 
				cache: false,
				isArray:false,
				params: {'token' : 'asdf'},
				headers:{'Content-Type':'application/json; charset=UTF-8'} 			
			},
			getStudentTimeline: {
				method: 'GET', 
				cache: false,
				isArray:true,
				headers:{'X-Access-Token' : 'asdf','Content-Type':'application/json; charset=UTF-8'} 			
			}
			
		});
	}
})();
