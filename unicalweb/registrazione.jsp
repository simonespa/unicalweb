<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Univalweb - registrazione gruppo di progetto</title>
		<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
		<script type="text/javascript" src="javascript/registrazione.js"></script>
	  <link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/registrazione.css" />
	</head>

	<body>
		<img src="images/logo.jpg" align="left" />
		<h1 align="left">UNICALWEB</h1>
    <h6 align="left">Il sistema web per la gestione online degli esami</h6>
    <hr />
    <h5 align="center">Registrazione gruppo di progetto</h5>
    <hr />
    <form name="registrazione" method="post" action="registrazione" onsubmit="return registra()">
			
			<div id="gruppo-progetto">
	   	  <fieldset>
		      <br />
				  <div align="center">
		      	username: <input name="username" type="text" maxlength="50" />
						password: <input name="password" type="password" maxlength="20" />
						<br />
						<br />
		      </div>
				</fieldset>
     	</div>
                
			<div id="stud1" align="center">
				<fieldset>
					<legend>Studente 1</legend>
					matricola<br />
		      <input name="matricola1" type="text" maxlength="10" /><br />
		      nome<br />
		      <input name="nome1" type="text" maxlength="40" /><br />
		      cognome<br />
		      <input name="cognome1" type="text" maxlength="40" /><br />
		      e-mail<br />
		      <input name="email1" type="text" maxlength="40" /><br />
				</fieldset>
      </div>
                
			<div id="stud2" align="center">
				<fieldset>
					<legend>Studente 2</legend>
					matricola<br />
			    <input name="matricola2" type="text" maxlength="10" /><br />
			    nome<br />
			    <input name="nome2" type="text" maxlength="40" /><br />
			    cognome<br />
			    <input name="cognome2" type="text" maxlength="40" /><br />
			    e-mail<br />
			    <input name="email2" type="text" maxlength="40" /><br />
			  </fieldset>
			</div>

			<div id="stud3" align="center">
				<fieldset>
					<legend>Studente 3</legend>
					matricola<br />
			    <input name="matricola3" type="text" maxlength="10" /><br />
			    nome<br />
			    <input name="nome3" type="text" maxlength="40" /><br />
			    cognome<br />
			    <input name="cognome3" type="text" maxlength="40" /><br />
			    e-mail<br />
			    <input name="email3" type="text" maxlength="40" /><br />
				</fieldset>
			</div>
                    
      <div id="stud4" align="center">
				<fieldset>
        	<legend>Studente 4</legend>
          matricola<br />
          <input name="matricola4" type="text" maxlength="10" /><br />
          nome<br />
          <input name="nome4" type="text" maxlength="40" /><br />
          cognome<br />
          <input name="cognome4" type="text" maxlength="40" /><br />
          e-mail<br />
          <input name="email4" type="text" maxlength="40" /><br />
				</fieldset>
      </div>
    
			<div id="submit" align="center">
      	<input name="submit" type="submit" value="registra" />
      </div>

		</form>
    
		<div id="paginaIniziale">
	    <h5>torna alla <a href="index.jsp">pagina iniziale</a></h5>
    </div>
	</body>

</html>