package br.com.coder.loja.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import br.com.coder.loja.dao.ProdutoDAO;

public class ResultsetUtils {
	
	private static Logger LOG = Logger.getLogger(ResultsetUtils.class.getName());


	public static String getAtributoPeloNomeColuna(Object obj, String nomeColuna) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
		Map describe = BeanUtils.describe(obj);
		Set keySet = describe.keySet();
		for (Object key : keySet) {
			if("class".equals(key) || "serialVersionUID".equals(key))
				continue;
			Field declaredField = getField(obj, key.toString());
			Class[] anotacoes = {Column.class, JoinColumn.class};
			for (Class annotationClass : anotacoes) {
				Object annotation = declaredField.getAnnotation(annotationClass);
				if(annotation == null){
					PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(obj, key.toString());
					annotation = propertyDescriptor.getReadMethod().getAnnotation(annotationClass);
					if(annotation == null){
						annotation = propertyDescriptor.getWriteMethod().getAnnotation(annotationClass);
					}
				}
				if(annotation!=null) {
					String name = null;
					if(annotation instanceof Column){
						Column column = (Column) annotation;
						name = column.name();
					}else if(annotation instanceof JoinColumn){
						JoinColumn column = (JoinColumn) annotation;
						name = column.name();
					}
					if(name.equalsIgnoreCase(nomeColuna)){
						return key.toString();
					}
				}
			}
		}
		LOG.warning("Coluna "+ nomeColuna +" não encontrada.");
		return null;
	}

	private static Field getField(Object obj, String string) {
		Class classe = obj.getClass(); 
		while(classe != Object.class){
			
			Field[] declaredFields = classe.getDeclaredFields();
			for (Field field : declaredFields) {
				if(field.getName().equals(string)){
					return field;
				}
			}
			classe = classe.getSuperclass();
		}
		return null;
	}

}
