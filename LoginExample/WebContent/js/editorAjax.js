//
//
////
//$(document).ready(function(){
//	$('#updateUsername').submit(function(){
//		$.ajax({
//			url:'EditorRetireAndAppointOne',
//			type:'post',
//			dataType:'json',
//			data:$('#updateUsername').serialize(),
//			success:function(data){
//				if(data.isValid){
//					$('#displayName').html('Your name is ' + data.username);
//					$('#displayName').slideDown(500);
//					alert("error");
//				}
//				else{
//					alert("error");
//				}
//			}
//		});
//		return false;
//	});
//});

//$(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...
//    $('#somebutton').click(function() {               // Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
//        $.post('EditorRetireAndAppointOne', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
//            $('#somediv').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
//        });
//    });
//});
$(document).ready(function() {                        
                $('#submit').click(function(event) {  
                    var username=$('#user').val();
                 $.get('EditorRetireAndAppointOne',{user:username},function(responseText) { 
                        $('#welcometext').text(responseText);         
                    });
                });
            });

    //return false;
