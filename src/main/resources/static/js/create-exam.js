/*$("#create-exam-form").validate({
    rules: {
        name: {
            required: true,
            maxlength: 255
        },
        description: {
            maxlength: 4000
        },
        dateRegisExamEnd: {
            required: true,
            maxlength: 10
        },
        drivingLicenseId: {
            required: true
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
        dateRegisExamEnd: {
            required: "Vui lòng chọn ngày hết hạn đăng ký!"
        },
        drivingLicenseId: {
            required: "Vui lòng chọn loại bằng!"
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
        case 'dateRegisExamEnd':
            error.insertAfter($("#dateRegisExamEndArea"));
            break;
        case 'typeDrivingLicense':
            error.insertAfter($("#typeDrivingLicense"));
            break;
        default:
            error.insertAfter(element);
        }
    },
    submitHandler: function(form) {
        if (confirm("Xác nhận tạo mới kỳ thi!")) {
            form.submit();
        }
    }
});
*/