package br.com.coder.loja.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.coder.loja.dao.DepartamentoDAO;
import br.com.coder.loja.entity.Departamento;
import br.com.coder.loja.util.PoolUtil;
import br.com.coder.loja.util.RetornoDTO;

@Path("/departamento")
@Produces(MediaType.APPLICATION_JSON)
public class DepartamentoService extends BaseService<Departamento> {
	
	DepartamentoDAO dao = (DepartamentoDAO) PoolUtil.getDao(DepartamentoDAO.class);
	
	@GET
	@Path("/buscarTodos")
	public RetornoDTO<List<Departamento>> buscarTodos() throws Exception {
		List<Departamento> buscarTodos = dao.buscarTodos();
		return new RetornoDTO<List<Departamento>>( buscarTodos);
	}
	
	@GET
	@Path("/buscarPorNome/{nome}")
	public RetornoDTO<List<Departamento>> buscarPorNome(@PathParam("nome")String nome) throws Exception {
		List<Departamento> buscarTodos = dao.buscarPorNome(nome);
		return new RetornoDTO<List<Departamento>>( buscarTodos);
	}
	
	@GET
	@Path("/buscarTodosEstruturado")
	public RetornoDTO<Departamento> buscarTodosEstruturado() throws Exception {
		List<Departamento> buscarTodos;
		try {
			buscarTodos = dao.buscarTodos();
			Collections.reverse(buscarTodos);
			Iterator<Departamento> iteratorA = buscarTodos.iterator();
			while (iteratorA.hasNext()) {
				Departamento departamentoA = (Departamento) iteratorA.next();
				Iterator<Departamento> iteratorB = buscarTodos.iterator();
				sair:
				while (iteratorB.hasNext()) {
					Departamento departamentoB = (Departamento) iteratorB.next();
					if(ehFilho(departamentoA, departamentoB)){
						if(departamentoB.getFilhos()==null)
							departamentoB.setFilhos(new ArrayList<Departamento>());
						departamentoB.getFilhos().add(departamentoA);
						Collections.sort(departamentoB.getFilhos());
						iteratorA.remove();
						break sair;
					}
				}
				
			}
			Collections.sort(buscarTodos);
			return new RetornoDTO(buscarTodos);
		} catch (Exception e) {
			e.printStackTrace();
			return new RetornoDTO(e);
		}
	}
	
	private boolean ehFilho(Departamento departamentoA, Departamento departamentoB) {
		String estruturaA = departamentoA.getEstrutura();
		String estruturaB = departamentoB.getEstrutura();
		estruturaA = estruturaA.replaceAll("\\.000", "");
		estruturaB = estruturaB.replaceAll("\\.000", "");
		if(!estruturaA.equals(estruturaB)
				&& estruturaA.startsWith(estruturaB)){
			return true;
		}
		return false;
	}

	@POST
	@Path("/departamentosFilhos")
	public List<Departamento> departamentosFilhos(Departamento departamento) throws Exception {
		return dao.buscarDepartamentosFilhos(departamento);
	}
	
	@POST
	@Path("/salvar")
	public Departamento salvar(Departamento departamento) {
		dao.salvar(departamento);
		return departamento;
	}

	@POST
	@Path("/excluir")
	public Departamento excluir(Departamento departamento) {
		dao.excluir(departamento);
		return departamento;
	}

}
