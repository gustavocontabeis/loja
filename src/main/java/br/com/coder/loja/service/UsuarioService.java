package br.com.coder.loja.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.coder.loja.dao.ClienteDAO;
import br.com.coder.loja.dao.UsuarioDAO;
import br.com.coder.loja.entity.Cliente;
import br.com.coder.loja.entity.Usuario;
import br.com.coder.loja.util.PoolUtil;
import br.com.coder.loja.util.RetornoDTO;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService {
	
	private static final String USUARIO = "usuario";
	
	private UsuarioDAO dao = (UsuarioDAO) PoolUtil.getDao(UsuarioDAO.class);
	private ClienteDAO daoCliente = (ClienteDAO) PoolUtil.getDao(ClienteDAO.class);
	
	/**
	 * @param request
	 * @param usuario
	 * @return
	 */
	@POST
	@Path("/logar")
	public RetornoDTO<Usuario> logar(@Context HttpServletRequest request, Usuario usuario) {
		try {
			Usuario buscarPorLogin = dao.buscarPorLogin(usuario);
			if(buscarPorLogin!=null){
				if(buscarPorLogin.getSenha().equals(usuario.getSenha())){
					HttpSession session= request.getSession(true);
					session.setAttribute(USUARIO, buscarPorLogin);
					return new RetornoDTO<Usuario>(true, buscarPorLogin, "Bem vindo, "+buscarPorLogin.getNome());
				}
			}
			return new RetornoDTO<Usuario>(false, "Usuário não cadastrado ou senha incorreta.");		
		} catch (Exception e) {
			e.printStackTrace();
			return new RetornoDTO<Usuario>(e);
		}
	}
	
	@GET
	@Path("/buscar")
	public RetornoDTO<Usuario> buscar(@Context HttpServletRequest request) {
		Object usuario = request.getSession(true).getAttribute(USUARIO);
		if(usuario!=null){
			return new RetornoDTO<Usuario>((Usuario) usuario);
		}
		return new RetornoDTO<Usuario>(false, "Nenhum usuário logado.");
	}
	
	@GET
	@Path("/sair")
	public RetornoDTO<Usuario> sair(@Context HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.removeAttribute(USUARIO);
		return new RetornoDTO<Usuario>(true, "Tchau!");
	}
	
}
