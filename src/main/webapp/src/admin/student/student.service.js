(function() {
"use strict";

	angular.module('admin')
	.service('ListStudentService', ListStudentService)
	.service('GetStudentDetailsService', GetStudentDetailsService)
	.service('CreateStudentTimelineEventService', CreateStudentTimelineEventService)
	.service('GetAllSubjectService', GetAllSubjectService)
	.service('GetAllStandardService', GetAllStandardService);
	
	ListStudentService.$inject=['$resource']
	GetStudentDetailsService.$inject=['$resource']
	CreateStudentTimelineEventService.$inject=['$resource']
	GetAllSubjectService.$inject=['$resource']
	GetAllStandardService.$inject=['$resource']
	
	function GetAllSubjectService($resource){
		var service = this;
		
		return $resource('rest/subject/:subjectId',{subjectId:'@subjectId'},
			{
				listSubject: {
					method: 'GET',
					isArray : true,
					headers : {'X-Access-Token' : 'asdf', 'Content-Type': 'application/json'}
				}
			}
		);
	}
	
	function GetAllStandardService($resource){
		return $resource('rest/standard/', {},
			{
				listStandard: {
					method: 'GET',
					isArray : true,
					headers : {'X-Access-Token' : 'asdf', 'Content-Type': 'application/json'}
				}
			}
		);
	}
	
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
