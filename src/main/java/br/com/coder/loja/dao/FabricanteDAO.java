package br.com.coder.loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import br.com.coder.loja.entity.Fabricante;
import br.com.coder.loja.util.HibernateUtil;

public class FabricanteDAO extends BaseDAO<Fabricante> {
	
	private static final long serialVersionUID = 1L;

//	public List<Fabricante>buscarTodos(){
//		Session session = HibernateUtil.getSession();
//		Query query = session.getNamedQuery("Fabricante.buscarTodos");
//		List list = query.list();
//		return list;
//	}
	
	public List<Fabricante>buscarTodos() { 
		List<Fabricante> list = null;
		try {
			list = new ArrayList<Fabricante>();
			Connection connection = HibernateUtil.getSession().connection();
			PreparedStatement stmt = connection.prepareStatement("SELECT ID_FABRICANTE, NOME FROM FABRICANTE ORDER BY NOME ASC");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fabricante obj = new Fabricante();
				obj.setId(rs.getLong("ID_FABRICANTE"));
				obj.setNome(rs.getString("NOME"));
				list.add(obj);
			}
			rs.close();
			stmt.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//connection.close();
		return list;
	}

	public List<Fabricante> buscarPorNome(String nome) {
		return null;
	}
	
//	@Override
//	public void salvar(Fabricante obj)  {
//		try {
//			if(obj.getId()==null){
//				stmt = getStatement("INSERT INTO FABRICANTE(ID_FABRICANTE, NOME) VALUES(null, ?)", Statement.RETURN_GENERATED_KEYS);
//				stmt.setString(1, obj.getNome());
//				stmt.executeUpdate();
//				ResultSet rs = stmt.getGeneratedKeys();
//				if (rs.next())
//				    obj.setId(rs.getLong(1));
//			}else{
//				stmt = getStatement("UPDATE FABRICANTE SET NOME= ? WHERE ID_FABRICANTE=?");
//				stmt.setString(1, obj.getNome());
//				stmt.setLong(2, obj.getId());
//				stmt.executeUpdate();
//			}
//			stmt.close();
//			conexao.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
//	@Override
//	public void excluir(Fabricante obj)  {
//		try {
//			if(obj.getId()==null){
//				stmt = getStatement("DELETE FROM FABRICANTE WHERE ID_FABRICANTE=?");
//				stmt.setLong(1, obj.getId());
//				stmt.executeUpdate();
//			}
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//	}
}
