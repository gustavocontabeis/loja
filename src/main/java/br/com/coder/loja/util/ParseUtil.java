package br.com.coder.loja.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ParseUtil {

	public static <T> T  p(Object object, T t) {
		if(t == BigDecimal.class){
			if(object instanceof BigDecimal){
				BigDecimal bigInteger = (BigDecimal) object;
				Class tipoGenerico = (Class) t;
				return (T) tipoGenerico.cast(bigInteger);
			}
		}else if(t == Long.class){
			if(object instanceof BigInteger){
				BigInteger bigInteger = (BigInteger) object;
				Class tipoGenerico = (Class) t;
				return (T) tipoGenerico.cast(bigInteger.longValue());
			}
		}else if(t == String.class){
			Class tipoGenerico = (Class) t;
			return (T) tipoGenerico.cast(object);
		}else if(t == Boolean.class){
			Class tipoGenerico = (Class) t;
			return (T) tipoGenerico.cast(object);
		}else if(t == byte[].class){
			Class tipoGenerico = (Class) t;
			return (T) tipoGenerico.cast(object);
		}
		throw new RuntimeException("Não foi possível fazer o parse de "+object+" para "+ t);
	}

}
