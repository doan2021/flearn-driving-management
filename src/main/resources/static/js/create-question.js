$(document).ready(function() {
    $('#addAnswer').click(function(){
        var numAnswers = $('#content-answer').children('.answer').length;
        if (numAnswers < 10) {
            var newAnswer = '<div class="row mt-4 answer">'+
                                '<div class="col-9">'+
                                    '<div class="input-group" id="errorPlace' + numAnswers + '">'+
                                        '<div class="input-group-prepend">'+
                                            '<button class="btn btn-outline-danger" type="button" id="deleteAnswer"><i class="fas fa-trash-alt"></i></button>'+
                                        '</div>'+
                                        '<input type="text" class="form-control" placeholder="Đáp án ' + (numAnswers + 1) + '" name="listAnswers[' + numAnswers + '].content">'+
                                    '</div>'+
                                '</div>'+
                                '<div class="col-3 d-flex align-items-center justify-content-center">'+
                                    '<div class="custom-control custom-checkbox">'+
                                        '<input type="checkbox" class="custom-control-input" id="answer' + numAnswers + '">'+
                                        '<label class="custom-control-label" for="answer' + numAnswers + '" name="listAnswers[' + numAnswers + '].isTrue">Đáp án đúng</label>'+
                                    '</div>'+
                                '</div>'+
                            '</div>';
            $('#content-answer').append(newAnswer);
            var numAnswers = $('#content-answer').children('.answer').length;
            if (numAnswers >= 10) {
                $(this).prop('disabled', true);
            }
        }
    });
    
    // Validate
    $("#formCreateQuestion").validate({
        rules: {
            number: {
                required: true,
                maxlength: 255
            },
            content: {
                required: true,
                maxlength: 4000
            },
            'listAnswers[0].content': {
                required: true
            },
            'listAnswers[1].content': {
                required: true
            },
            'listAnswers[2].content': {
                required: true
            },
            'listAnswers[3].content': {
                required: true
            },
            'listAnswers[4].content': {
                required: true
            },
            'listAnswers[5].content': {
                required: true
            },
            'listAnswers[6].content': {
                required: true
            },
            'listAnswers[7].content': {
                required: true
            },
            'listAnswers[8].content': {
                required: true
            },
            'listAnswers[9].content': {
                required: true
            }
        },
        messages: {
            number: {
                required : "Vui lòng điền số thứ tự câu hỏi!",
                maxlength: "Vui lòng nhập dưới 255 ký tự!"
            },
            content: {
            	required : "Vui lòng điền nội dung câu hỏi!",
                maxlength: "Nội dung không được nhập quá 4000 ký tự, vui lòng kiểm tra lại"
            },
            'listAnswers[0].content': {
                required: "Vui lòng điền nội dung đáp án 1!",
            },
            'listAnswers[1].content': {
                required: "Vui lòng điền nội dung đáp án 2!",
            },
            'listAnswers[2].content': {
                required: "Vui lòng điền nội dung đáp án 3!",
            },
            'listAnswers[3].content': {
                required: "Vui lòng điền nội dung đáp án 4!",
            },
            'listAnswers[4].content': {
                required: "Vui lòng điền nội dung đáp án 5!",
            },
            'listAnswers[5].content': {
                required: "Vui lòng điền nội dung đáp án 6!",
            },
            'listAnswers[6].content': {
                required: "Vui lòng điền nội dung đáp án 7!",
            },
            'listAnswers[7].content': {
                required: "Vui lòng điền nội dung đáp án 8!",
            },
            'listAnswers[8].content': {
                required: "Vui lòng điền nội dung đáp án 9!",
            },
            'listAnswers[9].content': {
                required: "Vui lòng điền nội dung đáp án 10!",
            }
        },
        errorClass: 'text-danger m-0',
        highlight: function(element) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function(element) {
            $(element).removeClass('is-invalid');
        },
        errorPlacement: function (error, element) {
            switch (element.attr("name")) {
            case 'listAnswers[2].content':
                error.insertAfter($("#errorPlace2"));
                break;
            case 'listAnswers[3].content':
                error.insertAfter($("#errorPlace3"));
                break;
            case 'listAnswers[4].content':
                error.insertAfter($("#errorPlace4"));
                break;
            case 'listAnswers[5].content':
                error.insertAfter($("#errorPlace5"));
                break;
            case 'listAnswers[6].content':
                error.insertAfter($("#errorPlace6"));
                break;
            case 'listAnswers[7].content':
                error.insertAfter($("#errorPlace7"));
                break;
            case 'listAnswers[8].content':
                error.insertAfter($("#errorPlace8"));
                break;
            case 'listAnswers[9].content':
                error.insertAfter($("#errorPlace9"));
                break;
            default:
                error.insertAfter(element);
            }
        },
        submitHandler: function(form) {
        	if (!checkAnswerTrue()) {
        		$('#errorCheckIsTrue').removeClass('d-none');
        	} else {
	            if (confirm("Xác nhận tạo mới câu hỏi!")) {
	                form.submit();
	            }
        	}
        }
    });
});
// Delete answer
$(document).on('click', '#deleteAnswer',function() {
    $(this).parents('.answer').remove();
    $('#addAnswer').prop('disabled', false);
});

function checkAnswerTrue() {
	if ($('#answers0').is(":checked")) {
		return true;
	}
	if ($('#answers1').is(":checked")) {
		return true;
	}
	if ($('#answers2').is(":checked")) {
		return true;
	}
	if ($('#answers3').is(":checked")) {
		return true;
	}
	if ($('#answers4').is(":checked")) {
		return true;
	}
	if ($('#answers5').is(":checked")) {
		return true;
	}
	if ($('#answers6').is(":checked")) {
		return true;
	}
	if ($('#answers7').is(":checked")) {
		return true;
	}
	if ($('#answers8').is(":checked")) {
		return true;
	}
	if ($('#answers9').is(":checked")) {
		return true;
	}
	return false;
}

// Init file input
$("#images-question").fileinput({
    theme         : "fas",
    showCancel    : false,
    showUpload    : false,
    showClose     : false,
    browseLabel   : 'Tải lên ảnh',
    removeLabel   : 'Xóa',
    removeIcon    : '<i class="fas fa-trash-alt"></i>',
    removeClass   : 'btn btn-danger',
    dropZoneTitle : 'Kéo và thả ảnh vào đây',
    maxFileSize   : '102400',
    msgSizeTooLarge : 'Tải lên ảnh tối đa {size} KB',
    msgProcessing : 'Đang chọn file....'
});