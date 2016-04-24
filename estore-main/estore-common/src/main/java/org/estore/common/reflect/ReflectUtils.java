package org.estore.common.reflect;

import org.apache.commons.lang.StringUtils;


public class ReflectUtils {
	
	public static final String PREFIX_SET = "set";
	
	public static final String PREFIX_GET = "get";
	
	public static String generateMethodName(String fieldName,String prefix){
		StringBuffer sb = new StringBuffer();
		String firstLetter = fieldName.substring(0, 1);
		sb.append(prefix).
			append(firstLetter.toUpperCase()).
			append(fieldName.substring(1));
		return sb.toString();
	}
	
	/**
	 * 将字符串转换为指定的基本数据类型
	 * @param value
	 * @param type
	 * @return 返回null如果value为空字符串
	 */
	public static Object parseString2PrimitiveDataType(String value,Class type){
		Object result = null;
		if(StringUtils.isBlank(value)){
			return null;
		}
		if(type == Integer.class || type == int.class){
			result = Integer.parseInt(value);
		}
		else if(type == Long.class || type == long.class){
			result = Long.parseLong(value);
		}
		else if(type == Double.class || type == double.class){
			result = Double.parseDouble(value);
		}
		else if(type == Boolean.class || type == boolean.class){
			result = Boolean.parseBoolean(value);
		}
		else if(type == Float.class || type == float.class){
			result = Float.parseFloat(value);
		}
		else if(type == Byte.class || type == byte.class){
			result = Byte.parseByte(value);
		}
		else if(type == Short.class || type == short.class){
			result = Short.parseShort(value);
		}
		else if((type == Character.class || type == char.class) && value.length() == 1){
			result = value.charAt(0);
		}
		else{
			throw new IllegalArgumentException("Type is not a primitive data type!");
		}
		
		return result;
	}
}	
