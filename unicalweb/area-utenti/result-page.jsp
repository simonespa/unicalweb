<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Esito operazione</title>
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
	</head>
<body>
	<fieldset>
    	<br />
			<br />
      <h4 align="center" style="color: green;"><%= request.getParameter("message") %></h4>
			<h4 align="center" style="color: green;">torna alla <a href="/unicalweb/area-utenti/docente/profilo-utente.jsp">homepage</a></h4>
			<br />
      <br />
      <br />
    </fieldset>
</body>
</html>