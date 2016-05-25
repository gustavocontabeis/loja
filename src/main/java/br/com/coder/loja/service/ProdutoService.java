package br.com.coder.loja.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.coder.loja.dao.ProdutoDAO;
import br.com.coder.loja.entity.CaracteristicaProduto;
import br.com.coder.loja.entity.CarrinhoCompras;
import br.com.coder.loja.entity.Cliente;
import br.com.coder.loja.entity.Compra;
import br.com.coder.loja.entity.ImagemProduto;
import br.com.coder.loja.entity.Produto;
import br.com.coder.loja.entity.Usuario;
import br.com.coder.loja.util.PoolUtil;
import br.com.coder.loja.util.RetornoDTO;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoService {
	
	private ProdutoDAO dao = (ProdutoDAO) PoolUtil.getDao(ProdutoDAO.class);
	
	@GET
	@Path("/buscarTodos")
	public RetornoDTO<List<Produto>> buscarTodos() {
		List<Produto> list = dao.buscarTodos();
		return new RetornoDTO<List<Produto>>(new ArrayList<Produto>(new HashSet(list)));
	}
	
	@GET
	@Path("/buscarProduto/{id}")
	public RetornoDTO<Produto> buscarProduto(@PathParam("id") Long id) {
		Produto obj = new Produto();
		obj.setId(id);
		obj = (Produto) dao.buscarProduto(obj);
		return new RetornoDTO<Produto>(obj);
	}
	
	@GET
	@Path("/consultaProdutosPorDepartamento/{estrutura}")
	public RetornoDTO<List<Produto>> consultaProdutosPorDepartamento(@PathParam("estrutura") String estrutura) {
		List<Produto> list = dao.consultaProdutosPorDepartamento(estrutura);
		return new RetornoDTO<List<Produto>>(new ArrayList<Produto>(new HashSet(list))); 
	}
	

	@POST
	@Path("/salvar")
	public RetornoDTO<Produto> salvar(Produto obj) {
		if(obj.getCaracteristicas()!=null){
		for (CaracteristicaProduto caracteristicaProduto : obj.getCaracteristicas()) 
			caracteristicaProduto.setProduto(obj);
		}
		
		if(obj.getImagens()!=null){
			for (ImagemProduto ip : obj.getImagens()){
				ip.setProduto(obj); 
				System.out.println(ip.getProduto().getId());
			}
		}
		
		obj.getValor().setProduto(obj);

		if(obj.getAtivo()==null)
			obj.setAtivo(false);
		//Produto buscar = dao.buscar(obj);
		dao.salvar(obj);
		
		RetornoDTO<Produto> retornoDTO = new RetornoDTO<Produto>(obj);
		retornoDTO.setOk(true);
		retornoDTO.setMsg("O produto foi salvo com sucesso.");
		return retornoDTO;
	}

	@POST
	@Path("/excluir")
	public RetornoDTO<Produto> excluir(Produto obj) {
		dao.excluir(obj);
		return new RetornoDTO<Produto>(obj);
	}
	
	@POST
	@Path("/excluirImagem")
	public RetornoDTO excluirImagem(ImagemProduto imagem) {
		try {
			dao.excluirImagem(imagem);
			return new RetornoDTO(true, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new RetornoDTO(e);
		}
	}
	
	@POST
	@Path("/adicionarAoCarrinho")
	public RetornoDTO adicionarAoCarrinho(@Context HttpServletRequest request, Compra compra) {
		HttpSession session= request.getSession(true);
		CarrinhoCompras carrinho = (CarrinhoCompras) session.getAttribute("carrinho");
		
		if(carrinho == null){
			carrinho = new CarrinhoCompras();
			carrinho.setCompras(new ArrayList<Compra>());
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			if(usuario != null) {
				Cliente cliente = new Cliente();
				cliente.setUsuario(usuario);
				carrinho.setCliente(cliente);
			}
			
		}
		carrinho.getCompras().add(compra);
		List<Compra> compras = carrinho.getCompras();
		for (Compra item : compras) {
			if(carrinho.getValorTotal()==null)
				carrinho.setValorTotal(BigDecimal.ZERO);
			item.setValorDesconto(BigDecimal.ZERO);
			item.setValorTotal(item.getProduto().getValor().getValor().multiply(BigDecimal.valueOf(item.getQuantidade())));
			item.setValorUnitario(item.getProduto().getValor().getValor());
			
			carrinho.setValorDesconto(BigDecimal.ZERO);
			carrinho.setValorTotal(carrinho.getValorTotal().add(item.getValorTotal()));
			carrinho.setValorFinal(carrinho.getValorTotal().subtract(item.getValorDesconto()));
		}
		session.setAttribute("carrinho", carrinho);
		return new RetornoDTO<CarrinhoCompras>(true, carrinho, "Item adicionado.");
	}
	
	@POST
	@Path("/buscarCarrinho")
	public RetornoDTO buscarCarrinho(@Context HttpServletRequest request) {
		HttpSession session= request.getSession(true);
		CarrinhoCompras carrinho = (CarrinhoCompras) session.getAttribute("carrinho");
		if(carrinho == null){
			carrinho = new CarrinhoCompras();
			carrinho.setCompras(new ArrayList<Compra>());
		}
		return new RetornoDTO<CarrinhoCompras>(carrinho);
	}
	
	@POST
	@Path("/removerDoCarrinho")
	public RetornoDTO removerCompraDoCarrinho(@Context HttpServletRequest request, Compra compra) {
		HttpSession session= request.getSession(true);
		CarrinhoCompras carrinho = (CarrinhoCompras) session.getAttribute("carrinho");
		Iterator<Compra> iterator = carrinho.getCompras().iterator();
		while (iterator.hasNext()) {
			Compra compra2 = (Compra) iterator.next();
			if(compra2.getProduto().getId().equals(compra.getProduto().getId())){
				iterator.remove();
				return new RetornoDTO<CarrinhoCompras>(true, carrinho, "Produto removido.");
			}else{
				return new RetornoDTO<CarrinhoCompras>(true, carrinho, "Produto não removido.");
			}
		}
		return null;
	}
	
	@POST
	@Path("/calcularFrete")
	public RetornoDTO calcularFrete(@Context HttpServletRequest request, String cep) {
		HttpSession session= request.getSession(true);
		CarrinhoCompras carrinho = (CarrinhoCompras) session.getAttribute("carrinho");
		if(carrinho == null){
			carrinho = new CarrinhoCompras();
			session.setAttribute("carrinho", carrinho);
		}
		carrinho.setValorFrete(new BigDecimal(new Random().nextFloat()*100));
		return new RetornoDTO<BigDecimal>(true, carrinho.getValorFrete(), null);//TODO Provisório
	}
	
	
}
