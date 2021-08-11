$("#update-chapter-form").validate({
	rules: {
		index: {
			required: true,
			maxlength: 5
		},
		name: {
			required: true,
			maxlength: 255
		},
		description: {
			maxlength: 4000
		}
	},
	messages: {
		index: {
			required: "Chưa điền tên chương, vui lòng kiểm tra lại!",
			maxlength: "Tên chương không được nhập quá 5 ký tự, vui lòng kiểm tra lại!"
		},
		description: {
			maxlength: "Mô tả chương không được nhập quá 4000 ký tự, vui lòng kiểm tra lại"
		},
		name: {
			required: "Chưa điền nội dung chương, vui lòng kiểm tra lại!",
			maxlength: "Nội dung chương không được nhập quá 255 ký tự, vui lòng kiểm tra lại!"
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
        if (confirm("Xác nhận cập nhật chương!")) {
            form.submit();
        }
	}
});
