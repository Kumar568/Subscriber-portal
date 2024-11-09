  
$(document).ready(function() {
	
	
	
$(".cpointer").click(function() {
	  try {
		    	  $.get("/getcaptcha", function(data) {
		              
		              var captchaNumber = data;
		              $(".captcha-image").attr("src", `data:image/png;base64, ${captchaNumber.image}`);
		           
		          });
		    	  
} catch (error) {
    console.error('An error occurred:', error);
    // You can handle the error here, e.g., display a message to the user.
}
		    	
		    });



    $('#generatehidepassword').click(function () {
    	try{
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
        console.error('An error occurred:', error);
        // You can handle the error here, e.g., display a message to the user.
    }
    });

    
    $('#gencpassword').on('change', function() {
    	try{
    	$("#confirmpasswordTooltip").hide();
    	genpassword = $("#genpassword").val();
    	gencpassword =$("#gencpassword").val();
    	
    	if (genpassword == gencpassword){
    		$("#genertaepassword").prop('disabled', false);;
    		
    	}
    	else {
    		$("#genertaepassword").prop('disabled', true);;
    	}
    } catch (error) {
        console.error('An error occurred:', error);
        // You can handle the error here, e.g., display a message to the user.
    }
    	
    });
    
    $('#gencpassword').on('click', function() {
    	try{
    	$("#confirmpasswordTooltip").show();
    } catch (error) {
        console.error('An error occurred:', error);
        // You can handle the error here, e.g., display a message to the user.
    }
    	
    });
    
    
    $(document).on("click", function(e) {
    	try{
        if (!$("#genpassword").is(e.target) && !$("#passwordTooltip").is(e.target) && $("#passwordTooltip").has(e.target).length === 0) {
            // Hide the tooltip when clicking anywhere outside the text box or the tooltip itself
            $("#passwordTooltip").hide();
        }
    	} catch (error) {
    	    console.error('An error occurred:', error);
    	    // You can handle the error here, e.g., display a message to the user.
    	}
    });

    // Attach a click event handler to the "username" text box
    $("#genpassword").click(function(e) {
    	try{
        // Show the tooltip when clicking on the text box
        $("#passwordTooltip").show();
        e.stopPropagation(); // Prevent the click event from propagating to the document
    	} catch (error) {
    	    console.error('An error occurred:', error);
    	    // You can handle the error here, e.g., display a message to the user.
    	}
    });

});
    

    		
$(document).on('click', '#genertaepassword', function(event) {

    event.preventDefault()

    var password = $("#genpassword").val();
    var captcha = $("#generatepasscaptcha").val();

    var passwordData = {
        userName: $('#newgenusername').val(),
        password: password,
        captcha: captcha
    };

    try {
        $.ajax({
            url: '/savecustomerpassword', // Replace with the actual URL to your API endpoint
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(passwordData),
            success: function(response) {
                if (response === "Enter Valid Captcha") {
                    displayValidationMessage("Enter Valid Captcha");
                } else {
                    $("#genp").hide();
                    $("#generatepasswordSuccessblock").show();
                    displayValidationMessage("Password Updated successfully");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // Handle the error here
                console.error("AJAX Error:", textStatus, errorThrown);
                displayValidationMessage("An error occurred during the request.");
            }
        });
    } catch (error) {
        console.error("Try-Catch Error:", error);
        displayValidationMessage("An error occurred in the try-catch block.");
    }
});




function displayValidationMessage(message) {
	try{
    $("#validationMessage").text(message);
    $("#validationModal").modal("show");
} catch (error) {
    console.error('An error occurred:', error);
    // You can handle the error here, e.g., display a message to the user.
}
}

