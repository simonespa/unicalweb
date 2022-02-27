function modificaProfilo() {
	var password = document.modifica.newPassword.value;
	var confermaPassword = document.modifica.renewPassword.value;
	if( password != confermaPassword ) {
		alert("La nuova password non coincide con la conferma");
		return false;
	}
	return true;
}