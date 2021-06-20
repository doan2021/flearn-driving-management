$("#create-exam-form").validate({
	rules: {
		name: {
			required: true,
			maxlength: 255
		},
		description: {
			required: true,
			maxlength: 4000
		}
	},
	messages: {
		name: {
			required : "Bat buoc",
			maxlength: "Vui lòng nhập trên 255 ký tự!"
		},
		description: {
			minlength: "Vui lòng nhập trên 10 ký tự!",
			maxlength: "Không được nhập quá 4000 ký tự!"
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
