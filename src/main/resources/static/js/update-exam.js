/*$(document).ready(function() {
	$("#update-exam-form").validate({
		rules: {
			name: {
				required: true,
				maxlength: 255
			},
			description: {
				maxlength: 4000
			},
			strDateRegisExamEnd: {
				required: true,
				maxlength: 10
			}
		},
		messages: {
			name: {
				required : "Vui lòng điền tên kỳ thi!",
				maxlength: "Vui lòng nhập dưới 255 ký tự!"
			},
			description: {
				maxlength: "Mô tả không được nhập quá 4000 ký tự, vui lòng kiểm tra lại"
			},
			strDateRegisExamEnd: {
				required: "Vui lòng chọn ngày hết hạn đăng ký!"
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
			case 'strDateRegisExamEnd':
				error.insertAfter($("#dateRegisExamEndArea"));
				break;
			default:
				error.insertAfter(element);
			}
		},
		submitHandler: function(form) {
			if (confirm("Xác nhận chỉnh sửa kỳ thi?")) {
				form.submit();
			}
		}
	});
});
*/