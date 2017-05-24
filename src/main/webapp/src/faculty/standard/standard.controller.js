(function() {
'use strict';

	angular.module('faculty')
	.filter('split', function() {
        return function(input, splitChar, splitIndex) {
            return input.split(splitChar)[splitIndex];
        }
    })
	.controller('StandardListController', StandardListController)
	.controller('SelectSubjectController', SelectSubjectController);

	StandardListController.$inject=['StandardListService','ModalService', '$location']
	function StandardListController(StandardListService, ModalService, $location){
		var _this = this;
		/*f374e8aa444785eb0144478613e80005*/
		/*f374e8aa444785eb0144478613e30002*/
		var standards = StandardListService.getStandardList({facultyId :'f374e8aa444785eb0144478613e30002'}, function(subjects) {
				return standards;
			});
		standards.$promise.then(function (result){
				_this.standardList = result;
		});
		
		_this.ShowUpdateSubjectModal = ShowUpdateSubjectModal;
		_this.ModalService = ModalService;
	}

	function ShowUpdateSubjectModal(ModalService, subjectList){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/updateCourse/subjectListTemplate.html',
			controller: "SelectSubjectController",
			controllerAs: 'selectSubjectController',
			inputs: {subjectList: subjectList}
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				$(".modal-backdrop").remove();
				$("body").removeClass("modal-open");
			});
		});
	}

	SelectSubjectController.$inject=['close', 'subjectList']
	function SelectSubjectController(close, subjectList){
		var modal = this;
		modal.subjectList = subjectList;		
		
		modal.close = function(isValid, result) {
			if(!isValid)
				return false;
			close(result);
		};
	}
	
})();