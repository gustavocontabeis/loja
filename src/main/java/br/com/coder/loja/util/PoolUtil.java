package br.com.coder.loja.util;

import java.util.HashMap;
import java.util.Map;

import br.com.coder.loja.dao.BaseDAO;

public class PoolUtil {
	
	private static Map<Class, BaseDAO>dao = new HashMap<Class, BaseDAO>();
	
	public static BaseDAO getDao(Class key){
		if(!dao.containsKey(key)){
			try {
				dao.put(key, (BaseDAO) key.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return dao.get(key);
	}

}
