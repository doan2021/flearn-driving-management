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
			maxlength: "Mô tả không được nhập quá 4000 ký tự, vui lòng kiểm tra lại"
		},
		startDate: {
			required : "Chưa chọn ngày bắt đầu đăng ký, vui lòng kiểm tra lại!",
		},
		endDate: {
			required: "Chưa chọn ngày kết thúc đăng ký, vui lòng kiểm tra lại!",
		}
	},
	
	errorClass: 'text-danger',
	highlight: function(element) {
		$(element).addClass('is-invalid');
	},
	unhighlight: function(element) {
		$(element).removeClass('is-invalid');
	},
	errorPlacement: function (error, element) {
        switch (element.attr("name")) {
        case 'startDay':
            error.insertAfter($("#startDay"));
            break;
        case 'endtDay':
            error.insertAfter($("#endDay"));
            break;
        default:
            error.insertAfter(element);
        }
    },
	submitHandler: function(form) {
		form.submit();
	}
});
