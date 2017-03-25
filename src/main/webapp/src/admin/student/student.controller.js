(function() {
'use strict';

	
	angular.module('admin').controller('StudentListController', StudentListController );
	angular.module('admin').controller('ViewStudentDetailsController', ViewStudentDetailsController );
	angular.module('admin').controller('AddStudentBadge', AddStudentBadge );
	
	
	StudentListController.$inject=['ListStudentService']

	function StudentListController(ListStudentService){
		var _this = this;
		console.log('CONTROLLER------------'   );
					_this.test = 'aaaaaaaaa';
		var students = ListStudentService.listStu(function(students) {
			return students;
		});
		
		
		//ListStudentService.getMessages();
		
		students.$promise.then(function (result) {
			console.log(result[0]);
			_this.studentList = result;
			_this.test = result[0];
		});
		
		
		
		
			
	}
	
	
	ViewStudentDetailsController.$inject=['ModalService']

	function ViewStudentDetailsController(ModalService){
		var _this = this;
		
		
		
		_this.show = function() {
			ModalService.showModal({
            templateUrl: 'src/admin/student/addBadge.html',
			controller: "AddStudentBadge",
			controllerAs: 'addBadge'

        }).then(function(modal) {
            modal.element.modal();
			modal.close.then(function(result) {
				console.log('---------selected Badge-------' + JSON.stringify(result) + "-----------" );
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
