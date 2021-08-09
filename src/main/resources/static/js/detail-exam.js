function deleteExam(){
	if(confirm("Xác nhận xóa kỳ thi?")){
		$('#formDeleteExam').submit();
	}
}

function cancelExam(){
	if(confirm("Xác nhận hủy bỏ kỳ thi?")){
		$('#formCancelExam').submit();
	}
}