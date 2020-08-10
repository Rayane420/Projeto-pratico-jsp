<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Produto</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img	src="resources/img/home2.png" alt="Home"	title="Voltar para o Inicio" width="40px" height="40px"></a>
	<a href="index.jsp"><img src="resources/img/sair.png" alt="Home"	title="Sair do sistema" width="40px" height="40px"></a>
	<center>
		<h1>Cadastro de Novos Produtos</h1>
		<h3 style="color: #FF4500">${msgE}</h3>
		<h3 style="color: #00FF00">${msgC}</h3>

		<center>
			<form action="salvarProduto" method="post" id="formProduto"
				onsubmit="return validarCampos()? true : false;">
				<ul class="form-style-1">
					<li>
						<table>
							<tr>
								<td>Id do Produto:</td>
								<td><input type="text" readonly="readonly" id="id"
									name="id" value="${produto.id}" class="field-long" ></td>
							</tr>
							<tr>
								<td>Nome:</td>
								<td><input type="text" id="nome" name="nome"
									value="${produto.nome}"  ></td>
							</tr>
							<tr>
								<td>Quantidade:</td>
								<td><input type="text" id="quantidade" name="quantidade"
									value="${produto.quantidade}" ></td>
							</tr>
							<tr>
								<td>Valor:</td>
								<td><input type="text" id="valor" name="valor"
									value="${produto.valor}" ></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Salvar"> <input
									type="submit" value="Cancelar"
									onclick="document.getElementById('formProduto').action = 'salvarProduto?acao=reset' "></td>
							</tr>
						</table>
					</li>
				</ul>
				
				<script type="text/javascript">
				function validarCampos(){
					
				if(document.getElementById("nome").value == ''){
					alert('Por favor informe o nome do produto!');
					return false;
				}else if(document.getElementById("quantidade").value == ''){
					alert('Por favor informe a quantidade!');
					return false;	
				}else if(document.getElementById("valor").value ==''){
					alert('Por favor informe o valor');
					return false;
				}	
					return true;
				}
				
				
				</script>
				
			</form>
		</center>
		<p />
		<p />
		<table class="container">
			<thead>
				<tr>
					<th><h1>ID</h1></th>
					<th><h1>Nome</h1></th>
					<th><h1>Quantidade</h1></th>
					<th><h1>Valor</h1></th>
					<th><h1>Exclusão</h1></th>
					<th><h1>Edição</h1></th>
				</tr>
			</thead>
			<p />
			<p />
			<p />
			<p />
			<caption>
				<h1>Produtos Cadastrados</h1>
			</caption>
			<c:forEach items="${produtos}" var="produto">

				<tbody>
					<tr>
						<td style="width: 150px"><c:out value="${produto.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${produto.nome}"></c:out></td>
						<td><c:out value="${produto.quantidade}"></c:out></td>
						<td style="width: 150px"><c:out value="${produto.valor}"></c:out></td>

						<td style="width: 100px"><a	href="salvarProduto?acao=delete&produto=${produto.id}"><img	src="resources/img/lixeira.png" alt="Excluir"	title="Excluir Produto" width="20px" height="20px"><></a></td>
						<td style="width: 100px"><a href="salvarProduto?acao=editar&produto=${produto.id}"><img	src="resources/img/editar.png" alt="Editar"	title="Editar Produto" width="20px" height="20px"></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
</body>
</html>