var pageCreateExamQuestions = new Vue({
    el: '#create-exam-questions-app',
    data: {
        listDrivingLicense    : [],
        listChapter           : [],
        drivingLicenseCurrent : '',
        listQuestionAdd       : []
    },
    mounted() {
    	this.initPage();
    },
    methods: {
        initPage: function() {
            var _this = this;
            var config = {
                url: "/management/init-create-exam-questions", 
                method: "GET"
            }
            axios(config).then(function (response) {
                _this.listDrivingLicense = response.data.result.listDrivingLicense;
            })
            .catch(function (error) {
            });
        },
        selectDrivingLicense: function() {
            var _this = this;
            var config = {
                url: "/management/select-driving-license", 
                method: "GET"
            }
            axios(config).then(function (response) {
            })
            .catch(function (error) {
            });
        },
        parseJsonNumQueChap: function() {
        	_this = this;
        	return JSON.parse(_this.drivingLicenseCurrent.numberQuestionInChapter);
        }
    }
});