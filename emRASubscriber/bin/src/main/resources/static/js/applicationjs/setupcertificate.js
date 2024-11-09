$(document).ready(function() {
	$("#viewCsrButton").hide();
	$("#updatecertificatesubmitbutton").hide();
	$("#cancelcertificatesubmitbutton").hide();
//	$("#certificateform :input:not(#editcertificatesubmitbutton):not(#updatecertificatesubmitbutton):not(#cancelcertificatesubmitbutton)").prop("disabled", true);
	// $("#certificateform :input").prop("disabled", true);
	//$("#editrcsrButton").hide();
	
    $("#csrDetailsModalContent textarea").css("height", "200px"); // Adjust the height value
    
    $('.editUploadDocuments').hide();
	getbirthcountrydropdown();
	//getcountrydropdown();
	 var countryData = /*[[${countryDetails}]]*/ [];
	    var selectedCountry = /*[[${applicationDto?.countryId}]]*/ null;
	var alphabetsExpression=/[a-zA-Z]/;
		 var numbersExpression =/\d/;
		  var specialCharactersExpression=/[!@#$%^&*(),.?":{}|<>]/;
		var numberAndspecialCharactersExpression=/^\d*[!@#$%^&*(),.?":{}|<>]+\d*$/;

		
		
		$(document).on('click', '#filllaterCsrButton', function(e) {
		
		
			$("#validateCSR").hide();
			$("#uploadCSR").show();

		});
		
		$(document).on('click', '#viewCsrButton', function(e) {
			$("#csrDetailsModal").modal("show");



		});
	
	 $("#setupmanagecertificate").hide();
	  $("#listmanagecertificatepage").show();
	
	showmanagecertificatelist();
	
	
	  $('#selectCountry').change(function () {
          var selectedCountryId = $(this).val();

          // Make an Ajax request to get states based on the selected country
          $.ajax({
              type: 'GET',
              url: '/state/' + selectedCountryId,
              success: function (data) {
                  // Update the state dropdown with the received state data
                  var stateDropdown = $('#selectState');
                  stateDropdown.empty(); // Clear existing options
                  stateDropdown.append('<option value="0" selected="selected">Select</option>'); // Add default option

                  $.each(data, function (index, state) {
                      stateDropdown.append('<option value="' + state.id + '">' + state.stateName + '</option>');
                  });
                  
                  var selectedStateId = "${applicationDto?.stateId}";
                  stateDropdown.val(selectedStateId);
              },
              error: function (error) {
                  console.error('Error fetching states: ' + error);
              }
          });
      });
	
	  let appId = $("#action-submit-email").attr("data-applicationId-value");
		 let token = $("#action-submit-email").data("tokenValue");
		
		$("#emailacceptbutton").on("click", function() {
		    AcceptEmail(appId, token, "1");
		});
		
		$("#emailrejectbutton").on("click", function() {
			 AcceptEmail(appId, token, "2");
		});
		
		
		$('#resendOTP').click(function() {
			
			let emailId = $('.tooltip-wrapper span').text();
			let applicationId =  $("#applicationid").val();
			ResendMail(emailId,applicationId,"1");
			
		});
		
		$('#resendMail').click(function() {
			
			let emailId = $('.tooltip-wrapper span').text();
			let applicationId =  $("#applicationid").val();
			ResendMail(emailId,applicationId,"2");
		});
		
		$('#verifyOTP').click(function() {
			
			let applicationId =  $("#applicationid").val();
			VerifyOTP(applicationId);
		});
		
		$('#docsubmitbutton').click(function() {
			let applicationId =  $("#applicationid").val();
			let applicationNumber = $("#applicationNumber").text();
			type = "1";
			UploadDocuments(applicationId, applicationNumber, type);
			
			
		});
			
		
		$('[id^=fileDownload], [id^=fileType]').on('click', function () {
		      // Get the typeId from the button's ID
		      var typeId = $(this).attr('id').replace('fileDownload', '').replace('fileType', '');
		      if ($(this).attr('id').startsWith('fileDownload')) {
		          // Handle Download button click
		    	  handleDownloadAndView(typeId, false);
		        } else if ($(this).attr('id').startsWith('fileType')) {
		          // Handle View button click
		        	handleDownloadAndView(typeId, true);
		        }
		      });	
		
		$("#doceditbutton").click(function(){

			$(".editUploadDocuments").show();

			 var proccedButton = $('<button type="button" class="btn btn-default validationdocsubmitbutton">Proceed</button>');
			 	$('#doceditbutton').hide();
				$('#docmenteditbutton').hide();
			 	$("#documentsubmitbutton").append(proccedButton);
	    	  
			
		});
		
	  
	 
	  $(document).on('click', '#editcertificatesubmitbutton', function(e) {
			$("#updatecertificatesubmitbutton").show();
			$("#editcertificatesubmitbutton").hide();
			$("#cancelcertificatesubmitbutton").show();

			

            $("#certificateform :input").prop("disabled", false);

		});
	  $(document).on('click', '#cancelcertificatesubmitbutton', function(e) {
			$("#updatecertificatesubmitbutton").hide();
			$("#editcertificatesubmitbutton").show();
			$("#cancelcertificatesubmitbutton").hide();

			

			$("#certificateform :input:not(#editcertificatesubmitbutton):not(#updatecertificatesubmitbutton):not(#cancelcertificatesubmitbutton)").prop("disabled", true);


		});
	  
	

	
	
//	  $(document).on('click', '#csrsubmitbutton', function(e) {
//		    e.preventDefault();
//		    var applicationId = $('#applicationid').val();
//	
//		    	 savecsrdetails(applicationId)
//		 
//		});
	  
		
	  
	  
	  $('#saveCsrButton').on('click', function () {
		  var applicationId = $('#applicationid').val();
          saveCsrContent(applicationId);
         
      });
	  
	  $('#fileInput').on('change', function() {
		  uploadFile(this);
		  //uploadFile();
	  });
	$('.validate-field').on('change', function() {
	    var fieldId = $(this).attr('id');
	    var errorId = 'error_' + fieldId.split('_')[1];
	    var errorDiv = $('#' + errorId);

	    var alphabetsValidation = $(this).data('alphabets-validation');
	    var numbersValidation = $(this).data('numbers-validation');
	    var specialCharactersValidation = $(this).data('special-characters-validation');

	    var value = $(this).val();

	    if (
	    		  alphabetsValidation === 1 &&
	    		  numbersValidation === 1 &&
	    		  specialCharactersValidation === 1 &&
	    		  !(alphabetsExpression.test(value) && numbersExpression.test(value) && specialCharactersExpression.test(value))
	    		) {
	    		  errorDiv.text('Must contain alphabets, numbers, and special characters.');
	    		  return;
	    		}

	    else if (numbersValidation==0 && specialCharactersValidation===0 && alphabetsValidation===1 && !alphabetsExpression.test(value)) {
	        errorDiv.text('Only alphabets are allowed');
	        return;
	    } else if (alphabetsValidation===0 && specialCharactersValidation===0 && numbersValidation===1 && !numbersExpression.test(value)) {
	        errorDiv.text('Only numbers are allowed');
	        return;
	    } else if (numbersValidation===0 && alphabetsValidation===0 && specialCharactersValidation===1 && !specialCharactersExpression.test(value)) {
	        errorDiv.text('Only special characters are allowed');
	        return;
	    }
	 else if (
			  specialCharactersValidation === 0 &&
			  alphabetsValidation === 1 &&
			  numbersValidation === 1 &&
			  !(alphabetsExpression.test(value) && numbersExpression.test(value)))
			 {
			  errorDiv.text('Must contain alphabets and numbers,');
			  return;
			}
 else if (numbersValidation===0 && alphabetsValidation===1 && specialCharactersValidation===1 && (!alphabetsExpression.test(value) || !specialCharactersExpression.test(value))) {
	        errorDiv.text('Must contain at least one alphabet and one special character');
	        return;
	    } else if (alphabetsValidation===0 && numbersValidation===1 && specialCharactersValidation===1 && !numberAndspecialCharactersExpression.test(value)) {
	        errorDiv.text('Must contain numbers and special characters');
	        return;
	    }

	    errorDiv.text('');
	});
	
	

	
	$("#phonenumber").intlTelInput({
		 initialCountry: "IN",
		customPlaceholder: function(selectedCountryPlaceholder) {
			console.log("e.g. " + selectedCountryPlaceholder);
			return "e.g. " + selectedCountryPlaceholder;
		}
	});
	
	$("#mobileNumber").intlTelInput({
		 initialCountry: "IN",
		customPlaceholder: function(selectedCountryPlaceholder) {
			console.log("e.g. " + selectedCountryPlaceholder);
			return "e.g. " + selectedCountryPlaceholder;
		}
	});
	

});


$(document).on('click', '#submitbutton', function(e) {
	e.preventDefault();
	 var Id = $('#id').val();
	
	 saveapplicationdetails(Id);

});

$(document).on('click', '#certificatesubmitbutton', function(e) {
	    e.preventDefault();
	    let applicationId =	$("#applicationid").val();
	    let iscustomfieldValid = validatecustomfiledForm();

	    if (iscustomfieldValid) {
	        savecertificatedetails(applicationId);
	    } else {
	        // Validation failed logic goes here
	        // For example, display an error message or call another function
	        console.log('Validation failed. Please check the form for errors.');
	        // Additional logic for handling validation failure can be added here
	    }
	});


$(document).on('click', '#editrcsrButton', function(e) {
    e.preventDefault();
    var applicationId = $('#applicationid').val();

    editCsr(applicationId);
    $("#encodedData").val('');

    // Clear file input
    $("#fileInput").val('');
    
    $("#viewCsrButton").hide();
    $("#validateButton").show();
    $("#editrcsrButton").hide();
 
});


$(document).on('click', '#updatecertificatesubmitbutton', function(e) {
    e.preventDefault();
    var applicationId = $('#applicationid').val();
    var iscustomfieldValid = validatecustomfiledForm();

    if (iscustomfieldValid) {
        updatecertificatedetails(applicationId);
    } else {
        // Validation failed logic goes here
        // For example, display an error message or call another function
        console.log('Validation failed. Please check the form for errors.');
        // Additional logic for handling validation failure can be added here
    }
});

$(document).on('click', '#validateButton', function(e) {
    e.preventDefault();
    //var applicationId = $('#applicationid').val();
    //$("#uploadCSR").hide();
    validatecsr();
 
});

$(document).on('click', '#commonsubmitandverify', function(e) {
	e.preventDefault();
	let applicationId =  $("#applicationid").val();
	submitForVerification(applicationId);
});




$(document).on('click', '.validationdocsubmitbutton', function(e) {
	e.preventDefault();
		let applicationId = $("#applicationid").val();
		let applicationNumber = $("#applicationNumber").text();
		type ="2";
		UploadDocuments(applicationId, applicationNumber,type);
	});

$(document).on('click', '.docmenteditbutton', function(e) {
	e.preventDefault();
		$('.typeUpload').show();
		$('.uploadType').show();
		$('.uploadType').val('');
		$('.typeUpload').val('');
		 var proccedButton = $('<button type="button" class="btn btn-default validationdocsubmitbutton">Proceed</button>');
		 	$('#doceditbutton').hide();
		 	$('.docmenteditbutton').hide();
		 	$("#documentsubmitbutton").append(proccedButton);
    	  
	});
	


//function showHideFields(enabledFields) {
//    // Hide all fields initially
//    $('.row.form-group').hide();
//
//    // Show only the enabled fields
//    for (var i = 0; i < enabledFields.length; i++) {
//        $('#' + enabledFields[i]).closest('.form-group').show();
//    }
//}
function showHideFields(enabledFields) {
    // Hide all fields initially
    $('.row.form-group').hide();

    // Show only the enabled fields
    for (var i = 0; i < enabledFields.length; i++) {
        $('input[name="' + enabledFields[i] + '"]').closest('.form-group').show();
    }
}
function showmanagecertificatelist() {
    var table = $('#manageCertificates').DataTable({
        "destroy": true,
        "serverSide": true,
        "ajax": {
            "url": "/getmanagecertificatelist",
            "data": function (data) {
                data.page = data.start / data.length;
                data.length = $('[name=manageCertificates_length]').val();
                return "pageNo=" + data.page + "&pageSize=" + data.length;
            },
            "dataSrc": function (data) {
                var pageInfo = table.page.info();
                console.log("success" + JSON.stringify(data));
                console.log("xyz" + JSON.stringify(pageInfo));
                console.log(data.data);
                table.page.info().recordsTotal = data.recordsTotal;
                return data.data;
            }
        },
        "columns": [
            {
                "data": null,
                "title": "Sl No",
                "render": function (data, type, row, meta) {
                    var serialNumber = meta.row + meta.settings._iDisplayStart + 1;
                    return serialNumber;
                }
            },
            {
                "data": "createdDate",
                "title": "Application ID / Date"
            },
//            {
//                "data":[ "validityUnit","validityFrequencyId"],
//                "title": "Common Name / Validity"
//            },
            
            {
                "data": null,
                "title": " Validity / Common Name ",
                "render": function (data, type, row) {
                    return row.validityUnit + "  " + row.validityFrequencyId;
                }
            },
            {
                "data": "productname",
                "title": "Certificate"
            },
            {
                "data": "orderDetailsStatus",
                "title": "Status"
            },
            {
                "data": null,
                "title": "Expires",
                "render": function (data, type, row) {
                    return "-";
                }
            },
            {
                "data": "id",
                "title": "Action",
                "render": function (data, type, row) {
                    var status = row.status;
                    var actionButton = '';
                    if (status === 1) {
                        actionButton = '<a href="/getvieworderdetail/' + data + '" class="btn btn-primary"><i class="fa fa-wrench">Setup</i></a>';
                    }  else {
                        actionButton = '-';
                    }
                    return actionButton;
                }
            }
        ],
        "paging": true,
        "ordering": false,
        "sDom": "lrtip",
        "pagingType": "full_numbers",
        "lengthMenu": [10, 25, 50, 100],
        "pageLength": 10
    });

    $("#manageCertificates_length").hide();
}

// Event handler for page number click
$('#manageCertificates').on('page.dt', function () {
    var table = $('#manageCertificates').DataTable();
    var pageInfo = table.page.info();
    var pageNumber = pageInfo.page + 1;
    console.log("xyz" + JSON.stringify(pageInfo));
    // fetchRecords(pageNumber);
});



//function getvieworderdetails(orderId) {
// 
//    $.ajax({
//        url: '/getvieworderdetail/' + orderId,
//        type: 'GET',
//        success: function (data) {
//            // Handle the successful response here
//            console.log(data);
//        },
//        error: function (error) {
//            // Handle errors here
//            console.error('Error:', error);
//        }
//    });
//}

function getorderdetails(orderId){
	

$.ajax({
    type: "GET",
    url: "/getorderdetail/" + orderId,
    success: function (response) {
    	
    	var data = response.orderDetailsDto;
    	var applicationdata = response.applicationDto;
		var enabledfieldname=response.enabledFieldNames;
    var mandatoryFieldNames=response.mandatoryFieldNames;
    
    
    	$("#requestordetailsstatus").html(data.orderDetailsStatus);
    	
    	$("#certificatedetailstatus").html(data.orderDetailsStatus);
    	
		$('#type1').val(data.certificatefor);
		$('#reqmailid').val(data.customeremailId);
		$('#reqname').val(data.customeruserName);
		$('#phonenumber').val(data.customerphoneNumber);
		$('#id').val(data.id);
		
		
		
		$('#createddate').text(data.createdDate);
		$('#ovalidity').text(data.validityUnit + ' ' + data.validityFrequencyId);
		$('#requestStatus').html(data.orderDetailsStatus);

		
		
		$("#type1").prop("disabled", true);
		$("#reqmailid").prop("disabled", true);
		$("#phonenumber").prop("disabled", true);
		$("#reqname").prop("disabled", true);
		
		$("#applicationNumber").text(applicationdata.applicationNumber);
		
		
		//var fieldsToHide =enabledfieldname ;

	   
	    showHideFields(enabledfieldname);
		
		
        
        console.log(data);
    },
    error: function (error) {
        // Handle the error response (e.g., order not found)
        console.error("Error fetching order details:", error);
    }
});
}


function saveapplicationdetails(Id) {
    $.ajax({
        type: "POST", // Use "POST" instead of "Post" for the HTTP method
        url: "/saveapplicationdetails/" +Id,
        success: function (response) {
        	let data=response.application;
        	let applicationid = data.id;
        	let applicationNumber=data.applicationNumber;
        	$("#applicationNumber").text(applicationNumber);
        	$("#applicationid").val(id);
        	
        	$("#requestordetailsstatus").html("<span id=\"setupstatus\" class=\"label label-warning\" style=\"background-color: green; color: white; font-size: 12px;\">Completed</span>");

            // Corrected the spelling of 'alert' and added quotes around the message
         
            displayValidationMessage("Application saved successfully");
            $("#submitbutton").hide();
            // Update your HTML elements with the received data if needed
            // $('#requestordetailsstatus').text(data.orderDetailsStatus);
            // $('#type1').val(data.description);
            console.log(data);
        },
        error: function (error) {
            // Handle the error response (e.g., order not found)
            console.error("Error fetching order details:", error);
        }
    });
}
function getbirthcountrydropdown() {

	$.ajax({
		url : "/country", // The URL to your server endpoint
		method : "GET",
		dataType : "json",
		success : function(data) {
			// Assuming data is an array of objects with 'id' and 'name'
			// properties
			var dropdown = $("#countryofBirth");

			// Clear existing options
			dropdown.empty();

			// Add a default option (optional)
			dropdown.append($('<option>').text('Select'));

			// Populate the dropdown with options
			$.each(data, function(key, entry) {
				dropdown.append($('<option>').text(entry.countryName).val(
						entry.id));
			});
		},
		error : function(error) {
			console.error("Error fetching data: " + error);
		}
	});
}

//function getcountrydropdown() {
//    $.ajax({
//        url: "/country",
//        method: "GET",
//        dataType: "json",
//        success: function(data) {
//            var dropdown = $("#selectCountry");
//
//            dropdown.empty();
//            dropdown.append($('<option>').text('Select'));
//
//            $.each(data, function(key, entry) {
//                var option = $('<option>').text(entry.countryName).val(entry.id);
//                dropdown.append(option);
//            });
//
//            // Set the selected option based on applicationDto countryId
//            var selectedCountryId = ${applicationDto?.country};
//            if (selectedCountryId) {
//                dropdown.val(selectedCountryId);
//            }
//        },
//        error: function(error) {
//            console.error("Error fetching data: " + error);
//        }
//    });
//}

function getcountrydropdown() {

	$.ajax({
		url : "/country", // The URL to your server endpoint
		method : "GET",
		dataType : "json",
		success : function(data) {
			
			
			// Assuming data is an array of objects with 'id' and 'name'
			// properties
			var dropdown = $("#selectCountry");

			// Clear existing options
			dropdown.empty();

			// Add a default option (optional)
			dropdown.append($('<option>').text('Select'));

			// Populate the dropdown with options
			$.each(data, function(key, entry) {
				dropdown.append($('<option>').text(entry.countryName).val(
						entry.id));
			});
			
			 if (selectedCountry) {
		            dropdown.val(selectedCountry);
		        }
			
		    
		},
		error : function(error) {
			console.error("Error fetching data: " + error);
		}
	});
}

function getValidationDocuments(emailId, applicationId){
	
	$.ajax({
		url : "/getvalidationdocuments", 
		method : "POST",
	    data: {
            emailId: emailId,
            applicationId: applicationId
        },
		success : function(data) {
			
		},
		error : function(error) {
			console.error("Error fetching validationdocuments: " + error);
		}
	});
	
}

function AcceptEmail(applicationId, token, type){
	
	$.ajax({
		url : "/verificationemaillink", 
		method : "POST",
	    data: {
	    	token: token,
            applicationId: applicationId,
            type : type
            
        },
		success : function(response) {
			$("#emailacceptbutton, #emailrejectbutton, #emailtext").hide();
			if(response == true){
				$("#action-submit-email").html("Email verified successfully");
			}else if (response=="Unauthorized Access"){
				$("#action-submit-email").html("Unauthorized Access");
				
			}else{
				$("#action-submit-email").html("You are rejected Email verification");
			}
			
		},
		error : function(error) {
			console.error("Error While verifing EmailLink: " + error);
		}
	});
	
	
	
}

function ResendMail(emailId,applicationId,type){
	$.ajax({
	    type: "POST",
	    url: "/resendotp" ,
	    data: { emailId: emailId ,
	    	applicationId:applicationId,
	    	type:type},
	    success: function (response) {
	    	let isMailSent = response.isMailSent;
	    	if(isMailSent == 1){
	    		displayValidationMessage("Email sent successfully");
	    	}
	
	    },
	    error: function (error) {
	        console.error("Error While resending verificationOTP:", error);
	    }
	});
	
}

function displayValidationMessage(message) {
    $("#validationMessage").text(message);
    $("#validationModal").modal("show");
}


function VerifyOTP(applicationId) {
	
	let mailOtp = $("#emailmsgbox").val();
	if(mailOtp == "" || mailOtp.length == 0){
		
		displayValidationMessage("Please enter OTP");
	}
	else {
		
		$.ajax({
		    type: "POST",
		    url: "/verifyingotp" ,
		    data: { 
		    	applicationId:applicationId,
		    	mailOtp:mailOtp},
		    success: function (response) {
		    	if(response == false){
		    		displayValidationMessage("Please enter valid OTP");
		    	}
		    	else{
		    		$("#emailmsgbox, #verifyOTP, #resendOTP").hide();
		    		
		    		 $("#appenddata").html('<div class="col-md-7 col-sm-7" id="verified">' +
		    	                '<b>Verified</b>' +
		    	                '</div>' +
		    	                '</div>' +
		    	                '</div>' +
		    	                '</div>');
		    		   $("#verificationStatus")
		                .removeClass('bg-warning')
		                .addClass('bg-success')    
		                .text('Verification Completed');
		    	}
		    },
		    error: function (error) {
		        console.error("Error While verifing OTP:", error);
		    }
		});
		
	}
	
}





function UploadDocuments(applicationId, applicationNumber,type) {
    let formData = new FormData();
    let allowUpload = true; // Flag to track whether to allow the upload

    $('[id^=docupload]').each(function () {
        let containerId = $(this).attr('id');
        let numericId = containerId.replace('docupload', '');
        let numericIdInteger = parseInt(numericId, 10);
        let fileInput = $(this).find('input[type=file]')[0].files[0];

        if (fileInput) {
            if (!/^image\/.*|application\/pdf$/.test(fileInput.type)) {
                displayValidationMessage('Only image and PDF files are allowed.');
                allowUpload = false; // Set the flag to false to prevent further processing
                return false; // Stop the loop
            }
                    formData.append('documents', fileInput);
                    formData.append('numericIdInteger', numericIdInteger);
                 
        }
    });

        if (allowUpload) {
            formData.append('applicationId', applicationId);
            formData.append('applicationNumber', applicationNumber);

            $.ajax({
                type: 'POST',
                url: '/uploaddocuments',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                	 if (response.includes("Encrypted PDF files are not allowed")) {
                		 displayValidationMessage('Encrypted PDF files are not allowed.');
                	}
                	 else if (response.includes("File size cannot exceed 5 MB")) {
                		 displayValidationMessage('File size can not exceed 5 MB.');
                	}
                else {
                          let editButton = $('<button type="button" class="btn btn-default docmenteditbutton">Edit</button>');
                          $('.uploadType').html("");
                	$('.uploadType').hide();
                    $('.typeUpload').html("");
                	$('.typeUpload').hide();
                	$('#docsubmitbutton').hide();
                	$('.validationdocsubmitbutton').hide();
                	   $("#documentsubmitbutton").append(editButton);
                	   $("#commonsubmitandverify").prop("disabled", false);
                	   $('#validationDocumentsStatus').css('background-color', 'green').text("Completed");
                	   if(type === "1"){
                	   $(".fileActionsContainer").show();
                	   }else 
                		   $(".fileActionsContainer").hide();
                	}
                },
                error: function (error) {
                    console.error('Error Upload Documents:', error);
                }
            });
        }
    
}




function handleDownloadAndView(typeId, view) {
    let applicationId = $("#applicationid").val();
    let applicationNumber = $("#applicationNumber").text();

    $.ajax({
        type: 'GET',
        url: '/getdownloadandviewdocuments',
        data: {
            applicationNumber: applicationNumber,
            applicationId: applicationId,
            typeId: typeId
        },
        xhrFields: {
            responseType: 'blob'
        },
        success: function (response, status, xhr) {
            const filename = getFilenameFromHeader(xhr);
            const blob = new Blob([response], { type: response.type });

            if (view) {
                // Open the file in a new tab or window for viewing
                const fileURL = window.URL.createObjectURL(blob);
                window.open(fileURL, '_blank');
            } else {
                // Trigger the download
                const link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = filename || 'downloaded-file';
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
            }
        },
        error: function (error) {
            console.error('Error handling download or view:', error);
        }
    });
}

function getFilenameFromHeader(xhr) {
    const contentDispositionHeader = xhr.getResponseHeader('Content-Disposition');
    const contentDisposition = parseContentDisposition(contentDispositionHeader);

    return contentDisposition && contentDisposition.parameters.filename;
}

function parseContentDisposition(contentDisposition) {
    if (!contentDisposition) {
        return null;
    }

    const parts = contentDisposition.split(';').map(part => part.trim());
    const type = parts[0].toLowerCase();

    if (type !== 'attachment' && type !== 'inline') {
        return null;
    }

    const parameters = {};

    for (let i = 1; i < parts.length; i++) {
        const [key, value] = parts[i].split('=').map(part => part.trim());
        parameters[key] = value.replace(/["']/g, ''); // Remove quotes around filename
    }

    return {
        type,
        parameters,
    };
}


function submitForVerification(applicationId){
	
	
	 $.ajax({
         type: 'POST',
         url: '/submitForVerification?applicationId=' + applicationId,
         contentType: false,
         processData: false,
         success: function (response) {
        	 console.log(response);
        	 if(response == true){
        		 displayValidationMessage('DSC has been successfully submitted .');
        		 $("#commonsubmitandverify").hide();
        		 
        	 }
        	 else {
        		 $("#commonsubmitandverify").prop("disabled", true);
        	 }
        	 
        	 
         },
         error: function (error) {
             console.error('Error While submitForVerification:', error);
         }
	 });
	
	
}



function savecertificatedetails(applicationId){

 
	
	var formData = {
			commonName: $('#commonName').val(),
			  //selectCountry: $('#selectCountry').val(),
			  //selectState: $('#selectState').val(),
			  gender: $('input[name="gender"]:checked').val(), // Get the selected gender value
			 // masCountry: $('#selectCountry').val(), // Assuming the country field has an ID of 'selectCountry'
			  //state: $('#selectState').val(),
			state:$("select[name='state'] option:selected").text(),
			stateId:$("select[name='state'] option:selected").val(),
	country:$("select[name='masCountry'] option:selected").text(),
	countryId:$("select[name='masCountry'] option:selected").val(),
			  dateOfBirth: $('#dateOfBirth').val(),
			  emailId: $('#emailId').val(),
			  mobileNumber: $('#mobileNumber').val(),
			  address: $('#address').val(),
			  city: $('#city').val(),
			  postalCode: $('#postalCode').val(),
			  nationalId: $('#nationalId').val(),
			  organizationName: $('#organizationName').val(),
			  pan: $('#pan').val(),
			  gstin: $('#gstin').val(),
			  iecCode: $('#iecCode').val(),
			  countryofBirth:$("select[name='countryofBirth']").text(),
			  ouEmp: $('#ouEmp').val(),
			  ouStaticVal: $('#ouStaticVal').val(),
			  ouadditonalVal: $('#ouadditonalVal').val(),
			  organizationUnit: $('#organizationUnit').val(),
			  registredID: $('#registredid').val(),
			 // registredID: $('#registredid').val(),registredid
			  modeofPayment: $("select[name='modeofPayment']").val(),
			  nameoftheBank: $('#nameoftheBank').val(),
			  amount: $('#amount').val(),
			  remarks: $('#remarks').val(),
			  landlineNumber: $('#landlinenumber').val(),
			  applicationName: $('#applicationname').val(),
			  applicationURL: $('#applicationurl').val(),
			  iPaddress: $('#ipaddress').val(),
			  additionalFields: []

			};
	 $('[id^="field_"]').each(function() {
         var fieldName = $(this).attr('name').substring(6); // Extract field name from 'field_' + fieldName
 
         var fieldData = {
             fieldname: fieldName,
             value: $(this).val(),
             oid: $(this).data('oid'),
             ismandatory: $(this).data('ismandatory'),// Get the tag name (e.g., 'INPUT', 'SELECT')
             alphabetsvalidation: $(this).data('alphabets-validation'),
             numberscalidation: $(this).data('numbers-validation'),
             specialcharactersValidation: $(this).data('special-characters-validation')
         };
 
         formData.additionalFields.push(fieldData);
        
 
     });
	  formData.additionalFields = JSON.stringify(formData.additionalFields);
	  $.ajax({
          type: 'POST',
          url: "/savecertificatedetails/" +applicationId, // Update with your actual URL
          data: JSON.stringify(formData),
          contentType: 'application/json',
          dataType: 'json',
          success: function (data) {
        	  displayValidationMessage("certificatedetails details saved successfully");        	  
        		$("#certificateform :input:not(#editcertificatesubmitbutton):not(#updatecertificatesubmitbutton):not(#cancelcertificatesubmitbutton)").prop("disabled", true);
        		$("#certificatesubmitbutton").hide();
        		$('#certificatedetailsstatus').css('background-color', 'green').text("Completed");
        		$('#asetupDocuments ').removeClass('panel-disabled').addClass('accordion3');
        		
        		
              // Handle success (e.g., show a success message)
              console.log('Form submitted successfully');
          },
          error: function (xhr, status, error) {
              // Handle errors (e.g., display an error message)
              console.error('Error submitting form:', error);
          }
      });
 
	
}





function updatecertificatedetails(applicationId){
	

	
	
	var formData = {
			commonName: $('#commonName').val(),
			  //selectCountry: $('#selectCountry').val(),
			  //selectState: $('#selectState').val(),
			  gender: $('input[name="gender"]:checked').val(), // Get the selected gender value
			 // masCountry: $('#selectCountry').val(), // Assuming the country field has an ID of 'selectCountry'
			  //state: $('#selectState').val(),
			state:$("select[name='state'] option:selected").text(),
			stateId:$("select[name='state'] option:selected").val(),
	country:$("select[name='masCountry'] option:selected").text(),
	countryId:$("select[name='masCountry'] option:selected").val(),
			  dateOfBirth: $('#dateOfBirth').val(),
			  emailId: $('#emailId').val(),
			  mobileNumber: $('#mobileNumber').val(),
			  address: $('#address').val(),
			  city: $('#city').val(),
			  postalCode: $('#postalCode').val(),
			  nationalId: $('#nationalId').val(),
			  organizationName: $('#organizationName').val(),
			  pan: $('#pan').val(),
			  gstin: $('#gstin').val(),
			  iecCode: $('#iecCode').val(),
			  countryofBirth:$("select[name='countryofBirth']").text(),
			  ouEmp: $('#ouEmp').val(),
			  ouStaticVal: $('#ouStaticVal').val(),
			  ouadditonalVal: $('#ouadditonalVal').val(),
			  organizationUnit: $('#organizationUnit').val(),
			  registredID: $('#registredid').val(),
			  modeofPayment: $("select[name='modeofPayment']").val(),
			  nameoftheBank: $('#nameoftheBank').val(),
			  amount: $('#amount').val(),
			  remarks: $('#remarks').val(),
			  landlineNumber: $('#landlinenumber').val(),
			  applicationName: $('#applicationname').val(),
			  applicationURL: $('#applicationurl').val(),
			  iPaddress: $('#ipaddress').val(),
			  additionalFields: [],
//	additionalFieldsvalue:[]
			};
	
	 $('[id^="field_"]').each(function() {
         var fieldName = $(this).attr('name').substring(6); // Extract field name from 'field_' + fieldName

         var fieldData = {
             fieldname: fieldName,
             value: $(this).val(),
            
             oid: $(this).data('oid'),
             ismandatory: $(this).data('ismandatory'),// Get the tag name (e.g., 'INPUT', 'SELECT')
             alphabetsvalidation: $(this).data('alphabets-validation'),
             numberscalidation: $(this).data('numbers-validation'),
             specialcharactersValidation: $(this).data('special-characters-validation')
         };

         formData.additionalFields.push(fieldData);
//         formData.additionalFieldsvalue.push(fieldData);
     });
	 
	  formData.additionalFields = JSON.stringify(formData.additionalFields);
	  
	
	  $.ajax({
          type: 'POST',
          url: "/savecertificatedetails/" +applicationId, // Update with your actual URL
          data: JSON.stringify(formData),
          contentType: 'application/json',
          success: function (data) {
        	  displayValidationMessage("certificatedetails details saved successfully");        	  
        		$("#certificateform :input:not(#editcertificatesubmitbutton):not(#updatecertificatesubmitbutton):not(#cancelcertificatesubmitbutton)").prop("disabled", true);
        		$("#cancelcertificatesubmitbutton").hide();
        		$("#updatecertificatesubmitbutton").hide();
        		$("#editcertificatesubmitbutton").show();
              // Handle success (e.g., show a success message)
             // console.log('Form submitted successfully');
          },
          error: function (xhr, status, error) {
              // Handle errors (e.g., display an error message)
              console.error('Error submitting form:', error);
          }
      });

	
}

function validatecustomfiledForm() {
    // Reset any previous error messages
    $('#error-messages').empty();
 
    var selectedcommonName = $('#commonName').val()==undefined?'': $('#commonName').val();
    var maleMandatoryValue = $('#male').data('maleismandatory');
    var femaleMandatoryValue = $('#female').data('femaleismandatory');
    var countryMandatoryValue = $('#selectCountry').data('countryismandatory');
    var selectedCountry = $('#selectCountry').val()==undefined?'': $('#selectCountry').val()
    var stateMandatoryValue = $('#selectState').data('stateismandatory');
    var selectedState = $('#selectState').val()==undefined?'': $('#selectState').val()
    var dateOfBirthMandatoryValue = $('#dateOfBirth').data('dateofbirthismandatory');
    var selectedDateOfBirth = $('#dateOfBirth').val()==undefined?'': $('#dateOfBirth').val()
    var emailIdMandatoryValue = $('#emailId').data('emailidismandatory');
    var selectedemailId = $('#emailId').val()==undefined?'': $('#emailId').val()
    var mobileNumberMandatoryValue = $('#mobileNumber').data('mobilenumberismandatory');
    var selectedmobileNumber = $('#mobileNumber').val()==undefined?'': $('#mobileNumber').val();
    var addressismandatory = $('#address').data('addressismandatory');
    var selectedaddress = $('#address').val()==undefined?'': $('#address').val();
    var cityisMandatoryValue = $('#city').data('cityismandatory');
    var selectedcity = $('#city').val()==undefined?'': $('#city').val();
    var postalCodeMandatoryValue = $('#postalCode').data('postcodeismandatory');
    var selectedpostalCode = $('#postalCode').val()==undefined?'': $('#postalCode').val()
    var nationalIdMandatoryValue = $('#nationalId').data('nationalidnumberismandatory');
    var selectednationalId = $('#nationalId').val()==undefined?'': $('#nationalId').val();
    var organizationNameMandatoryValue = $('#organizationName').data('organizationismandatory');
    var selectednorganizationName = $('#organizationName').val()==undefined?'': $('#organizationName').val();
    var panMandatoryValue = $('#pan').data('taxidnumberismandatory');
    var selectedpan = $('#pan').val()==undefined?'': $('#pan').val();
    var gstinMandatoryValue = $('#gstin').data('organizationidentifierismandatory');
    var selectedgstin = $('#gstin').val()==undefined?'': $('#gstin').val();
    var iecCodeMandatoryValue = $('#iecCode').data('cinismandatory');
    var selectediecCode = $('#iecCode').val()==undefined?'': $('#iecCode').val();
    var ouEmpMandatoryValue = $('#ouEmp').data('ouempismandatory');
    var selectedouEmp = $('#ouEmp').val()==undefined?'': $('#ouEmp').val();
    var ouStaticValMandatoryValue = $('#ouStaticVal').data('oustaticvalismandatory');
    var selectedouStaticVal = $('#ouStaticVal').val()==undefined?'': $('#ouStaticVal').val();
    var ouadditonalValMandatoryValue = $('#ouadditonalVal').data('ouadditonalvalismandatory');
    var selectedouadditonalVal = $('#ouadditonalVal').val()==undefined?'': $('#ouadditonalVal').val();
    var organizationUnitMandatoryValue = $('#organizationUnit').data('organizationunitismandatory');
    var selectedorganizationUnit = $('#organizationUnit').val()==undefined?'': $('#organizationUnit').val();
    var registredIDMandatoryValue = $('#registredid').data('serialnumberismandatory');
    var selectedregistredID = $('#registredid').val()==undefined?'': $('#registredid').val();
    var selectedmodeofPayment = $('#modeofPayment').val()==undefined?'': $('#modeofPayment').val();
    var selectednameoftheBank = $('#nameoftheBank').val()==undefined?'': $('#nameoftheBank').val();
    var selectedamount = $('#amount').val()==undefined?'': $('#amount').val();
    var landlineNumberMandatoryValue = $('#landlinenumber').data('landlineismandatory');
    var selectedlandlineNumber = $('#landlinenumber').val()==undefined?'': $('#landlinenumber').val();
    var applicationnameMandatoryValue = $('#applicationname').data('applicationismandatory');
    var selectedapplicationName = $('#applicationname').val()==undefined?'': $('#applicationname').val();
    var applicationurlMandatoryValue = $('#applicationurl').data('applicationurlmandatory');
    var selectedapplicationURL = $('#applicationurl').val()==undefined?'': $('#applicationurl').val();
    var ipaddressMandatoryValue = $('#ipaddress').data('ipaddressismandatory');
    var selectediPaddress = $('#ipaddress').val()==undefined?'': $('#ipaddress').val();
    var ipPattern = /^(\d{1,3}\.){3}\d{1,3}$/;
   // var urlRegex = /^(ftp|http|https):\/\/[^ "]+$/;
    var urlPattern = /^(https?:\/\/)?([\w.-]+\.[a-z]{2,})(\/\S*)?$/;
 
    var alphanumericRegex = /^[a-zA-Z0-9\s]+$/;
    var numericRegex = /^[0-9]+$/;
    var alphaRegex = /^[a-zA-Z\s]+$/;
    var alphaWithSpecialCharRegex = /^[a-zA-Z\s\-_.,!@#$%^&*()]+$/;
    var errorMessages = [];
 
    // CommonName validation
    if (selectedcommonName.trim() === '') {
        errorMessages.push('CommonName is required.');
        displayErrorModal(errorMessages);
      return false;
    } else if (!alphaRegex.test(selectedcommonName)) {
        errorMessages.push('CommonName must be alphabetical characters only.');
        displayErrorModal(errorMessages);
      return false;
    }
 
    // Gender validation
    if ((maleMandatoryValue === 1 || femaleMandatoryValue === 1) && !$('input[name="gender"]:checked').val()) {
        errorMessages.push('Gender is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Country validation
    if (countryMandatoryValue === 1 && selectedCountry === '0') {
        errorMessages.push('Country is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // State validation
    if (stateMandatoryValue === 1 && selectedState === '0') {
        errorMessages.push('State is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Date of Birth validation
    if (dateOfBirthMandatoryValue === 1 && selectedDateOfBirth === '') {
        errorMessages.push('Date of Birth is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Email validation
    if (emailIdMandatoryValue === 1 && selectedemailId.trim() === '') {
        errorMessages.push('EmailId is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Mobile Number validation
    if (mobileNumberMandatoryValue === 1 && selectedmobileNumber.trim() === '') {
        errorMessages.push('MobileNumber is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectedmobileNumber !== null && selectedmobileNumber.trim() !== '') {
        if (!numericRegex.test(selectedmobileNumber)||(selectedmobileNumber.length !== 10)) {
            errorMessages.push('Enter valid MobileNumber Number.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
 
    // Address validation
    if (addressismandatory === 1 && selectedaddress.trim() === '') {
        errorMessages.push('Address is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Address alphanumeric validation
    if (selectedaddress !== null && selectedaddress.trim() !== '') {
        if (!alphanumericRegex.test(selectedaddress)) {
            errorMessages.push('Enter valid Address.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // City validation
    if (cityisMandatoryValue === 1 && selectedcity.trim() === '') {
        errorMessages.push('City is required.');
    }
 
    // City alphabetical validation
    if (selectedcity !== null && selectedcity.trim() !== '') {
        if (!alphaRegex.test(selectedcity)) {
            errorMessages.push('Enter valid City .');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // Postal Code validation
    if (postalCodeMandatoryValue === 1 && selectedpostalCode.trim() === '') {
        errorMessages.push('PostalCode is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Postal Code numeric validation
    if (selectedpostalCode !== null && selectedpostalCode.trim() !== '') {
        if (!numericRegex.test(selectedpostalCode)||(selectedpostalCode.length !== 6)) {
            errorMessages.push('Enter valid  PostalCode .');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // National ID validation
    if (nationalIdMandatoryValue === 1 && selectednationalId.trim() === '') {
        errorMessages.push('NationalId is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // National ID numeric validation
    if (selectednationalId !== null && selectednationalId.trim() !== '') {
        if (!numericRegex.test(selectednationalId)||(selectednationalId.length !== 14)) {
            errorMessages.push('Enter valid NationalId .');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // Organization Name validation
    if (organizationNameMandatoryValue === 1 && selectednorganizationName.trim() === '') {
        errorMessages.push('OrganizationName is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Organization Name alphabetical validation
    if (selectednorganizationName !== null && selectednorganizationName.trim() !== '') {
        if (!alphaRegex.test(selectednorganizationName)) {
            errorMessages.push('Enter valid OrganizationName.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // PAN validation
    if (panMandatoryValue === 1 && selectedpan.trim() === '') {
        errorMessages.push('Tax ID Number is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // PAN alphanumeric validation
    if (selectedpan !== null && selectedpan.trim() !== '') {
        if (!alphanumericRegex.test(selectedpan) || (selectedpan.length !== 16)) {
            errorMessages.push('Enter valid Tax ID Number .');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // GSTIN validation
    if (gstinMandatoryValue === 1 && selectedgstin.trim() === '') {
        errorMessages.push('Organization Identifier  is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectedgstin !== null && selectedgstin.trim() !== '') {
        if (!alphanumericRegex.test(selectedgstin) || (selectedgstin.length !== 15)) {
            errorMessages.push('Enter valid Organization Identifier  .');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // IEC Code validation
    if (iecCodeMandatoryValue === 1 && selectediecCode.trim() === '') {
        errorMessages.push('CIN is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectediecCode !== null && selectediecCode.trim() !== '') {
        if (!alphanumericRegex.test(selectediecCode) || (selectediecCode.length !== 21)) {
            errorMessages.push('Enter valid CIN .');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // OU Emp validation
    if (ouEmpMandatoryValue === 1 && selectedouEmp.trim() === '') {
        errorMessages.push('OU Emp is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
     //OU Emp alphanumeric validation
    if (selectedouEmp !== null && selectedouEmp.trim() !== '') {
        if (!alphanumericRegex.test(selectedouEmp)) {
            errorMessages.push('OU Emp must be Alphanumeric.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // OU Static Val validation
    if (ouStaticValMandatoryValue === 1 && selectedouStaticVal.trim() === '') {
        errorMessages.push('OU Static Val is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // OU Static Val alphanumeric validation
    if (selectedouStaticVal !== null && selectedouStaticVal.trim() !== '') {
        if (!alphanumericRegex.test(selectedouStaticVal)) {
            errorMessages.push('OU Static Val must be Alphanumeric.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // OU Additional Val validation
    if (ouadditonalValMandatoryValue === 1 && selectedouadditonalVal.trim() === '') {
        errorMessages.push('OU Additional Val is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // OU Additional Val alphanumeric validation
    if (selectedouadditonalVal !== null && selectedouadditonalVal.trim() !== '') {
        if (!alphanumericRegex.test(selectedouadditonalVal)) {
            errorMessages.push('OU Additional Val must be Alphanumeric.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // Organization Unit validation
    if (organizationUnitMandatoryValue === 1 && selectedorganizationUnit.trim() === '') {
        errorMessages.push('Organization Unit is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Registered ID validation
    if (registredIDMandatoryValue === 1 && selectedregistredID.trim() === '') {
        errorMessages.push('Serial Number is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectedregistredID !== null && selectedregistredID.trim() !== '') {
        if (!alphanumericRegex.test(selectedregistredID)) {
            errorMessages.push('Enter valid Serial Number  .');
            displayErrorModal(errorMessages);
            return false;
        }
    }

 
    // Landline Number validation
    if (landlineNumberMandatoryValue ==1 && selectedlandlineNumber.trim() === '') {
        errorMessages.push('Landline Number is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectedlandlineNumber !== null && selectedlandlineNumber.trim() !== '') {
        if (!numericRegex.test(selectedlandlineNumber)|| (selectedlandlineNumber.length !== 11)) {
            errorMessages.push('Enter valid Landline Number.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // Application Name validation
    if (applicationnameMandatoryValue ==1 && selectedapplicationName.trim() === '') {
        errorMessages.push('Application Name is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectedapplicationName !== null && selectedapplicationName.trim() !== '') {
        if (!alphaRegex.test(selectedapplicationName)) {
            errorMessages.push('Enter valid Application Name.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // Application URL validation
    if (applicationurlMandatoryValue == 1 && selectedapplicationURL.trim() === '') {
        errorMessages.push('Application URL is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectedapplicationURL !== null && selectedapplicationURL.trim() !== '') {
        if (!urlPattern.test(selectedapplicationURL)) {
            errorMessages.push('Enter valid Application URL.');
            displayErrorModal(errorMessages);
            return false;
        }
    }

    if (ipaddressMandatoryValue == 1 && selectediPaddress.trim() === '') {
        errorMessages.push('IP address  is required.');
        displayErrorModal(errorMessages);
        return false;
    }
    if (selectediPaddress !== null && selectediPaddress.trim() !== '') {
        if (!ipPattern.test(selectediPaddress)) {
            errorMessages.push('Enter valid IP address.');
            displayErrorModal(errorMessages);
            return false;
        }
    }
 
    // Mode of Payment validation
    if (selectedmodeofPayment === '0') {
        errorMessages.push('Mode of Payment is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Name of the Bank validation
    if (selectednameoftheBank.trim() === '') {
        errorMessages.push('Name of the Bank is required.');
        displayErrorModal(errorMessages);
        return false;
    }
 
    // Amount validation
    if (selectedamount.trim() === '') {
        errorMessages.push('Amount is required.');
    }
 
 
 
    return true; // Return true to indicate successful validation
}

function replaceUndefinedWithNull(obj) {
	  for (const key in obj) {
	    if (obj.hasOwnProperty(key) && obj[key] === undefined) {
	      obj[key] = null;
	    }
	  }
	}




function displayErrorModal(messages) {
    // Clear any previous error messages
    $('#error-messages').empty();

    // Add each error message to the list
    messages.forEach(function (message) {
        $('#error-messages').append('<li>' + message + '</li>');
    });

    // Show the modal
    $('#customfieldvalidationModal').modal('show');
}


function uploadFile(fileInput) {
	 var file = fileInput.files[0];

    // Use FormData to send the file via AJAX
    var formData = new FormData();
    formData.append("file", file);
    // Perform AJAX request
    $.ajax({
        type: "POST",
        url: "/viewuploadcsr",
        data: formData,
        contentType: false,
        processData: false,
        success: function (response) {
            // Update the textarea with the received content
            $("#encodedData").val(response);
        },
        error: function (xhr, status, error) {
            // Handle error
            console.error("Invalid CSR file:", error);
            alert("Invalid CSR file");
        }
    });
}

function insertNewLines(str, every) {
    var result = '';
    for (var i = 0; i < str.length; i += every) {
        result += str.substr(i, every) + '\n';
    }
    return result;
}

function isValidFileType(file) {
    var allowedExtensions = ['jpg', 'jpeg', 'pdf', 'csr'];
    var fileName = file.name;
    var fileExtension = fileName.split('.').pop().toLowerCase();
    return allowedExtensions.includes(fileExtension);
}


function saveCsrContent(applicationId) {
	
	   var fileInput = document.getElementById('fileInput');
	    var file = fileInput.files[0];

	    var formData = new FormData();
	    formData.append('file', file);

	    $.ajax({
	        type: 'POST',
	        url:  "/savecsrcontent/" +applicationId,
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function(response) {
	            $('#response').html(response);
	            alert("csr  saved successfully");
	            $("#validateCSR").hide();
	             $("#uploadCSR").show();
	             $("#viewCsrButton").show();
	         	$("#editrcsrButton").show();
	           	$("#saveCsrButton").hide();
	          	$("#validateButton").hide();
	          	$('#uploadCsrStatus').css('background-color', 'green').text("Completed");
	          	
	        },
	        error: function(error) {
	            console.error('Error uploading file:', error);
	            $('#response').html('Error uploading file.');
	        }
	    });
	
//	var formData={
//		  csr:$("#encodedData").val(),
//	};
//   
//
//    // Perform AJAX request to save CSR content
//    $.ajax({
//        type: "POST",
//        url: "/savecsrcontent/" +applicationId,
//        data: JSON.stringify(formData),
//      contentType: 'application/json',
//        success: function (data) {
//        	alert("csr  saved successfully");
//        	uploadFileintoLocalDir();
//            //alert(csr upload successfully);
//        },
//        error: function (xhr, status, error) {
//            console.error("Error saving CSR content:", error);
//            alert("Error saving CSR content");
//        }
//    });
}

function uploadFileintoLocalDir() {
    var fileInput = document.getElementById('fileInput');
    var file = fileInput.files[0];

    var formData = new FormData();
    formData.append('file', file);

    $.ajax({
        type: 'POST',
        url: '/uploadfileintolocaldir',
        data: formData,
        processData: false,
        contentType: false,
        success: function(response) {
            $('#response').html(response);
        },
        error: function(error) {
            console.error('Error uploading file:', error);
            $('#response').html('Error uploading file.');
        }
    });
}

//function savecsrdetails(applicationId){
//	
//
//	
//	
//	var formData = {
//			csr: $('#encodedData').val(),
//			  
//			};
//	
//	 
//	 
//
//	  $.ajax({
//          type: 'POST',
//          url: "/savecsrdetails/" +applicationId, 
//          data: JSON.stringify(formData),
//          contentType: 'application/json',
//          success: function (data) {
//        	  
//        	  
//              // Handle success (e.g., show a success message)
//        	  alert('upload csr submitted successfully');
//          },
//          error: function (xhr, status, error) {
//              // Handle errors (e.g., display an error message)
//              console.error('Error submitting form:', error);
//          }
//      });
//
//	
//}


function validatecsr(){
	  var fileInput = $("#fileInput")[0].files[0]; // Assuming you have an input with id="fileInput"
      var formData = new FormData();
      formData.append("file", fileInput);

      $.ajax({
          type: "POST",
          url: "/validateupload",
          data: formData,
          processData: false,
          contentType: false,
          success: function (response) {
             // $("#resultContainer").html(response);
              
              var formattedContent = formatCsrDetails(response);
              $("#csrDetailsModalContent").html(formattedContent);
              $("#viewCsrDetailsModalContent").html(formattedContent);
              
             $("#validateCSR").show();
             $("#saveCsrButton").show();
             
             $("#uploadCSR").hide();
          },
          error: function (error) {
              console.log("Error:", error);
              $("#resultContainer").html("Error occurred while validating the file.");
          }
      });
}


function formatCsrDetails(details) {
	var htmlResponse = "<div class='row'><div class='col-md-3 form-label'>CN</div><div class='col-md-9'>" + details.CN + "</div></div>";

     htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Key Size</div><div class='col-md-9'>" + details.keySize + " " + "(bits)" + "<span style='padding-left:5px;color:red;'></span></div></div>";
    
    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Key Algorithm</div><div class='col-md-9'>" + details.keyAlgorithm + "</div></div>";
    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Signature Algorithm</div><div class='col-md-9'>" + details.signatureAlgorithm + "</div></div>";
    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Finger Print (MD5)</div><div class='col-md-9 data-wrap'>" + details.fingerPrint_MD5 + "</div></div>";
    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Finger Print (SHA-1)</div><div class='col-md-9 data-wrap'>" + details.fingerPrint_SHA1 + "</div></div>";
    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Finger Print (SHA-256)</div><div class='col-md-9 data-wrap'>" + details.fingerPrint_SHA256 + "</div></div>";
    htmlResponse += "</div>";

    if (details.isView === "setup") {
        if (details.type === "1") {
            htmlResponse += "<div class='form-group'><b>Public Key</b><div><textarea disabled cols='95' rows='20' class='textarea-control'>" + details.publicKeyPEM + "</textarea></div>";
            htmlResponse += "<div class=\"form-buttons\" id=\"csrsubmitblock\">\n"
                    + "<span id=\"csrfillLater\"><input type=\"button\" class=\"btn btn-default\" value=\"Fill Later\" onclick=\"filllater('" + details.orderproductslno + "','csrfillLater');\" /></span>\n"
                    + "<span id=\"csrcontinue\"><input type=\"button\" class=\"btn btn-default\" value=\"Proceed\" id=\"csrconfirmationyes\" onclick=\"submitcsr('" + details.orderproductslno + "','1');\" /></span>\n"
                    + "</div>\n";
        } else {
            htmlResponse += "<div class='form-group'><b>Public Key</b><div><textarea disabled  style='height: 200px;' cols='60' rows='20' class='form-control textarea-control'>" + details.publicKeyPEM + "</textarea></div>";
        }
    } else {
        htmlResponse += "<div class='form-group'><b>Public Key</b><div><textarea disabled style='height: 200px;' cols='105' rows='20' class='form-control textarea-control'>" + details.publicKeyPEM + "</textarea></div>";
    }



    return htmlResponse;
}

function editCsr(entityId){
	
	
	  $.ajax({
          type: "PATCH",
          url: "/editcsr/" + entityId,
          success: function (data) {
              //alert("CSR deleted successfully");
          },
          error: function (error) {
              //alert("Error deleting CSR");
          }
      });
}

//function formatCsrDetails(details) {
//   //htmlResponse = "<div class='row'><div class='col-md-3 form-label'>Key Size</div><div class='col-md-9'>" + details.keySize + "</div></div>";
//   var htmlResponse += "<div class='row'><div class='col-md-3 form-label'>" + value[0] + "</div><div class='col-md-9'>" + value[1] + "</div></div>";
//
//     htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Key Size</div><div class='col-md-9'>" + details.keySize + " " + "(bits)" + "<span style='padding-left:5px;color:red;'></span></div></div>";
//    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Key Algorithm</div><div class='col-md-9'>" + details.keyAlgorithm + "</div></div>";
//    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Signature Algorithm</div><div class='col-md-9'>" + details.signatureAlgorithm + "</div></div>";
//    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Finger Print (MD5)</div><div class='col-md-9 data-wrap'>" + details.fingerPrint_MD5 + "</div></div>";
//    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Finger Print (SHA-1)</div><div class='col-md-9 data-wrap'>" + details.fingerPrint_SHA1 + "</div></div>";
//    htmlResponse += "<div class='row'><div class='col-md-3 form-label'>Finger Print (SHA-256)</div><div class='col-md-9 data-wrap'>" + details.fingerPrint_SHA256 + "</div></div>";
//    htmlResponse += "</div>";
//
//    if (details.isView === "setup") {
//        if (details.type === "1") {
//            htmlResponse += "<div class='form-group'><b>Public Key</b><div><textarea disabled cols='95' rows='20' class='textarea-control'>" + details.publicKeyPEM + "</textarea></div>";
//            htmlResponse += "<div class=\"form-buttons\" id=\"csrsubmitblock\">\n"
//                    + "<span id=\"csrfillLater\"><input type=\"button\" class=\"btn btn-default\" value=\"Fill Later\" onclick=\"filllater('" + details.orderproductslno + "','csrfillLater');\" /></span>\n"
//                    + "<span id=\"csrcontinue\"><input type=\"button\" class=\"btn btn-default\" value=\"Proceed\" id=\"csrconfirmationyes\" onclick=\"submitcsr('" + details.orderproductslno + "','1');\" /></span>\n"
//                    + "</div>\n";
//        } else {
//            htmlResponse += "<div class='form-group'><b>Public Key</b><div><textarea disabled  style='height: 200px;' cols='60' rows='20' class='form-control textarea-control'>" + details.publicKeyPEM + "</textarea></div>";
//        }
//    } else {
//        htmlResponse += "<div class='from-group'><b>Public Key</b><div><textarea disabled style='height: 200px;' cols='105' rows='20' class='form-control textarea-control'>" + details.publicKeyPEM + "</textarea></div>";
//    }
//    
//
//
//
//    return htmlResponse;
//}
