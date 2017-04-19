(function() {
"use strict";

	angular.module('faculty')
	.service('StandardListService', StandardListService)
	.service('SubjectBookService', SubjectBookService);
	
	StandardListService.$inject=['$resource']
	SubjectBookService.$inject=['$resource']
		
	
	function StandardListService($resource) {
		var service = this;
		return $resource('rest/standard/getStandardListForFaculty/:facultyId', {facultyId:'@facultyId'},
		{
			getStandardList: {
				method: 'GET',
				isArray : true
			}
		});
	}


	function SubjectBookService($resource){

		var service = this;
		return $resource('rest/book/getBooksForSubject/:subjectId', {subjectId:'@subjectId'},
		{
			getSubjectBooks: {
				method: 'GET',
				isArray : true
			}
		});
	
	
	}	
})();
