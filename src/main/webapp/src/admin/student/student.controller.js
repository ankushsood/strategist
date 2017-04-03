(function() {
'use strict';
	
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
	
	ViewStudentDetailsController.$inject=['$stateParams', 'GetStudentDetailsService' ,'ModalService', '$location']
	function ViewStudentDetailsController($stateParams, GetStudentDetailsService, ModalService, $location){
		var _this = this;
		console.log($stateParams.studentId)
		
		if($stateParams.studentId == undefined || $stateParams.studentId == null){
			$location.url('/admin/home/students');
		}
		var studentDetails = GetStudentDetailsService.studentDetails
		({studentId : $stateParams.studentId},function(students) {
			return students;
		});
		
		studentDetails.$promise.then(function (result) {
			_this.student = result;
		});
		
		_this.show = function() {
			ModalService.showModal({
            templateUrl: 'src/admin/student/addBadge.html',
			controller: "AddStudentBadge",
			controllerAs: 'addBadge'

        }).then(function(modal) {
            modal.element.modal();
			modal.close.then(function(result) {
				console.log('---------selected Badge-------' + JSON.stringify(result) + "-----------" + JSON.stringify(_this.student));
				$(".modal-backdrop").remove();
				_this.student

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
		_this.close = function(result) {
			close(result, 500);
		};
	}
})();
