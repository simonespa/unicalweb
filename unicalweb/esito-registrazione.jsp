<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Unicalweb - non ci sono progetti nel sistema</title>
		<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>

	<body>
		<img src="images/logo.jpg" align="left" />
    <h1 align="left">UNICALWEB</h1>
    <h6 align="left">Il sistema web per la gestione online degli esami</h6>
    <hr />
    <h5 align="center">Registrazione gruppo di progetto</h5>
    <hr />
    <fieldset>
    	<br />
			<br />
			<%
				if( request.getParameter("result").equals("no") ) {
			%>
      		<h4 align="center" style="color: red;"><%= request.getParameter("message") %></h4>
					<h4 align="center" style="color: red;">torna alla <a href="/unicalweb/registrazione.jsp" style="color: red;">pagina di registrazione</a></h4>
					<br />
      		<br />
      		<br />
    </fieldset>
   	<h5 align="center">vai alla <a href="/unicalweb/index.jsp">pagina principale</a></h5>
			<%
				} else {
			%>
					<h4 align="center" style="color: green;"><%= request.getParameter("message") %></h4>
      		<br />
      		<br />
    </fieldset>
   	<h5 align="center">vai alla <a href="/unicalweb/index.jsp">pagina principale</a></h5>
		<%
				}
		%>
	</body>
</html>