<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Unicalweb - area login</title>
		<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />

		<script type="text/javascript" src="spry/js/SpryMenuBar.js"></script>
		<script type="text/javascript" src="spry/js/SpryCollapsiblePanel.js"></script>
		<script type="text/javascript" src="javascript/login.js"></script>
		<link type="text/css" rel="stylesheet" href="spry/css/SpryMenuBarHorizontal.css" />
		<link type="text/css" rel="stylesheet" href="spry/css/SpryCollapsiblePanel.css" />
		<link type="text/css" rel="stylesheet" href="css/style.css" />		
	</head>

	<body>
		<img src="images/logo.jpg" align="left" />
		<h1 align="left">UNICALWEB</h1>
		<h6 align="left">Il sistema web per la gestione online degli esami</h6>
		<hr />
		<br />
		<fieldset>
			<legend>Area login</legend>
			<br />
			<div id="CollapsiblePanel1" class="CollapsiblePanel">
			  <div class="CollapsiblePanelTab">docente</div>
			  <div class="CollapsiblePanelContent">
			  	<br />
			  	<form name="docente" method="post" action="login" onsubmit="return login1()" >
			    		<p><input name="user" type="text" maxlength="50" /> username</p>
				      <p><input name="password" type="password" maxlength="20" /> password</p>
			        <input name="tipo" type="hidden" value="docente" />
				      <input name="log" type="submit" value="login" />
			        <input name="reset" type="reset" value="reset" />
					</form>
	  			<br />
	  		</div>
			</div>
			<script type="text/javascript">
			
				var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
			
			</script>
			<div id="CollapsiblePanel2" class="CollapsiblePanel">
	  		<div class="CollapsiblePanelTab">gruppo di progetto</div>
	  		<div class="CollapsiblePanelContent">
		  		<br />
		  		<form name="gruppo" method="post" action="login" onsubmit="return login2()">
		    		<p><input name="user" type="text" maxlength="50" /> username</p>
			      <p><input name="password" type="password" maxlength="20" /> password</p>
		        <input name="tipo" type="hidden" value="gruppo-progetto" />            
			      <input name="log" type="submit" value="login" />
		        <input name="reset" type="reset" value="reset" />
		   		</form>
		    	<br />
	  		</div>
			</div>
			<script type="text/javascript">
				var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2");
			</script>
		</fieldset>
		<p>Se non sei in un gruppo di progetto registrato <a href="registrazione.jsp">clicca qui</a></p>
	</body>

</html>