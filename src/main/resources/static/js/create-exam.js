$("#create-exam-form").validate({
	rules: {
		name: {
			required: true,
			maxlength: 255
		},
		description: {
			maxlength: 4000
		},
		startDate: {
			required: true,
			maxlength: 10
		},
		endDate: {
			required: true,
			maxlength: 10
		}
	},
	messages: {
		name: {
			required : "Chưa điền tên kỳ thi, vui lòng kiểm tra lại!",
			maxlength: "Vui lòng nhập dưới 255 ký tự!"
		},
		description: {
			maxlength: "Không được nhập quá 4000 ký tự, vui lòng kiểm tra lại"
		},
		startDate: {
			required : "Chưa chọn ngày bắt đầu, vui lòng kiểm tra lại!",
		}
	},
	
	errorClass: 'text-danger',
	highlight: function(element) {
		$(element).addClass('is-invalid');
	},
	unhighlight: function(element) {
		$(element).removeClass('is-invalid');
	},
	submitHandler: function(form) {
		form.submit();
	}
});
