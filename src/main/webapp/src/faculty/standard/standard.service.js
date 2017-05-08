(function() {
"use strict";

	angular.module('faculty')
	.service('StandardListService', StandardListService)
	.service('SubjectBookService', SubjectBookService)
	.service('BookChapterService', BookChapterService);
	
	StandardListService.$inject=['$resource']
	SubjectBookService.$inject=['$resource']
	BookChapterService.$inject=['$resource']
	
	function StandardListService($resource) {
		var service = this;
		return $resource('/rest/standard/getStandardListForFaculty/:facultyId', {facultyId:'@facultyId'},
		{
			getStandardList: {
				method: 'GET',
				isArray : true
			}
		});
	}

	function SubjectBookService($resource){
		var service = this;
		return $resource('/rest/book/:subjectId', {subjectId:'@subjectId'},
		{
			getSubjectBooks: {
				method: 'GET',
				isArray : true
			},
			addSubjectBook:{
				method:'POST',
				cache: false,
				isArray:true
			}
		});
	}

	function BookChapterService($resource){
		var service = this;
		return $resource('/rest/chapter/:bookId', {bookId:'@bookId'},
		{
			getBookChapters: {
				method: 'GET',
				isArray : true
			},
			addBookChapter:{
				method:'POST',
				cache: false,
				isArray:true
			},
			updateBookChapter:{
				method:'PUT',
				cache: false,
				isArray:true
			},
		});
	}	
})();
