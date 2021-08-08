// Function seach common
function search(num) {
	$('#pageNumber').val(num);
	$('#formSearch').submit();
}

//Init file input
$(".image-input").fileinput({
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