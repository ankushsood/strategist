(function() {
'use strict';

	angular.module('faculty')
	.filter('split', function() {
        return function(input, splitChar, splitIndex) {
            return input.split(splitChar)[splitIndex];
        }
    })
	.controller('StandardListController', StandardListController)
	.controller('SubjectBookController', SubjectBookController)
	.controller('AddBookController', AddBookController)
	.controller('AddChapterController', AddChapterController)
	.controller('SubjectBookDetailsController', SubjectBookDetailsController);
	
	StandardListController.$inject=['StandardListService','ModalService', '$location']
	
	function StandardListController(StandardListService, ModalService, $location){
		var _this = this;
		var standards = StandardListService.getStandardList({facultyId :'f374e8aa444785eb0144478613e30002'}, function(subjects) {
				return standards;
			});
		standards.$promise.then(function (result){
				_this.standardList = result;
		});
	}
	
	SubjectBookController.$inject=['$stateParams', 'SubjectBookService', '$location', 'ModalService']
	function SubjectBookController($stateParams, SubjectBookService, $location, ModalService){
		var _this = this;
		_this.subjectData = {} 
		if($stateParams.selectedSubjectId == undefined || $stateParams.selectedSubjectId == null){
			$location.url("/home/standards");
			return;
			
		}
		_this.subjectTitle = $stateParams.selectedSubjectId.split('~~')[0];
		_this.subjectId = $stateParams.selectedSubjectId.split('~~')[1];
		var books = SubjectBookService.getSubjectBooks({subjectId : _this.subjectId}, function(books) {
				return books;
			});
			
		books.$promise.then(function (result){
				_this.subjectBooks = result;
				console.log(_this.subjectBooks);
		});
		
		_this.showAddBookModal = ShowAddBookModal;
		_this.modalViewService = ModalService;
		_this.subjectBookService = SubjectBookService;
	}
	
	function ShowAddBookModal(selectedSubject, SubjectBookService, ModalService, subjectId, SubjectBookController){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/addBookTemplate.html',
			controller: "AddBookController",
			controllerAs: 'addBookController',
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				$(".modal-backdrop").remove();
				var addBook = {};
				addBook.bookTitle = result.title;
				addBook.bookSummary = result.summary;
				var saveNewBook = SubjectBookService.addSubjectBook({subjectId : subjectId}, addBook);
				saveNewBook.$promise.then(function (result){
					SubjectBookController.subjectBooks = result;
				});
				
			});
		});

	}
	
	AddBookController.$inject=['close']
	function AddBookController(close){
		var _this = this;
		_this.bookDetails = {};
		_this.close = function(isValid, result) {
			if(!isValid)
				return false;
			close(result, 500);
		};
	}
	

	SubjectBookDetailsController.$inject=['$stateParams', 'SubjectBookService', '$location', 'ModalService']
	function SubjectBookDetailsController($stateParams, SubjectBookService, $location, ModalService){
		var _this = this;
		
		_this.selectedBookTitle = $stateParams.selectedBookTitle;
		_this.selectedBookId = $stateParams.selectedBookId;
		_this.selectedSubjectId = $stateParams.selectedSubjectId;
		_this.showHideAccordian = accordianHide;
		
		_this.ModalService = ModalService;
		_this.ShowAddChapterModal = ShowAddChapterModal;
	}
	
	function ShowAddChapterModal(subjectId, ModalService, bookId, bookTitle){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/addChapterTemplate.html',
			controller: "AddChapterController",
			controllerAs: 'addChapterController',
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				$(".modal-backdrop").remove();
				var addBook = {};
				addBook.bookTitle = result.title;
				addBook.bookSummary = result.summary;
				var saveNewBook = SubjectBookService.addSubjectBook({subjectId : subjectId}, addBook);
				saveNewBook.$promise.then(function (result){
					SubjectBookController.subjectBooks = result;
				});
				
			});
		});

	}

	AddChapterController.$inject=['close']
	function AddChapterController(close){
		var _this = this;
		_this.bookDetails = {};
		_this.close = function(isValid, result) {
			if(!isValid)
				return false;
			close(result, 500);
		};
	}
	var accordianHide = function(total, showAccordian){
			for(var i=0;i<total;i++){
				if($('.panel div#' + i).is(":visible")){
					$('.panel div#' + i).slideUp();
				}
			}
			if(!$('.panel div#' + showAccordian).is(":visible")){
					$('.panel div#' + showAccordian).slideDown();
			}	
	}
	
})();	