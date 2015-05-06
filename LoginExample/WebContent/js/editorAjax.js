//
//
//
$(document).ready(function(){
	$('#updateUsername').submit(function(){
		$.ajax({
			url:'EditorRetireAndAppointOne',
			type:'post',
			dataType:'json',
			data:$('#updateUsername').serialize(),
			success:function(data){
				if(data.isValid){
					$('#displayName').html('Your name is ' + data.username);
					$('#displayName').slideDown(500);
					alert("error");
				}
				else{
					alert("error");
				}
			}
		});
		return false;
	});
});