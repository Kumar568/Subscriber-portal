$(document).ready(function() {
	

	
	$("#resetpassword").click(function() {
		  try {
		    updateresetpassword();
		  } catch (error) {
		    // Handle the exception here
		    console.error("An error occurred:", error);
		  }
		});

	$("#changepassword").click(function() {
		try{
			
		
		changepassword();
		}
		 catch (error) {
			    // Handle the exception here
			    console.error("An error occurred:", error);
		 }
	  });
	
	$(".cpointer").click(function() {
		  try {
		    $.get("/getcaptcha", function(data) {
		      var captchaNumber = data;
		      $(".captcha-image").attr("src", `data:image/png;base64, ${captchaNumber.image}`);
		    });
		  } catch (error) {
		    // Handle the error here
		    console.error("An error occurred:", error);
		  }
		});

	

	$('#generatehidepassword').click(function () {
	    try {
	        const passwordField = $('#genpassword');
	        const type = passwordField.attr('type');

	        if (type === 'password') {
	            passwordField.attr('type', 'text');
	            $('#generatehidepassword').removeClass('fa-eye-slash').addClass('fa-eye');
	        } else {
	            passwordField.attr('type', 'password');
	            $('#generatehidepassword').removeClass('fa-eye').addClass('fa-eye-slash');
	        }
	    } catch (error) {
	        // Handle the error here
	        console.error("An error occurred:", error);
	    }
	});


	  
	$('#gencpassword').on('change', function() {
	    try {
	        $("#confirmpasswordTooltip").hide();
	        var genpassword = $("#genpassword").val();
	        var gencpassword = $("#gencpassword").val();

	        if (genpassword == gencpassword) {
	            $("#resetpassword").prop('disabled', false);
	        } else {
	            $("#resetpassword").prop('disabled', true);
	        }
	    } catch (error) {
	        // Handle the error here
	        console.error("An error occurred:", error);
	    }
	});


	
	$('#gencpassword1').on('change', function() {
	    try {
	        $("#confirmpasswordTooltip").hide();
	        var genpassword = $("#genpassword").val();
	        var gencpassword = $("#gencpassword1").val();

	        if (genpassword === gencpassword) {
	            $("#changepassword").prop('disabled', false);
	        } else {
	            $("#changepassword").prop('disabled', true);
	        }
	    } catch (error) {
	        // Handle the error here
	        console.error("An error occurred:", error);
	    }
	});

	    

	    
	$('#gencpassword').on('click', function() {
	    try {
	        $("#confirmpasswordTooltip").show();
	    } catch (error) {
	        // Handle the error here
	        console.error("An error occurred:", error);
	    }
	});


	$(document).on("click", function(e) {
	    try {
	        if (!$("#genpassword").is(e.target) && !$("#passwordTooltip").is(e.target) && $("#passwordTooltip").has(e.target).length === 0) {
	            // Hide the tooltip when clicking anywhere outside the text box or the tooltip itself
	            $("#passwordTooltip").hide();
	        }
	    } catch (error) {
	        // Handle the error here
	        console.error("An error occurred:", error);
	    }
	});


	$("#genpassword").click(function(e) {
	    try {
	        // Show the tooltip when clicking on the text box
	        $("#passwordTooltip").show();
	        e.stopPropagation(); // Prevent the click event from propagating to the document
	    } catch (error) {
	        // Handle the error here
	        console.error("An error occurred:", error);
	    }
	});



});




function updateresetpassword() {
    try {
        var newgenemailid = $('#newgenemailid').val();
        var newgenusername = $('#newgenusername').val();
        var generatepasscaptcha = $('#generatepasscaptcha').val();
        var genpassword = $('#genpassword').val();

        var requestData = {
            emailid: newgenemailid,
            username: newgenusername,
            password: genpassword,
            subscribecaptcha: generatepasscaptcha
        };

        $.ajax({
            type: 'POST',
            url: '/updateresetpassword', // Replace with your actual endpoint
            data: JSON.stringify(requestData),
            contentType: 'application/json',
            success: function(response) {
                alert("password reset successfully!");
                // Handle success, e.g., show a success message or update the UI
            },
            error: function(xhr, status, error) {
                // Handle error (e.g., display an error message)
            }
        });
    } catch (error) {
        // Handle the error here
        console.error("An error occurred:", error);
    }
}

function changepassword() {
    try {
        var currentpassword = $('#currentpassword').val();
        var newpassword = $('#genpassword').val();
        var userId = $('#userId').val();
        var generatepasscaptcha = $('#generatepasscaptcha').val();

        var requestData = {
            currentpassword: currentpassword,
            password: newpassword,
            userId: userId,
            subscribecaptcha: generatepasscaptcha
        };

        $.ajax({
            type: 'POST',
            url: '/changepassword', // Replace with your actual endpoint
            data: JSON.stringify(requestData),
            contentType: 'application/json',
            success: function(response) {
                alert("password change successfully!");
                // Handle success, e.g., show a success message or update the UI
            },
            error: function(xhr, status, error) {
                // Handle error (e.g., display an error message)
            }
        });
    } catch (error) {
        // Handle the error here
        console.error("An error occurred:", error);
    }
}



