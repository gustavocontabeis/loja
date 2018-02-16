package br.com.coder.loja.dao;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;

import br.com.coder.loja.entity.Arquivo;
import br.com.coder.loja.entity.CaracteristicaProduto;
import br.com.coder.loja.entity.Departamento;
import br.com.coder.loja.entity.Fabricante;
import br.com.coder.loja.entity.ImagemProduto;
import br.com.coder.loja.entity.Produto;
import br.com.coder.loja.entity.Valor;
import br.com.coder.loja.util.HibernateUtil;
import br.com.coder.loja.util.JDBCUtil;

public class ProdutoDAO extends BaseDAO<Produto> {

	private static final long serialVersionUID = 1L;
	
	private static Logger LOG = Logger.getLogger(ProdutoDAO.class.getName());

	public List<Produto> buscarTodos() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from br.com.coder.loja.entity.Produto p left join fetch p.imagens imgs ");
		List<Produto> list = query.list();
		for (Produto obj : list) {
			for (CaracteristicaProduto caracteristicaProduto : obj.getCaracteristicas())
				caracteristicaProduto.setProduto(null);
			for (ImagemProduto ip : obj.getImagens())
				ip.setProduto(null);
			obj.getValor().setProduto(null);
		}
		session.close();
		return new ArrayList<Produto>(new HashSet(list));
	}

	private List<Produto> transformResultSet(ResultSet rs, Class<Produto> classe) throws IntrospectionException, SQLException {
		List list = new ArrayList();
		BeanInfo info = Introspector.getBeanInfo(classe);
		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			System.out.println(pd.getName());

		}

		Method[] methods = classe.getMethods();
		int col = 0;
		while (rs.next()) {
			col++;
			ResultSetMetaData metaData = rs.getMetaData();
			String columnName = metaData.getColumnName(col);
			for (Method method : methods) {
				Fabricante obj = new Fabricante();
				obj.setId(rs.getLong("ID_FABRICANTE"));
				obj.setNome(rs.getString("NOME"));
				list.add(obj);
			}

		}
		return null;
	}

	public List<Produto> buscarPorDepartamento(String nomeDepartamento) {
		return null;
	}

	public List<Produto> buscarPorNome(String nomeDepartamento) {
		return null;
	}

	public List<Produto> buscarPorDescricao(String nomeDepartamento) {
		return null;
	}

	public List<Produto> buscarPorNomeDescricao(String nomeDepartamento) {
		return null;
	}

	public List<Produto> consultaProdutosPorDepartamento(String estrutura) { 
		while(estrutura.endsWith(".000"))
			estrutura = StringUtils.removeEnd(estrutura, ".000");
		String sql = "select "
				+ "PROD.ID_PRODUTO, img_prod.ID_IMAGEM_PRODUTO, arq.ID_ARQUIVO, PROD.ATIVO, PROD.COD_REFERENCIA_FABRICANTE, "
				+ "PROD.ID_DEPARTAMENTO, PROD.DESCRICAO, PROD.ID_FABRICANTE, PROD.NOME, PROD.ID_VALOR, img_prod.ID_ARQUIVO, "
				+ "img_prod.ID_PRODUTO, img_prod.ID_IMAGEM_PRODUTO, arq.DADOS, arq.EXTENCAO, arq.NOME, arq.TAMANHO, vlr.VALOR "
				+ "from PRODUTO PROD "
				+ "inner join DEPARTAMENTO depto on PROD.ID_DEPARTAMENTO=depto.ID_DEPARTAMENTO "
				+ "inner join VALOR vlr on vlr.ID_PRODUTO=PROD.ID_PRODUTO "
				+ "left outer join IMAGEM_PRODUTO img_prod on PROD.ID_PRODUTO=img_prod.ID_PRODUTO "
				+ "left outer join ARQUIVO arq on img_prod.ID_ARQUIVO=arq.ID_ARQUIVO "
				+ "where depto.ESTRUTURA LIKE ? and arq.NOME=?"; 
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setString(0, estrutura+"%");
		query.setString(1, "Capa");
		query.setResultTransformer(new ResultTransformer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Object transformTuple(Object[] obj, String[] alias) {
				
				Produto prod = new Produto();
				Fabricante fabricante = new Fabricante();
				prod.setFabricante(fabricante);
				Departamento departamento = new Departamento();
				prod.setDepartamento(departamento);
				Valor valor = new Valor();
				prod.setValor(valor);
				Arquivo arq = new Arquivo();
				ImagemProduto ip = new ImagemProduto();
				ip.setArquivo(arq);
				prod.setImagens(new ArrayList<ImagemProduto>());
				prod.getImagens().add(ip);
				
				try {
					Object[] x = {prod,ip,arq,prod,prod,departamento,prod,fabricante,prod,valor,arq,prod,ip,arq,arq,arq,arq,valor};
					for (int i = 0; i < alias.length; i++){ 
						try {
							//LOG.info(i + " - " + String.valueOf(x[i])+" - "+String.valueOf(alias[i])+" - "+String.valueOf(obj[i]));
							JDBCUtil.attr(x[i], alias[i], obj[i]);
						} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				return prod;
			}
			
			@Override
			public List transformList(List list) {
				return list;
			}
		});
		List list = query.list();
		if(session.isOpen())
			session.close();
		return list;
	}

	public void excluirImagem(ImagemProduto imagem) {
		BaseDAO<ImagemProduto> baseDAO = new BaseDAO<ImagemProduto>();
		imagem = baseDAO.buscar(imagem);
		baseDAO.excluir(imagem);
	}

	public Produto buscarProduto(Produto obj) {
		Session session = getSession();
		Query query = session.createQuery("select obj from br.com.coder.loja.entity.Produto obj inner join fetch obj.valor vlr left join fetch obj.imagens img where obj.id = :id");
		query.setLong("id", obj.getId());
		Produto prod = (Produto) query.uniqueResult();
		List caracteristicas = session.createQuery("select obj from br.com.coder.loja.entity.CaracteristicaProduto obj where obj.produto.id = :id").setLong("id", obj.getId()).list();
		prod.setCaracteristicas(caracteristicas);
		if(session.isOpen())
			session.close();
		return prod;
	}

}
