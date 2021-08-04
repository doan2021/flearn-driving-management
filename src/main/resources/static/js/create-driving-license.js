$(document).ready(function(){
    $('.sub').click(function () {
        var min = parseInt($(this).parent('.input-group-prepend').next().attr('min'));
        if ($(this).parent('.input-group-prepend').next().val() > min) {
            $(this).parent('.input-group-prepend').next().val(+$(this).parent('.input-group-prepend').next().val() - 1);
        }
    });
    $('.add').click(function () {
        var max = parseInt($(this).parent('.input-group-append').prev().attr('max'));
        if($(this).parent('.input-group-append').prev().val() < max) {
            $(this).parent('.input-group-append').prev().val(+$(this).parent('.input-group-append').prev().val() + 1);
        }
    });
});