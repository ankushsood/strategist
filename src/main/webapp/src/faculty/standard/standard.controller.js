(function() {
'use strict';

	angular.module('faculty')
	.filter('split', function() {
        return function(input, splitChar, splitIndex) {
            return input.split(splitChar)[splitIndex];
        }
    })
	.controller('StandardListController', StandardListController);
	
	StandardListController.$inject=['StandardListService','ModalService', '$location']
	
	function StandardListController(StandardListService, ModalService, $location){
		var _this = this;
		/*f374e8aa444785eb0144478613e80005*/
		/*f374e8aa444785eb0144478613e30002*/
		var standards = StandardListService.getStandardList({facultyId :'f374e8aa444785eb0144478613e80005'}, function(subjects) {
				return standards;
			});
		standards.$promise.then(function (result){
				_this.standardList = result;
		});
		
		_this.showUpdateSubjectModal = function(){
			ModalService.showModal({
			templateUrl: 'src/faculty/standard/updateCourse/subjectListTemplate.html',
			controller: "AddChapterController",
			controllerAs: 'addChapterController',
			inputs: {chapterData: chapter}
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				
				

				
			});
		});

		}
	}
	
	
})();