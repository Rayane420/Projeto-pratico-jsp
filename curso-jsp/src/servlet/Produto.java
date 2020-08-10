package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto beanProduto = daoProduto.consultar(produto);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", beanProduto);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");

				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setQuantidade(quantidade);
			produto.setValor(valor);

			try {

				String msgL = null;
				boolean podeCadastrar = true;

				if (nome == null || nome.isEmpty()) {
					msgL = "O nome deve ser informado!";
					podeCadastrar = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					msgL = "A quantidade deve ser informada";
					podeCadastrar = false;
				} else if (valor == null || valor.isEmpty()) {
					msgL = "O valor deve ser informado";
					podeCadastrar = false;
				}

				if (msgL != null) {
					request.setAttribute("msgE", msgL);
				} else if (id == null || id.isEmpty() && daoProduto.validarProduto(nome) && podeCadastrar) {
					daoProduto.Salvar(produto);
					request.setAttribute("msgC", "Novo produto cadastrado com sucesso! ");
				}
				else if (id == null || id.isEmpty() && !daoProduto.validarProduto(nome)) {
					request.setAttribute("msgE", "Esse produto já foi cadastrado anteriormente!");
					request.setAttribute("produto", produto);
					
				} else if (id == null || id.isEmpty() && daoProduto.validarProduto(nome) && podeCadastrar) {
					daoProduto.Salvar(produto);
					request.setAttribute("msgC", "Novo produto cadastrado com sucesso! ");
					
				} else if (id != null && !id.isEmpty() && podeCadastrar) {
					daoProduto.atualizar(produto);
					request.setAttribute("msgC", "Produto Atualizado com sucesso!");
				}
				if(!podeCadastrar) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
