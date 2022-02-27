var messaggio = "";

function almenoUnoStudente() {
	var matricola1 = document.registrazione.matricola1.value;
	var nome1 = document.registrazione.nome1.value;
	var cognome1 = document.registrazione.cognome1.value;
	var email1 = document.registrazione.email1.value;
	
	var matricola2 = document.registrazione.matricola2.value;
	var nome2 = document.registrazione.nome2.value;
	var cognome2 = document.registrazione.cognome2.value;
	var email2 = document.registrazione.email2.value;
	
	var matricola3 = document.registrazione.matricola3.value;
	var nome3 = document.registrazione.nome3.value;
	var cognome3 = document.registrazione.cognome3.value;
	var email3 = document.registrazione.email3.value;
	
	var matricola4 = document.registrazione.matricola4.value;
	var nome4 = document.registrazione.nome4.value;
	var cognome4 = document.registrazione.cognome4.value;
	var email4 = document.registrazione.email4.value;	
	
	if( matricola1 == "" && nome1 == "" && cognome1 == "" && email1 == "" && matricola2 == "" && nome2 == "" && cognome2 == "" && email2 == "" &&
	    matricola3 == "" && nome3 == "" && cognome3 == "" && email3 == "" && matricola4 == "" && nome4 == "" && cognome4 == "" && email4 == "" ) {
		return false;
	}
	return true;
}

function checkStudente1() {
	var check = false;
	var matricola = document.registrazione.matricola1.value;
	var nome = document.registrazione.nome1.value;
	var cognome = document.registrazione.cognome1.value;
	var email = document.registrazione.email1.value;
	// controlla che i campi siano o tutti compilati o tutti vuoti
	if( matricola != "" || nome != "" || cognome != "" ) {
		// se non sono tutti e tre compilati entra nella condizione
		messaggio += "\nStudente 1:\n";
		if( matricola == "" ) {
			check = true;
			messaggio += "il campo matricola e' vuoto\n";
		} else if( isNaN(matricola) ) {
			check = true;
			messaggio += "non ha una matricola valida\n";
		}
		if( nome == "" ) {
			check = true;
			messaggio += "il campo nome e' vuoto\n";
		}
		if( cognome == "" ) {
			check = true;
			messaggio += "il campo cognome e' vuoto\n";
		}
		if( email == "" ) {
			check = true;
			messaggio += "il campo email e' vuoto\n";
		}
		if( !check ) {
			messaggio = "";
		}
	}
}

function checkStudente2() {
	var check = false;
	var matricola = document.registrazione.matricola2.value;
	var nome = document.registrazione.nome2.value;
	var cognome = document.registrazione.cognome2.value;
	var email = document.registrazione.email2.value;
	if( matricola != "" || nome != "" || cognome != "" ) {
		// se non sono tutti e tre compilati entra nella condizione
		messaggio += "\nStudente 2:\n";
		if( matricola == "" ) {
			check = true;
			messaggio += "il campo matricola e' vuoto\n";
		} else if( isNaN(matricola) ) {
			check = true;
			messaggio += "non ha una matricola valida\n";
		}
		if( nome == "" ) {
			check = true;
			messaggio += "il campo nome e' vuoto\n";
		}
		if( cognome == "" ) {
			check = true;
			messaggio += "il campo cognome e' vuoto\n";
		}
		if( email == "" ) {
			check = true;
			messaggio += "il campo email e' vuoto\n";
		}
		if( !check ) {
			messaggio = messaggio.replace("\nStudente 2:\n", "");
		}
	}
}

function checkStudente3() {
	var check = false;
	var matricola = document.registrazione.matricola3.value;
	var nome = document.registrazione.nome3.value;
	var cognome = document.registrazione.cognome3.value;
	var email = document.registrazione.email3.value;
	// controlla che i campi siano o tutti compilati o tutti vuoti
	if( matricola != "" || nome != "" || cognome != "" ) {
		// se non sono tutti e tre compilati entra nella condizione
		messaggio += "\nStudente 3:\n";
		if( matricola == "" ) {
			check = true;
			messaggio += "il campo matricola e' vuoto\n";
		} else if( isNaN(matricola) ) {
			check = true;
			messaggio += "non ha una matricola valida\n";
		}
		if( nome == "" ) {
			check = true;
			messaggio += "il campo nome e' vuoto\n";
		}
		if( cognome == "" ) {
			check = true;
			messaggio += "il campo cognome e' vuoto\n";
		}
		if( email == "" ) {
			check = true;
			messaggio += "il campo email e' vuoto\n";
		}		
		if( !check ) {
			messaggio = messaggio.replace("\nStudente 3:\n", "");
		}
	}
}

function checkStudente4() {
	var check = false;
	var matricola = document.registrazione.matricola4.value;
	var nome = document.registrazione.nome4.value;
	var cognome = document.registrazione.cognome4.value;
	var email = document.registrazione.email4.value;
	// controlla che i campi siano o tutti compilati o tutti vuoti
	if( matricola != "" || nome != "" || cognome != "" ) {
		// se non sono tutti e tre compilati entra nella condizione
		messaggio += "\nStudente 4:\n";
		if( matricola == "" ) {
			check = true;
			messaggio += "il campo matricola e' vuoto\n";
		} else if( isNaN(matricola) ) {
			check = true;
			messaggio += "non ha una matricola valida\n";
		}
		if( nome == "" ) {
			check = true;
			messaggio += "il campo nome e' vuoto\n";
		}
		if( cognome == "" ) {
			check = true;
			messaggio += "il campo cognome e' vuoto\n";
		}
		if( email == "" ) {
			check = true;
			messaggio += "il campo email e' vuoto\n";
		}
		if( !check ) {
			messaggio = messaggio.replace("\nStudente 4:\n", "");
		}
	}
}

function registra() {
	// controlla username e password del gruppo
	var user = document.registrazione.username.value;
	var password = document.registrazione.password.value;
	if( user == "" && password == "" ) {
		alert("La username e la password sono obbligatori");
		return false;
	} else if( user == "" ) {
		alert("La username e' obbligatoria");
		return false;
	} else if( password == "" ) {
		alert("La password e' obbligatoria");
		return false;
	}
	
	// verifica che ci sia almeno uno studente
	if( !almenoUnoStudente() ) {
		alert("Il gruppo deve essere composto da almeno uno studente");
		return false;
	}
	
	// controlla i campi dello studente 1
	checkStudente1();
	
	// controlla i campi dello studente 2
	checkStudente2();
	
	// controlla i campi dello studente 3
	checkStudente3();
	
	// controlla i campi dello studente 4
	checkStudente4();
	
	if( messaggio == "" ) {
		return true;
	} else {
		alert(messaggio);
		messaggio = "";
		return false;
	}
}