(function() {
'use strict';


	angular.module('faculty')
	.filter('split', function() {
        return function(input, splitChar, splitIndex) {
            // do some bounds checking here to ensure it has that index
            return input.split(splitChar)[splitIndex];
        }
    })
	.controller('StandardListController', StandardListController)
	.controller('SubjectBookController', SubjectBookController);
	
	
	StandardListController.$inject=['StandardListService','ModalService', '$location']
	
	function StandardListController(StandardListService, ModalService, $location){
		var _this = this;
		var standards = StandardListService.getStandardList({facultyId :'f374e8aa444785eb0144478613e30002'}, function(subjects) {
				return standards;
			});
			
		standards.$promise.then(function (result){
				_this.standardList = result;
		});
		
		_this.modalService = ModalService;
		_this.showSubjectModal = ShowSubjectModal;
	}
	
	
	function ShowSubjectModal(selectedSubject, ModalService){
		
		console.log(selectedSubject);
		
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/subjectBookTemplate.html',
			controller: "SubjectBookController",
			controllerAs: 'subBookController'
		}).then(function(modal) {
			modal.element.modal();
			
			console.log('aaaaaaaaaaaaaaaaaaaa');
			
			
			modal.close.then(function(result) {
				$(".modal-backdrop").remove();
/*				var studentTimeline = {};
				studentTimeline.studentId = $stateParams.studentId;
				studentTimeline.title = result.title;
				studentTimeline.description = result.desc;
				studentTimeline.badgeString = result.selectedBadge;
				var postTimeline = CreateStudentTimelineEventService.addStudentTimeline({studentId : null},studentTimeline);
				postTimeline.$promise.then(function (result){
					_this.newTimelineEvent = result;
				});
*/
			});
		});

	}
	
	SubjectBookController.$inject=['close', 'SubjectBookService']
	function SubjectBookController(close, SubjectBookService){
		var _this = this;
		_this.subjectData = {} 
		
			console.log('bbbbbbbbbbbbbbbbbb');
			
		
		var books = SubjectBookService.getSubjectBooks({subjectId :'f374e8aa44478c040144478c1c4c000a'}, function(books) {
				return books;
			});
			
		books.$promise.then(function (result){
				_this.subjectBooks = result;
		console.log(_this.subjectBooks);

				});
		
		
		_this.close = function(isValid, result) {
			if(!isValid)return false;
			close(result, 500);
		};
	}
})();	