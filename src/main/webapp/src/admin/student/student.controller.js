(function() {
'use strict';
	var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
	angular.module('admin').controller('StudentListController', StudentListController );
	angular.module('admin').controller('ViewStudentDetailsController', ViewStudentDetailsController );
	angular.module('admin').controller('AddStudentBadge', AddStudentBadge );
	
	StudentListController.$inject=['ListStudentService']

	function StudentListController(ListStudentService){
		var _this = this;
		var students = ListStudentService.listStu(function(students) {
			return students;
		});
		students.$promise.then(function (result) {
			_this.studentList = result;
		});
	}
	
	ViewStudentDetailsController.$inject=['$stateParams', 'GetStudentDetailsService' ,'ModalService', '$location', 'CreateStudentTimelineEventService']
	function ViewStudentDetailsController($stateParams, GetStudentDetailsService, ModalService, $location, CreateStudentTimelineEventService){
		var _this = this;
		
		if($stateParams.studentId == undefined || $stateParams.studentId == null){
			$location.url('/admin/home/students');
		}
		var studentDetails = GetStudentDetailsService.studentDetails
		({studentId : $stateParams.studentId},function(students) {
			return students;
		});
		var studentTimeline = CreateStudentTimelineEventService.getStudentTimeline({studentId : $stateParams.studentId});
			
		studentTimeline.$promise.then(function (result){
			_this.studentTimelineList = result;
				
		});
		studentDetails.$promise.then(function (result) {
			_this.student = result;
			
		});
		
		_this.getDate = function(dateString){
			return new Date(dateString).getDate();
		}
		_this.getMonth = function(dateString){
			return monthNames[new Date(dateString).getMonth()];
		}
		
		_this.show = function() {
			ModalService.showModal({
				templateUrl: 'src/admin/student/addBadge.html',
				controller: "AddStudentBadge",
				controllerAs: 'addBadge'
			}).then(function(modal) {
				modal.element.modal();
				modal.close.then(function(result) {
					$(".modal-backdrop").remove();
					var studentTimeline = {};
					studentTimeline.studentId = $stateParams.studentId;
					studentTimeline.title = result.title;
					studentTimeline.description = result.desc;
					studentTimeline.badgeString = result.selectedBadge;
					var postTimeline = CreateStudentTimelineEventService.addStudentTimeline({studentId : null},studentTimeline);
					postTimeline.$promise.then(function (result){
						_this.newTimelineEvent = result;
					});
				});
			});
		};
	}

	AddStudentBadge.$inject=['close']
	function AddStudentBadge( close) {
		var _this = this;
		_this.badgeList = [{badgeIconUrl:'images/badges/complete-profile-badge.png', badgeTitle:'Complete Profile'},{badgeIconUrl:'images/badges/good-citizen.png', badgeTitle:'Good Citizen'},{badgeIconUrl:'images/badges/good-question.png', badgeTitle:'Good Question'},
							{badgeIconUrl:'images/badges/hard-worker.png', badgeTitle:'Hard Worker'},{badgeIconUrl:'images/badges/homework-helper.png', badgeTitle:'Homework Helper'},{badgeIconUrl:'images/badges/participant.png', badgeTitle:'Participant'},
							{badgeIconUrl:'images/badges/perfect-attendance.png', badgeTitle:'Prefect Attendance'},{badgeIconUrl:'images/badges/star-performer.png', badgeTitle:'Star Performer'},{badgeIconUrl:'images/badges/student-month.png', badgeTitle:'Student Of The Month'}];
		_this.close = function(isValid, result) {
			if(!isValid)return false;
			close(result, 500);
		};
	}
})();
