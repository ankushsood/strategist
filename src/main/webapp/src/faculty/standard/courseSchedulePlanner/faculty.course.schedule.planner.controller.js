(function() {
'use strict';

	angular.module('faculty')
	.controller('StandardCourseSchedulePlanController', StandardCourseSchedulePlanController)
	.controller('CourseSchedulePlannerController', CourseSchedulePlannerController);
	
	StandardCourseSchedulePlanController.$inject=['$stateParams', '$location', 'SubjectBookService', 'ModalService', 'moment', 'alert', 'calendarConfig']
	function StandardCourseSchedulePlanController($stateParams, $location, SubjectBookService, ModalService, moment, alert, calendarConfig){
		var _this = this;
		
		
		if($stateParams.selectedSubjectId == undefined || $stateParams.selectedSubjectId == null){
			$location.url("/home/standards");
			return;
		}
		_this.selectedBookTitle = $stateParams.selectedBookTitle;
		_this.selectedBookId = $stateParams.selectedBookId;
		_this.selectedSubjectId = $stateParams.selectedSubjectId.split('~~')[1];
		_this.selectedSubjectTitle = $stateParams.selectedSubjectId.split('~~')[0];
		
		var books = SubjectBookService.getSubjectBooks({subjectId : _this.selectedSubjectId}, function(books) {
				return books;
			});
			
		books.$promise.then(function (result){
			_this.subjectBooks = result;
		});
		
		
		
		/*Calendar Code Start */
		
		//These variables MUST be set as a minimum for the calendar to work
		_this.calendarView = 'month';
		_this.viewDate = new Date();
		
		_this.events = [
		  /*{
			title: 'An event',
			color: calendarConfig.colorTypes.warning,
			startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
			endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
			draggable: true,
			resizable: true,
			actions: actions
		  }, {
			title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
			color: calendarConfig.colorTypes.info,
			startsAt: moment().subtract(1, 'day').toDate(),
			endsAt: moment().add(5, 'days').toDate(),
			draggable: true,
			resizable: true,
			actions: actions
		  }, {
			title: 'This is a really long event title that occurs on every year',
			color: calendarConfig.colorTypes.important,
			startsAt: moment().startOf('day').add(7, 'hours').toDate(),
			endsAt: moment().startOf('day').add(19, 'hours').toDate(),
			recursOn: 'year',
			draggable: true,
			resizable: true,
			actions: actions
		  }*/
		];

		_this.cellIsOpen = false;
		
		_this.addEvent = function(selectedDate) {
			/*var startDate = new Date(selectedDate.getTime())
			startDate.setHours(10,0,0,0);
			var endDate = new Date(selectedDate.getTime())
			endDate.setHours(10,30,0,0);
			_this.events.push({
			title: 'New event',
			startsAt: startDate,
			endsAt: endDate,
			color: calendarConfig.colorTypes.important,
			draggable: true,
			resizable: true
		  });*/
		  
		  _this.cellIsOpen = true;
		};

		_this.eventClicked = function(event) {
			ShowCourseScheduleModal(ModalService, _this.subjectBooks, null, 'Clicked', event);
		};

		_this.eventEdited = function(event) {
		  //alert.show('Edited', event);
		};

		_this.eventDeleted = function(event) {
		  //alert.show('Deleted', event);
		};

		_this.eventTimesChanged = function(event) {
		  //alert.show('Dropped or resized', event);
		};

		_this.toggle = function($event, field, event) {
		  $event.preventDefault();
		  $event.stopPropagation();
		  event[field] = !event[field];
		};

		_this.timespanClicked = function(date, cell) {
			//_this.toggle();
					_this.selectedDate = undefined;
		  if (_this.calendarView === 'month') {
			_this.selectedDate = date;
			if ((_this.cellIsOpen && moment(date).startOf('day').isSame(moment(_this.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
			  _this.cellIsOpen = false;
			  
			} else {
			  _this.cellIsOpen = true;
			  _this.viewDate = date;
			}
		  } else if (_this.calendarView === 'year') {
			if ((_this.cellIsOpen && moment(date).startOf('month').isSame(moment(_this.viewDate).startOf('month'))) /*|| cell.events.length === 0*/) {
			  _this.cellIsOpen = false;
			} else {
			  _this.cellIsOpen = true;
			  _this.viewDate = date;
			}
		  }
		  if(_this.calendarView === 'month' && cell.events.length === 0 && moment(date).startOf('month').isSame(moment(_this.viewDate).startOf('month'))){
			ShowCourseScheduleModal(ModalService, _this.subjectBooks, null, 'Clicked', null, _this.selectedDate);
			//_this.addEvent(date);
			  
		  }

		};
		
		
		/*Calendar Code End */

	}
	
	
	function ShowCourseScheduleModal(ModalService, bookList, chapterList, action, event, selectedDate){
		ModalService.showModal({
			templateUrl: 'src/faculty/standard/courseSchedulePlanner/subjectPlanModalContent.html',
			controller: "CourseSchedulePlannerController",
			controllerAs: 'vm',
			inputs: {bookList: bookList, chapterList: chapterList, action: action, event: event, selectedDate: selectedDate}
			
		}).then(function(modal) {
			modal.element.modal();
			modal.element.on('hidden.bs.modal', function (e) {
				modal.element.remove();
			});
			modal.close.then(function(result) {
				console.log('aaaaaaaaaaadffdf22222222222');
				
				//$(".modal-backdrop").remove();
				//$("body").removeClass("modal-open");
				//ModalService.closeModals();
			});
		});
	}
	
	CourseSchedulePlannerController.$inject=['close', 'bookList', 'chapterList', 'action', 'event', 'selectedDate', 'calendarConfig', 'BookChapterService']
	function CourseSchedulePlannerController(close, bookList, chapterList, action, event, selectedDate, calendarConfig, BookChapterService){
		
		
		
		if(event === undefined || event === null){
			
			event = {
			title: '',
			color: calendarConfig.colorTypes.info,
			startsAt: selectedDate,
			endsAt: selectedDate,
			draggable: false,
			resizable: true,
			actions: [/*{
						  label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
						  onClick: function(args) {
							//alert.show('Edited', args.calendarEvent);
						  }
						}, {
						  label: '<i class=\'glyphicon glyphicon-remove\'></i>',
						  onClick: function(args) {
							//alert.show('Deleted', args.calendarEvent);
						  }
					}*/]
		  }
		}
		
		var modal = this;
		modal.bookList = bookList;		
		modal.action = action;
		modal.event = event;
		modal.selectedDate = selectedDate;
		modal.selectedBookIds = [];
		modal.selectedBookChapters = [];
		modal.selectedBook = function(bookId){
			var elementIndex = modal.selectedBookIds.indexOf(bookId);
			if(elementIndex == -1){
				modal.selectedBookIds.push(bookId);
				var bookChapters = BookChapterService.getBookChapters({bookId : bookId}, function(bookChapters) {
					return bookChapters;
				});
				bookChapters.$promise.then(function (result){
					modal.selectedBookChapters.push({bookId: bookId, chapterList : result});
				});
		
			}else{
				modal.selectedBookIds.splice( elementIndex, 1 );
			}
			console.log(modal.selectedBookIds)
		}	

		modal.close = function(isValid, result) {
		
		//	if(!isValid)
		//		return false;
			$(".modal").remove;
			$(".modal-backdrop").remove();
			$("body").removeClass("modal-open");
			close(result);
		};
	
	}
})();