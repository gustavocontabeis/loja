package br.com.coder.loja.dao;

import br.com.coder.loja.entity.Usuario;
import br.com.coder.loja.util.HibernateUtil;

public class UsuarioDAO extends BaseDAO<Usuario>{

	private static final long serialVersionUID = 1L;
	
	public void recuperarSenha(){
		
	}

	public void alterarSenha(){
		
	}

	public Usuario buscarPorLogin(Usuario usuario) {
		Object uniqueResult = HibernateUtil.getSession().createQuery("from br.com.coder.loja.entity.Usuario usu inner join fetch usu.perfis perf where usu.login = :usu").setString("usu", usuario.getLogin()).uniqueResult();
		if(uniqueResult!=null)
			return (Usuario) uniqueResult;
		else 
			return null;
	}

}
