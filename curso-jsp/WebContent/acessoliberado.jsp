<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bem vindo</title>
</head>
<body>
	<jsp:setProperty property="*" name="calcula"/>
	 <center> </p><img src="resources/img/bemV.png" width="210px" height="60px"> </p><p/>
	<a href="salvarUsuario?acao=listartodos"><img src="resources/img/CadastrarU.png" alt="Adicionar novo usuário" title="Adicionar usuário" width="240px" height="200px"></a>
	<a href="salvarProduto?acao=listartodos"><img src="resources/img/CadastrarP.png" alt="Adicionar novo produto" title="Adicionar produto" width="240px" height="200px"></a>
	</center>
</body>
</html>