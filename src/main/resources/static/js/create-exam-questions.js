$( document ).ready(function() {
    $("#formCreateExamQuestions").validate({
        rules: {
            name: {
                required: true,
                maxlength: 255
            },
            description: {
                maxlength: 4000
            }
        },
        messages: {
            name: {
                required: "Vui lòng điền tên!",
                maxlength: "Tên không được quá 255 ký tự!"
            },
            description: {
                maxlength: "Mô tả chương không được quá 4000 ký tự!"
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
            if (!isInvalidNumberQuestionForChapter() & isInvalidTotalNumberQuestion()) {
                if (confirm("Xác nhận tạo mới đề thi!")) {
                    form.submit();
                }
            } 
        }
    });
    
    $('.question-check').change(function(){
        var chapterIndex = $(this).attr("chapter-index");
        var numberQuestionCheckInChapter = $('input[chapter-index=' + chapterIndex + ']').filter(':checked').length;
        var numberQuestionInChapter = $('#numberQuestionInChapter' + chapterIndex).val();
        $('#numberQuestionCheckInChapter'+chapterIndex).text(numberQuestionCheckInChapter);
        if (numberQuestionCheckInChapter == numberQuestionInChapter) {
            $('#labelNumberQuestionInChapter'+chapterIndex).removeClass('text-danger');
            $('#labelNumberQuestionInChapter'+chapterIndex).addClass('text-success');
            $('#errorNumberQuestionInChapter' + chapterIndex).text('');
        } else {
            $('#labelNumberQuestionInChapter'+chapterIndex).addClass('text-danger');
            $('#labelNumberQuestionInChapter'+chapterIndex).removeClass('text-success');
        }
        checkTotalNumberQuestion();
    });
    
    $('.question-paralysis-check').change(function(){
        var numberQuestionParalysisCheck = $('.question-paralysis-check').filter(':checked').length;
        var numberQuestionParalysis = $('#numberQuestionParalysis').val();
        $('#numberQuestionParalysisCheck').text(numberQuestionParalysisCheck);
        if (numberQuestionParalysisCheck == numberQuestionParalysis) {
            $('#labelNumberQuestionParalysis').removeClass('text-danger');
            $('#labelNumberQuestionParalysis').addClass('text-success');
            $('#errorNumberQuestionParalysis').text('');
        } else {
            $('#labelNumberQuestionParalysis').addClass('text-danger');
            $('#labelNumberQuestionParalysis').removeClass('text-success');
        }
        checkTotalNumberQuestion();
    });
});
function checkTotalNumberQuestion() {
	var totalNumberQuestion = $('#numberQuestion').val();
	var totalNumberQuestionCheck = $('.question-check').filter(':checked').length + $('.question-paralysis-check').filter(':checked').length;
	$('#totalNumberQuestionCheck').text(totalNumberQuestionCheck);
	if(totalNumberQuestion == totalNumberQuestionCheck) {
        $('#totalNumberQuestion').addClass('text-success');
        $('#totalNumberQuestion').removeClass('text-danger');
	} else {
        $('#totalNumberQuestion').addClass('text-danger');
        $('#totalNumberQuestion').removeClass('text-success');
	}
}

function isInvalidNumberQuestionForChapter() {
    var isError = false;
    var numberOfChapter = $('#numberOfChapter').val();
    for (let i = 0; i < numberOfChapter; i++) {
        var numberQuestionInChapter = $('#numberQuestionInChapter' + i).val();
        var numberQuestionCheckInChapter = $('input[chapter-index=' + i + ']').filter(':checked').length;
        if (numberQuestionInChapter != numberQuestionCheckInChapter) {
            $('#errorNumberQuestionInChapter' + i).text('Số lượng câu hỏi không khớp!');
            isError = true;
        } else {
            $('#errorNumberQuestionInChapter' + i).text('');
            isError = false;
        }
    }
    
    var numberQuestionParalysisCheck = $('.question-paralysis-check').filter(':checked').length;
    var numberQuestionParalysis = $('#numberQuestionParalysis').val();
    if (numberQuestionParalysisCheck != numberQuestionParalysis) {
        $('#errorNumberQuestionParalysis').text('Số lượng câu hỏi không khớp!');
        isError = true;
    } else {
        $('#errorNumberQuestionParalysis').text('');
        isError = false;
    }
    return isError;
}

function isInvalidTotalNumberQuestion() {
	var totalNumberQuestion = $('#numberQuestion').val();
	var totalNumberQuestionCheck = $('.question-check').filter(':checked').length + $('.question-paralysis-check').filter(':checked').length;
	return !(totalNumberQuestion == totalNumberQuestionCheck);
}
