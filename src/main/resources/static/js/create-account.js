function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#image-preview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}

$("#create-account-form").validate({
	rules: {
		firstName: {
			required: true,
			maxlength: 255
		},
		middleName: {
			maxlength: 255
		},
		lastName: {
			required: true,
			maxlength: 255
		},
		email: {
			required: true,
			email: true
		},
		numberPhone: {
			required: true,
			number: true,
			rangelength: [10, 10]
		},
		birthDay: {
			required: true
		},
		userName: {
			required: true
		},
		password: {
			required: true,
			minlength: 8
		},
		cPassword: {
			equalTo: "#password"
		},
		gender: {
			required: true
		},
		roles: {
			required: true
		}
	},
	messages: {
		firstName: {
			required: "Vui lòng nhập tên!"
		},
		middleName: {
			
		},
		lastName: {
			required: "Vui lòng nhập họ!"
		},
		email: {
			required: "Vui lòng nhập Email!",
			email: "Email chưa đúng định dạng, vui lòng kiểm tra lại!"
		},
		numberPhone: {
			required: "Vui lòng nhập số điện thoại!",
			rangelength: "Số điện thoại phải đủ 10 số, vui lòng kiểm tra lại!",
			number: "Số điện thoại phải là số, vui lòng kiểm tra lại!"
		},
		birthDay: {
			required: "Vui lòng chọn ngày sinh!"
		},
		userName: {
			required: "Vui lòng điền tên đăng nhập!"
		},
		password: {
			required: "Vui lòng nhập mật khẩu!",
			minlength: "Mật khẩu phải trên 8 kí tự, vui lòng kiểm tra lại!"
		},
		confirmPassword: {
			equalTo: "Mật khẩu không khớp, vui lòng kiểm tra lại!"
		},
		gender: {
			required: "Vui lòng chọn giới tính!"
		},
		roles: {
			required: "Vui lòng chọn quyền"
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
        case 'password':
            error.insertAfter($("#password-place"));
            break;
        case 'confirmPassword':
            error.insertAfter($("#cPassword-place"));
            break;
        case 'roles':
            error.insertAfter($("#role-place"));
            break;
        default:
            error.insertAfter(element);
        }
    },
	submitHandler: function(form) {
		form.submit();
	}
});
