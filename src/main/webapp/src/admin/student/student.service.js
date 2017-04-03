(function() {
"use strict";

angular.module('admin')
.service('ListStudentService', ListStudentService)
.service('GetStudentDetailsService', GetStudentDetailsService);

ListStudentService.$inject=['$resource']
GetStudentDetailsService.$inject=['$resource', '$http', '$q']

function ListStudentService($resource) {
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

	function GetStudentDetailsService($resource, $http, $q) {
		var service = this;
		
		
		
		return $resource('rest/students/student/:studentId', {studentId:'@studentId'},
		{
			studentDetails: {
				method: 'GET',
				params: {'token' : 'asdf'},
				isArray : false,
				headers : {'Content-Type': 'application/json'}
			}
		});
		
		
		
		/** Retrieves an access token using a username and password 
		service.getStudentDetails = function(studentId) {
		
		console.log('ssssssssssssssssss');
		var params = {
		  'token': 'testtt'
		};
			var def = $q.defer();

            $http.get("rest/students/student/" + studentId + "?token=234")
                .success(function(data) {
                    service.students = data;
                    def.resolve(data);
                })
                .error(function() {
                    def.reject("Failed to get studentDetails");
                });
            return def.promise;
		
		
		
		};
	
		return null;*/
	}



})();
