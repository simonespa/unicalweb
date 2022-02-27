function login1() {
	var user = document.docente.user.value;
	var password = document.docente.password.value;
	if( user == "" && password == "" ) {
		alert("I campi sono obbligatori");
		return false;
	} else if( user == "" ) {
		alert("L'username e' obbligatoria");
		return false;
	} else if( password == "" ) {
		alert("La password e' obbligatoria");
		return false;
	}
	return true;
}
		
function login2() {
	var user = document.gruppo.user.value;
	var password = document.gruppo.password.value;
	if( user == "" && password == "" ) {
		alert("I campi sono obbligatori");
		return false;
	} else if( user == "" ) {
		alert("L'username e' obbligatoria");
		return false;
	} else if( password == "" ) {
		alert("La password e' obbligatoria");
		return false;
	}
	return true;
}