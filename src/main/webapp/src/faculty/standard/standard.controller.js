(function() {
'use strict';

	angular.module('faculty')
	.filter('split', function() {
        return function(input, splitChar, splitIndex) {
            return input.split(splitChar)[splitIndex];
        }
    }).filter('decode', function() {
        return function(input) {
            return $('#questionTitle').html(input);
        }
    })
	.controller('StandardListController', StandardListController)
	.controller('SubjectBookController', SubjectBookController)
	.controller('AddBookController', AddBookController)
	.controller('AddChapterController', AddChapterController)
	.controller('SubjectBookDetailsController', SubjectBookDetailsController)
	.controller('AddSectionController', AddSectionController)
	.controller('AddQuestionController', AddQuestionController);
	
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
				var addBook = {};
				addBook.bookTitle = result.title;
				addBook.bookSummary = result.summary;
				var saveNewBook = SubjectBookService.addSubjectBook({subjectId : subjectId}, addBook);
				saveNewBook.$promise.then(function (result){
					SubjectBookController.subjectBooks = result;
					$(".modal-backdrop").remove();
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
	

	SubjectBookDetailsController.$inject=['$stateParams', 'BookChapterService', '$location', 'ModalService']
	function SubjectBookDetailsController($stateParams, BookChapterService, $location, ModalService){
		var _this = this;
		
		_this.selectedBookTitle = $stateParams.selectedBookTitle;
		_this.selectedBookId = $stateParams.selectedBookId;
		_this.selectedSubjectId = $stateParams.selectedSubjectId;
		_this.showHideAccordian = accordianHide;
		
		_this.ModalService = ModalService;
		_this.ShowAddChapterModal = ShowAddChapterModal;
		_this.BookChapterService = BookChapterService;
		_this.data = {};
		_this.sectionCounter = {};
		_this.chapterCounter = {};
		var chapters = BookChapterService.getBookChapters({bookId : _this.selectedBookId}, function(books) {
			for(var i=0;i< chapters.length;i++){
				_this.data[chapters[i].chapterId] = [];
				_this.sectionCounter[chapters[i].chapterId] = 0;
				_this.chapterCounter[chapters[i].chapterId]= (i + 1);
			}
			return chapters;
		});
			
		chapters.$promise.then(function (result){
				_this.bookChapters = result;
			});
		
		_this.remove = function (scope) {
			scope.remove();
		};

		_this.toggle = function (scope) {
			scope.toggle();
		};

		_this.moveLastToTheBeginning = function () {
			var a = _this.data.pop();
			_this.data.splice(0, 0, a);
		};
		_this.sectionCounter = {};
		
		_this.newItem = function(chapter){
			ShowAddSectionModal(BookChapterService, ModalService, _this.selectedBookId, _this, chapter, null);
		}
		
		_this.newSubItem = function (scope, chapter) {
			ShowAddSectionModal(BookChapterService, ModalService, _this.selectedBookId, _this, chapter, scope);
		};

		_this.newQuestion = function(scope, chapter){
			
			ShowAddQuestionModal(BookChapterService, ModalService, _this.selectedBookId, _this, chapter, scope);
		}
		
		_this.collapseAll = function () {
			_this.$broadcast('angular-ui-tree:collapse-all');
		};

		_this.expandAll = function () {
			_this.$broadcast('angular-ui-tree:expand-all');
		};
		
		
	}
	
	function ShowAddChapterModal(BookChapterService, ModalService, bookId, SubjectBookDetailsController, chapter){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/addChapterTemplate.html',
			controller: "AddChapterController",
			controllerAs: 'addChapterController',
			inputs: {chapterData: chapter}
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				
				var addChapter = {};
				
				addChapter.chapterTitle = result.title;
				addChapter.chapterSummary = result.summary;
				
				if(result.chapterId != undefined){
					addChapter.id = result.chapterId;
					var saveNewChapter = BookChapterService.updateBookChapter({bookId : bookId}, addChapter);
					saveNewChapter.$promise.then(function (chapters){
						SubjectBookDetailsController.bookChapters = chapters;
						for(var i=0;i<chapters.length;i++){
							SubjectBookDetailsController.data[chapters[i].chapterId] = [];
							SubjectBookDetailsController.sectionCounter[chapters[i].chapterId] = 0;
							SubjectBookDetailsController.chapterCounter[chapters[i].chapterId] = (i + 1);

						}
					});
				}else{
					var saveNewChapter = BookChapterService.addBookChapter({bookId : bookId}, addChapter);
					saveNewChapter.$promise.then(function (chapters){
						SubjectBookDetailsController.bookChapters = chapters;
						for(var i=0;i<chapters.length;i++){
							SubjectBookDetailsController.data[chapters[i].chapterId] = [];
							SubjectBookDetailsController.sectionCounter[chapters[i].chapterId] = 0;
							SubjectBookDetailsController.chapterCounter[chapters[i].chapterId] = (i + 1);

							
						}
					});
				}

				
			});
		});

	}

	AddChapterController.$inject=['close', 'chapterData']
	function AddChapterController(close, chapterData){
		var modal = this;
		modal.bookDetails = {};		
		if(chapterData.title != undefined)
		{
			modal.bookDetails.title = chapterData.title;
		}
		if(chapterData.summary != undefined)
		{
			modal.bookDetails.summary = chapterData.summary;
		}
		modal.close = function(isValid, result) {
			if(chapterData.chapterId != undefined)
			{
				result.chapterId = chapterData.chapterId;
			}			
			if(!isValid)
				return false;
			close(result, 500);
		};
	}
	
	
	
	function ShowAddSectionModal(BookChapterService, ModalService, bookId, SubjectBookDetailsController, chapter, scope){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/addSectionTemplate.html',
			controller: "AddSectionController",
			controllerAs: 'addSectionController',
			inputs: {chapterData: chapter}
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				
				var addSection = {};
				
				addSection.sectionTitle = result.title;
				
				if(result.chapterId != undefined){
					addSection.id = result.chapterId;
					// var saveNewChapter = BookChapterService.updateBookChapter({bookId : bookId}, addSection);
					// saveNewChapter.$promise.then(function (chapters){
						// SubjectBookDetailsController.bookChapters = chapters;
						// $(".modal-backdrop").remove();
					// });
					
					if(scope == null){
						SubjectBookDetailsController.sectionCounter[chapter.chapterId] = SubjectBookDetailsController.sectionCounter[chapter.chapterId] + 1;
						SubjectBookDetailsController.data[chapter.chapterId].push({
									id: SubjectBookDetailsController.sectionCounter[chapter.chapterId] * 10 + SubjectBookDetailsController.data[chapter.chapterId].length,
									title: result.title,
									sectionNumber: SubjectBookDetailsController.chapterCounter[chapter.chapterId] + '.' + SubjectBookDetailsController.sectionCounter[chapter.chapterId],
									isQuestion: false,
									nodes: []
						});
					}else{
						var nodeData = scope.$modelValue;
							nodeData.nodes.push({
								id: SubjectBookDetailsController.sectionCounter[chapter.chapterId] * 10 + nodeData.nodes.length,
								title: result.title ,
								sectionNumber: nodeData.sectionNumber + '.' + (nodeData.nodes.length + 1),
								isQuestion: false,
								nodes: []
							});
					}
				}

			});
		});

	}

	AddSectionController.$inject=['close', 'chapterData']
	function AddSectionController(close, chapterData){
		var modal = this;
		modal.sectionDetails = {};		

		modal.close = function(isValid, result) {
			if(chapterData.chapterId != undefined)
			{
				result.chapterId = chapterData.chapterId;
			}			
			if(!isValid)
				return false;
			close(result, 500);
		};
	}
	
	function ShowAddQuestionModal(BookChapterService, ModalService, bookId, SubjectBookDetailsController, chapter, scope){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/addQuestionTemplate.html',
			controller: "AddQuestionController",
			controllerAs: 'addQuestionController',
			inputs: {chapterData: chapter}
			
		}).then(function(modal) {
			modal.element.modal();
			modal.close.then(function(result) {
				
				var addQuestion = {};
				console.log(result);
				addQuestion.questionText = result.htmlContent;
				
				if(result.chapterId != undefined){
					addQuestion.id = result.chapterId;
					var nodeData = scope.$modelValue;
					if(nodeData.nodes.length == 0){
						nodeData.nodes.push({
							id: SubjectBookDetailsController.sectionCounter[chapter.chapterId] * 10 + nodeData.nodes.length,
							title: result.htmlContent,
							sectionNumber: nodeData.sectionNumber + '.' + (nodeData.nodes.length + 1),
							isQuestion: true,
							nodes: []
						});
							
					}
				}

			});
		});

	}

	AddQuestionController.$inject=['close', 'chapterData']
	function AddQuestionController(close, chapterData){
		var modal = this;
		modal.questionData = {};
		modal.close = function(isValid, result) {
		
		console.log(result);
			if(chapterData.chapterId != undefined)
			{
				result.chapterId = chapterData.chapterId;
			}
		
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