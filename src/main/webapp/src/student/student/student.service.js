(function() {
"use strict";

	angular.module('admin')
	.service('ListStudentService', ListStudentService)
	.service('GetStudentDetailsService', GetStudentDetailsService)
	.service('CreateStudentTimelineEventService', CreateStudentTimelineEventService)
	.service('GetAllSubjectService', GetAllSubjectService)
	.service('GetAllStandardService', GetAllStandardService)
	.service("SaveStudentService", SaveStudentService);
	ListStudentService.$inject=['$resource']
	GetStudentDetailsService.$inject=['$resource']
	CreateStudentTimelineEventService.$inject=['$resource']
	GetAllSubjectService.$inject=['$resource']
	GetAllStandardService.$inject=['$resource']
	SaveStudentService.$inject=['$http', '$q']
	
	
	function SaveStudentService($http, $q) {

		return ({
		  upload: upload
		});

		function upload(file, data) {
		
			console.log(data);
			var upl = $http({
			method: 'POST',
			url: 'http://jsonplaceholder.typicode.com/posts', // /api/upload
			headers: {
			  'Content-Type': 'multipart/form-data'
			},
			data: {
			  upload: file,
			  text : 'stuName'
			},
			transformRequest: function(data, headersGetter) {
			  var formData = new FormData();
			  angular.forEach(data, function(value, key) {
				formData.append(key, value);
			  });

			  var headers = headersGetter();
			  delete headers['Content-Type'];

			  return formData;
			}
		  });
		  return upl.then(handleSuccess, handleError);

		} // End upload function

		// ---
		// PRIVATE METHODS.
		// ---
  
		function handleError(response, data) {
		  if (!angular.isObject(response.data) ||!response.data.message) {
			return ($q.reject("An unknown error occurred."));
		  }

		  return ($q.reject(response.data.message));
		}

		function handleSuccess(response) {
		  return (response);
		}

  }
	
	
	
	
	
	
	
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
			},
			addStudent: {
				method: 'POST', 
				cache: false,
				params: {'token' : 'asdf'},
				headers:{'Content-Type':'application/json; charset=UTF-8'} 			
			},
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
