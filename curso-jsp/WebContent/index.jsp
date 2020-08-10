<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page" />


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag2.tld"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="resources/css/estilo.css">
</head>
<body class="align">

	<div class="grid">
		<div class="form">
			<form action="LoginServlet" method="post" class="form login">
				LOGIN: <input type="text" id="login" name="login" class="form__input" placeholder="Login" required> <br />
				SENHA: <input type="password" id="senha" name="senha" placeholder="senha" required> <br />
				<div class="form__field">
				<input type="submit" value="logar">
				</div>
			</form>
		</div>
	</div>
</body>
</html>