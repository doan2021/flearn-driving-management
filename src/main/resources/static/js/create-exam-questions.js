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
            if (confirm("Xác nhận tạo mới đề thi!")) {
                form.submit();
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
        } else {
            $('#labelNumberQuestionInChapter'+chapterIndex).addClass('text-danger');
            $('#labelNumberQuestionInChapter'+chapterIndex).removeClass('text-success');
        }
    });
});

function checkNumberQuestionForChapter() {
    var numberOfChapter = $('#numberOfChapter').val();
}

function checkNumberQuestion() {
    var numberQuestion = $('#numberQuestion').val();
    var numberQuestionParalysis = $('#numberQuestionParalysis').val();
    var numberQuestionCheck = $('.question-check').filter(':checked').length;
    var numberQuestionParalysisCheck = $('.question-paralysis-check').filter(':checked').length;
    
    if (numberQuestion != numberQuestionCheck) {
        
    }
}
