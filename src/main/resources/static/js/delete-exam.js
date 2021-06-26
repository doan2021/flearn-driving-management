$(document).ready(function(){
  $(".del").click(function(){
    if (!confirm("Do you want to delete")){
      return false;
    }
  });
});