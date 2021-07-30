$("#create-chapter-form").validate({
	rules: {
		name: {
			required: true,
			maxlength: 255
		},
		content: {
			required: true,
			maxlength: 4000
		},
		description: {
			maxlength: 4000
		}
	},
	messages: {
		name: {
			required: "Chưa điền tên chương, vui lòng kiểm tra lại!",
			maxlength: "Nội dung chương không được nhập quá 255 ký tự, vui lòng kiểm tra lại!"
		},
		description: {
			maxlength: "Mô tả chương không được nhập quá 4000 ký tự, vui lòng kiểm tra lại"
		},
		content: {
			required: "Chưa điền nội dung chương, vui lòng kiểm tra lại!",
			maxlength: "Nội dung chương không được nhập quá 4000 ký tự, vui lòng kiểm tra lại!"
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
