package br.com.coder.loja.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;

import br.com.coder.loja.entity.Departamento;
import br.com.coder.loja.entity.Fabricante;
import br.com.coder.loja.util.HibernateUtil;
import br.com.coder.loja.util.JDBCUtil;

public class DepartamentoDAO extends BaseDAO<Departamento>{

	private static final long serialVersionUID = 1L;
	
	public List<Departamento>buscarTodos(){
		List<Departamento> list = null;
		try {
			list = new ArrayList<Departamento>();
			Connection connection = HibernateUtil.getSession().connection();
			PreparedStatement stmt = connection.prepareStatement("SELECT ID_DEPARTAMENTO, DESCRICAO, ESTRUTURA, NOME FROM DEPARTAMENTO ORDER BY ESTRUTURA");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId(rs.getLong("ID_DEPARTAMENTO"));
				obj.setNome(rs.getString("NOME"));
				obj.setDescricao(rs.getString("DESCRICAO"));
				obj.setEstrutura(rs.getString("ESTRUTURA"));
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
	
//	public List<Departamento>buscarTodos() throws SQLException{
//		List<Departamento>list=new ArrayList<Departamento>();
//		stmt = getStatement("SELECT ID_DEPARTAMENTO, DESCRICAO, ESTRUTURA, NOME FROM DEPARTAMENTO");
//		ResultSet rs = stmt.executeQuery();
//		while (rs.next()) {
//			Departamento obj = new Departamento();
//			obj.setId(rs.getLong("ID_DEPARTAMENTO"));
//			obj.setDescricao(rs.getString("DESCRICAO"));
//			obj.setEstrutura(rs.getString("ESTRUTURA"));
//			obj.setNome(rs.getString("NOME"));
//			list.add(obj);
//		}
//		rs.close();
//		stmt.close();
//		conexao.close();
//		return list;
//	}
	
	public List<Departamento>buscarPorNome(String nome){
		String sql = "SELECT ID_DEPARTAMENTO, DESCRICAO, ESTRUTURA, NOME FROM DEPARTAMENTO WHERE NOME LIKE upper(:nome)";
		Session session = HibernateUtil.getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setString("nome", nome.toUpperCase()+"%");
		sqlQuery.setResultTransformer(new ResultTransformer() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object transformTuple(Object[] vlr, String[] alias) {
				Departamento obj = new Departamento();
				for (int i = 0; i < alias.length; i++){ 
					try {
						JDBCUtil.attr(obj, alias[i], vlr[i]);
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
						e.printStackTrace();
					}
				}

				return obj;
			}
			
			@Override
			public List transformList(List list) {
				return list;
			}
		});
		return sqlQuery.list();
	}

	public List<Departamento>buscarPorDescricao(String nome){
		return null;
	}

	public List<Departamento> buscarDepartamentosFilhos(Departamento departamento) {
		//ID_DEPARTAMENTO     DESCRICAO     ESTRUTURA        NOME 
		String sql = "select * from departamento where ESTRUTURA like '?' order by estrutura";
		List<Departamento> list = null;
		try {
			list = new ArrayList<Departamento>();
			Connection connection = HibernateUtil.getSession().connection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "001.001.%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId(rs.getLong("ID_DEPARTAMENTO"));
				obj.setNome(rs.getString("NOME"));
				obj.setDescricao(rs.getString("DESCRICAO"));
				obj.setEstrutura(rs.getString("ESTRUTURA"));
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
	
	

}
