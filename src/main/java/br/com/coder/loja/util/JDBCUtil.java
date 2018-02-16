package br.com.coder.loja.util;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.coder.loja.entity.BaseEntity;

public class JDBCUtil {
	
	private static final String DRV = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://bancoloja321.mysql.uhserver.com:3306/bancoloja321";
	private static final String USR = "loja1q2w3e";
	private static final String PWD = "1qa2WS[]3ed";
	private static Logger LOG = Logger.getLogger(JDBCUtil.class.getName());
	
	protected Connection conexao;
	protected PreparedStatement stmt;
	
//	{
//		try {
//			Class.forName(DRV);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	protected Connection getConnection() throws SQLException {
		if(conexao==null || conexao.isClosed())
			conexao = DriverManager.getConnection(URL, USR, PWD);
		return conexao;
	}
	
	protected PreparedStatement getStatement(String sql, int option) throws SQLException {
		if(stmt!=null&&!stmt.isClosed())
			stmt.close();
		stmt = getConnection().prepareStatement(sql, option);
		return stmt;
	}
	
	protected PreparedStatement getStatement(String sql) throws SQLException {
		if(stmt!=null&&!stmt.isClosed())
			stmt.close();
		stmt = getConnection().prepareStatement(sql);
		return stmt;
	}
	
	protected void salvar(BaseEntity obj) throws IntrospectionException{
		//String sql = SQLUtils.insert(obj);
	}
	
	protected void alterar(BaseEntity obj){
		
	}
	
	protected void excluir(BaseEntity obj){
		
	}
	
	protected BaseEntity buscar(BaseEntity obj){
		return obj;
	}
	
	protected List<BaseEntity> buscar(String sql, Class classe){
		return null;
	}

	public static void attr(Object object, String alias, Object vlr) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
		//LOG.info(String.valueOf(object)+" - "+String.valueOf(alias)+" - "+String.valueOf(vlr));
		String atributo = ResultsetUtils.getAtributoPeloNomeColuna(object, alias);
		if(atributo == null)
			throw new RuntimeException("Alias "+alias+ " não encontrado.");
		Class propertyType = PropertyUtils.getPropertyType(object, atributo);
		vlr = ParseUtil.p(vlr, propertyType);
		PropertyUtils.setProperty(object, atributo, vlr);
	}
}
