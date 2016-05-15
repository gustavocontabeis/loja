package br.com.coder.loja.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.coder.loja.dao.BaseDAO;
import br.com.coder.loja.dao.ClienteDAO;
import br.com.coder.loja.entity.Cliente;
import br.com.coder.loja.entity.EnderecoEntrega;
import br.com.coder.loja.entity.Perfil;
import br.com.coder.loja.entity.Usuario;
import br.com.coder.loja.util.PoolUtil;
import br.com.coder.loja.util.RetornoDTO;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
public class ClienteService {
	
	private ClienteDAO dao = (ClienteDAO) PoolUtil.getDao(ClienteDAO.class);
	
	@POST
	@Path("/salvar")
	public RetornoDTO<Cliente> salvar(Cliente obj) {
		if(obj.getId()==null){
			obj.getUsuario().setPerfis(new ArrayList<Perfil>());
			obj.getUsuario().getPerfis().add(new Perfil(2L, "CLI"));
		}
		if(obj.getEnderecos()!=null)
			for (EnderecoEntrega enderecoEntrega : obj.getEnderecos()) 
				enderecoEntrega.setCliente(obj);
		dao.salvar(obj);
		return new RetornoDTO<Cliente>(true, obj, "Pronto.");
	}

	@POST
	@Path("/buscarEnderecos")
	public RetornoDTO<Cliente> buscarEnderecos(Usuario obj) {
		Cliente cliente = dao.buscarEnderecos(obj);
		return new RetornoDTO<Cliente>(true, cliente, null);
	}
	
	@POST
	@Path("/salvarEndereco")
	public RetornoDTO<Cliente> salvarEndereco(@Context HttpServletRequest request, EnderecoEntrega obj) {
		HttpSession session = request.getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Cliente cliente = dao.buscarPorUsuario(usuario);
		obj.setCliente(cliente);
		cliente = dao.buscar(cliente);
		cliente.getEnderecos().add(obj);
		dao.salvar(cliente);
		cliente.setEnderecos(dao.buscarEnderecos(cliente.getUsuario()).getEnderecos());
		cliente.setUsuario(null);
		return new RetornoDTO<Cliente>(true, cliente, null);
	}
	
	@POST
	@Path("/excluirEndereco")
	public RetornoDTO<Cliente> excluirEndereco(@Context HttpServletRequest request, EnderecoEntrega obj) {
		BaseDAO<EnderecoEntrega> baseDAO = new BaseDAO<EnderecoEntrega>();
		obj = baseDAO.buscar(obj);
		Cliente buscarEnderecos = dao.buscarEnderecos(obj.getCliente().getUsuario());
		baseDAO.excluir(obj);
		return new RetornoDTO<Cliente>(true, buscarEnderecos, "Endereço excluído.");
	}
	
	@POST
	@Path("/buscarPorUsuario")
	public RetornoDTO<Cliente> buscarPorUsuario(Usuario obj) {
		Cliente cliente = dao.buscarPorUsuario(obj);
		return new RetornoDTO<Cliente>(true, cliente, null);
	}
	
}
