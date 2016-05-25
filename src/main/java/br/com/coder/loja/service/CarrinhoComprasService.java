package br.com.coder.loja.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.coder.loja.dao.CarrinhoComprasDAO;
import br.com.coder.loja.dao.ClienteDAO;
import br.com.coder.loja.entity.CarrinhoCompras;
import br.com.coder.loja.entity.Cliente;
import br.com.coder.loja.entity.Compra;
import br.com.coder.loja.entity.Fabricante;
import br.com.coder.loja.entity.TipoQuantidade;
import br.com.coder.loja.entity.Usuario;
import br.com.coder.loja.util.PoolUtil;
import br.com.coder.loja.util.RetornoDTO;

@Path("/carrinhoCompras")
@Produces(MediaType.APPLICATION_JSON)
public class CarrinhoComprasService {
	
	private CarrinhoComprasDAO dao = (CarrinhoComprasDAO) PoolUtil.getDao(CarrinhoComprasDAO.class);
	private ClienteDAO daoCliente = (ClienteDAO) PoolUtil.getDao(ClienteDAO.class);
	
	@POST
	@Path("/finalizarCompra")
	public RetornoDTO<CarrinhoCompras> finalizarCompra(@Context HttpServletRequest request, CarrinhoCompras carrinho) throws Exception {
		try {
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			if(usuario == null)
				throw new RuntimeException("Nenhum usuário logado;");
			Cliente cliente = daoCliente.buscarPorUsuario(usuario);
			cliente = dao.buscar(cliente);
			carrinho.setCliente(cliente);
			List<Compra> compras = carrinho.getCompras();
			carrinho.setDataCompra(new Date());
			for (Compra compra : compras) {
				compra.setCarrinhoCompras(carrinho);
				compra.setTipoQuantidade(TipoQuantidade.UNIDADE);
			}
			carrinho.getPagamento().setCarrinho(carrinho);
			dao.salvar(carrinho);
			carrinho.setCliente(cliente);
			cliente.getUsuario().setPerfis(null);
			cliente.setEnderecos(null);
			return new RetornoDTO<CarrinhoCompras>(true, carrinho, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new RetornoDTO<CarrinhoCompras>(false, null, e.getMessage());
		}
	}

	@GET
	@Path("/buscarTodos")
	public RetornoDTO<List<CarrinhoCompras>> buscarTodos() throws Exception {
		List<CarrinhoCompras> list = dao.buscarTodos();
		for (CarrinhoCompras carrinhoCompras : list) {
			//carrinhoCompras.setEnderecoEntrega(null);
		}
		return new RetornoDTO<List<CarrinhoCompras>>(list);
	}
}
