function validate() {
	var voto = document.modifica.voto.value;
	if( voto == null ) {
		alert("Il voto non può essere vuoto");
		return false;
	}
	if( voto < 0 || voto > 30 ) {
		alert("Voto non valido");
		return false;
	}
	return true;
}