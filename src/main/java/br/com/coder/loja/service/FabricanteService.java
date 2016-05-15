package br.com.coder.loja.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.coder.loja.dao.FabricanteDAO;
import br.com.coder.loja.entity.Fabricante;
import br.com.coder.loja.util.PoolUtil;
import br.com.coder.loja.util.RetornoDTO;

@Path("/fabricante")
@Produces(MediaType.APPLICATION_JSON)
public class FabricanteService {
	
	FabricanteDAO dao = (FabricanteDAO) PoolUtil.getDao(FabricanteDAO.class);
	
	@GET
	@Path("/buscarTodos")
	public RetornoDTO<List<Fabricante>> buscarTodos() throws Exception {
		List<Fabricante> list = dao.buscarTodos();
		return new RetornoDTO<List<Fabricante>>(list);
	}

	@GET
	@Path("/buscarPorNome")
	public RetornoDTO<List<Fabricante>> buscarPorNome(String nome) throws Exception {
		List<Fabricante> list = dao.buscarPorNome(nome);
		return new RetornoDTO<List<Fabricante>>(list);
	}

	@POST
	@Path("/salvar")
	public RetornoDTO<Fabricante> salvar(Fabricante fabricante) {
		dao.salvar(fabricante);
		return new RetornoDTO<Fabricante>(true, fabricante, "Registro salvo com sucesso.");
	}

	@POST
	@Path("/excluir")
	public RetornoDTO<Fabricante> excluir(Fabricante fabricante) {
		dao.excluir(fabricante);
		return new RetornoDTO<Fabricante>(fabricante);
	}

}
