(function() {
'use strict';


	angular.module('faculty').controller('StandardListController', StandardListController);
	StandardListController.$inject=['StandardListService']
	
	function StandardListController(StandardListService){
		var _this = this;
		var standards = StandardListService.getStandardList({facultyId :'f374e8aa444785eb0144478613e30002'}, function(subjects) {
				return standards;
			});
			
		standards.$promise.then(function (result){
				_this.standardList = result;
		});
	}
	
	
		
})();