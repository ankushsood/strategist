(function() {
'use strict';


	angular.module('faculty')
	.filter('split', function() {
        return function(input, splitChar, splitIndex) {
            // do some bounds checking here to ensure it has that index
            return input.split(splitChar)[splitIndex];
        }
    })
	.controller('StandardListController', StandardListController);
	StandardListController.$inject=['StandardListService']
	
	function StandardListController(StandardListService){
		var _this = this;
		var standards = StandardListService.getStandardList({facultyId :'f374e8aa444785eb0144478613e30002'}, function(subjects) {
				return standards;
			});
			
		standards.$promise.then(function (result){
		
		
				
				_this.standardList = result;
		});
		
		_this.getSubjectIndex = function(num){
			return new Array(num);
		}
	}
})();