$(document).ready(function() {
	
	
	$('#forgotpassword').hide();
	$("#forgotusername").hide();
	$('#subscriberlogin').show();

	
	$('#forgotuserid').click(function() {
	    try {
	        // Your code here
	        $('#forgotusername').show();
	        $('#forgotpassword').hide();
	        $('#subscriberlogin').hide();
	    } catch (error) {
	        // Handle any errors here
	        console.error("An error occurred:", error);
	    }
	});

	
	$('#forgotuserpassword')
	.click(
			function() {
			    try {
				$('#forgotusername').hide();
				$('#forgotpassword').show();
				$('#subscriberlogin').hide();
			 } catch (error) {
			        // Handle any errors here
			        console.error("An error occurred:", error);
			    }

			});
	
	
	
	$('#passwordcnclbtn')
	.click(
			function() {
			    try {
				$('#forgotusername').hide();
				$('#forgotpassword').hide();
				$('#subscriberlogin').show();
			    } catch (error) {
			        // Handle any errors here
			        console.error("An error occurred:", error);
			    }

			});
	$('#useridcnclbtn')
	.click(
			function() {
			    try {
				$('#forgotusername').hide();
				$('#forgotpassword').hide();
				$('#subscriberlogin').show();
			 } catch (error) {
			        // Handle any errors here
			        console.error("An error occurred:", error);
			    }

			});
	

	$('#togglePassword').on('click', function() {
	    try {
	        const passwordInput = $('#password');
	        const showPasswordIcon = $('#showpassword');

	        if (passwordInput.attr('type') === 'password') {
	            passwordInput.attr('type', 'text');
	            showPasswordIcon.removeClass('fa-eye');
	            showPasswordIcon.addClass('fa-eye-slash');
	        } else {
	            passwordInput.attr('type', 'password');
	            showPasswordIcon.removeClass('fa-eye-slash');
	            showPasswordIcon.addClass('fa-eye');
	        }
	    } catch (error) {
	        console.error('An error occurred:', error);
	        // You can handle the error here, e.g., display a message to the user.
	    }
	});




    $(".cpointer").click(function() {
        $.get("/getcaptcha", function(data) {
            var captchaNumber = data;
            $(".captcha-image").attr("src", "data:image/png;base64," + captchaNumber.image);
            $("#subscribecaptcha").val(""); // Clear the input field for captcha
        });
    })
});

