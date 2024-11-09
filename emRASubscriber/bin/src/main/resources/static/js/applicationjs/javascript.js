
function emailvalidation(emailid)
{
    var emailExp = /^[A-Z0-9\._%-]+@[A-Z0-9\.-]+\.[A-Z]{2,4}(?:(?:[,;][A-Z0-9\._%-]+@[A-Z0-9\.-]+))*$/i;
    var emails = emailid.replace(/[,\s]*,[,\s]*/g, ",").replace(/^,/, "").replace(/,$/, "");
    if (emails.match(emailExp)) {
        return true;
    } else {
        return false;
    }
}



function validateOrgName(string)
{

    var addressExp = /^[a-zA-Z0-9]+( [a-zA-Z0-9-.&()\/]+)*$/;

    var address = string;
    if (address.match(addressExp)) {
        return true;
    } else {
        return false;
    }
}

function validateChecklistName(alphabets) {
	var regex = /^[a-zA-Z_]+$/;
    var valid = regex.test(alphabets);
    if (valid)
        return true;
    else
        return false;
}


