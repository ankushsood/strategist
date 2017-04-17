(function() {
"use strict";

	angular.module('faculty')
	.service('StandardListService', StandardListService);
	
	
	StandardListService.$inject=['$resource']
	
	
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
	
})();
