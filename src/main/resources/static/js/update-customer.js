function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#image-preview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}

$("#update-customer-form").validate({
	rules: {
		firstName: {
			required: true,
			maxlength: 36
		},
		middleName: {
			maxlength: 36
		},
		lastName: {
			required: true,
			maxlength: 36
		},
		email: {
			required: true,
			email: true,
			maxlength: 255
		},
		numberPhone: {
			required: true,
			number: true,
			rangelength: [10, 10]
		},
		birthDay: {
			required: true,
			maxlength: 10
		},
		userName: {
			required: true,
			maxlength: 36
		},
		gender: {
			required: true
		},
	},
	messages: {
		firstName: {
			required: "Vui lòng nhập tên!",
			maxlength: "Tên không được nhập quá 36 ký tự!"
		},
		middleName: {
			maxlength: "Tên đệm không được nhập quá 36 ký tự!"
		},
		lastName: {
			required: "Vui lòng nhập họ!",
			maxlength: "Họ không được nhập quá 36 ký tự!"
		},
		email: {
			required: "Vui lòng nhập Email!",
			email: "Email chưa đúng định dạng, vui lòng kiểm tra lại!",
			maxlength: "Email phải dưới 255 kí tự, vui lòng kiểm tra lại!"
		},
		numberPhone: {
			required: "Vui lòng nhập số điện thoại!",
			rangelength: "Số điện thoại phải đủ 10 số, vui lòng kiểm tra lại!",
			number: "Số điện thoại phải là số, vui lòng kiểm tra lại!"
		},
		birthDay: {
			required: "Vui lòng chọn ngày sinh!",
			maxlength: "Ngày sinh chỉ được nhập 10 ký tự!"
		},
		userName: {
			required: "Vui lòng điền tên đăng nhập!",
			maxlength: "Tên đăng nhập phải dưới 36 kí tự, vui lòng kiểm tra lại!"
		},
		gender: {
			required: "Vui lòng chọn giới tính!"
		},
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
		case 'lastName':
            error.insertAfter($("#lastName-place"));
            break;
        case 'firstName':
            error.insertAfter($("#firstName-place"));
            break;
        case 'birthDay':
            error.insertAfter($("#birthday-place"));
            break;
		case 'numberPhone':
			error.insertAfter($("#numberPhone-place"));
			break;
		case 'email':
            error.insertAfter($("#email-place"));
            break;
        case 'userName':
            error.insertAfter($("#username-place"));
            break;
        default:
            error.insertAfter(element);
        }
    },
	submitHandler: function(form) {
		if (confirm("Xác nhận chỉnh sửa tài khoản?")) {
			form.submit();
		}
	}
});

function deletecustomer() {
	if (confirm("Xác nhận xóa tài khoản?")) {
		$("#delete-customer-form").submit();
	}
}