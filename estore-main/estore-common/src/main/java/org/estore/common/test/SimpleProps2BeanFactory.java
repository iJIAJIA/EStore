package org.estore.common.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.estore.common.reflect.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将Properties数据赋值给指定的bean类的基本数据类型字段
 * 约定:
 * 	1,Properties文件以"#"开头,以","为间隔标明需要赋值的Bean字段
 * 	2,该字段在Bean中必须存在,且为基本数据类型或者字符串
 * 	3,该字段有相对应的set方法
 * 	4,如果想插入空值,则以空格代替该位置
 * @author iJIAJIA
 *
 */
public class SimpleProps2BeanFactory {
	
	private static Logger logger = LoggerFactory.getLogger(SimpleProps2BeanFactory.class);
	
	private ArrayList<Method> cacheMethods; 
	
	private ArrayList<Class> fieldType;
	
	private Constructor beanConstructor;
	
	private SimpleProps2BeanFactory(){
		this.cacheMethods = new ArrayList<Method>();
		this.fieldType = new ArrayList<Class>();
	}
	
	public static SimpleProps2BeanFactory build(String header,Class targetBean)
			throws IllegalAccessException{
		
		String[] fieldNames = checkHeader(header);
		
		Map<String,Class> fieldTypeMap = getFieldTypeMap(fieldNames, targetBean);
		
		SimpleProps2BeanFactory factory = new SimpleProps2BeanFactory();
		
		for(String fieldName : fieldNames){
			String methodName = ReflectUtils.generateMethodName(fieldName, ReflectUtils.PREFIX_SET);
			try {
				Class fieldType = fieldTypeMap.get(fieldName);
				Method setMethod = targetBean.getMethod(methodName, fieldTypeMap.get(fieldName));
				factory.addMethod(setMethod);
				factory.addParamType(fieldType);
			} catch (NoSuchMethodException | SecurityException e) {
				logger.error(e.getMessage());
				throw new IllegalArgumentException("Illegal Bean Set Method");
			}
		}
		try {
			Constructor construtor = targetBean.getConstructor();
			factory.setBeanConstructor(construtor);
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e.getMessage());
		}
		return factory;
	}
	
	/**
	 * 根据给定data生成Bean
	 * @param data
	 * @return
	 * @throws IllegalAccessException
	 * @throws Exception 
	 */
	public Object generateTargetBean(String data) 
			throws IllegalAccessException,Exception{
		if(StringUtils.isBlank(data)){
			throw new IllegalArgumentException("data is blank");
		}
		String[] values = data.split(",");
		if(values == null || values.length != cacheMethods.size()){
			throw new IllegalArgumentException("data's values are not suitable "
					+ "expect number: " + cacheMethods.size() + " actual number: " + values.length );
		}
		Object target = null;
		try {
			target = this.beanConstructor.newInstance();
		} catch (InstantiationException | IllegalArgumentException
				| InvocationTargetException | IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new Exception("构建Bean异常");
		}
		for(int i = 0;i<values.length;i++){
			Class type = this.fieldType.get(i);
			Object value = null;
			if(type == String.class){
				if(!StringUtils.isBlank(values[i])){
					value = values[i];
				}
			}else{
				value = ReflectUtils.parseString2PrimitiveDataType(values[i], type);				
			}
			if(value != null){
				Method method = this.cacheMethods.get(i);
				method.invoke(target, value);				
			}
		}
		
		return target;
	}
	
	
	protected void addMethod(Method method){
		cacheMethods.add(method);
	}
	
	protected void addParamType(Class type){
		fieldType.add(type);
	}
	
	protected void setBeanConstructor(Constructor beanConstructor) {
		this.beanConstructor = beanConstructor;
	}

	/**
	 * 检查头部是否合法
	 * @param header
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static String[] checkHeader(String header)
			throws IllegalArgumentException{
		if(StringUtils.isBlank(header)){
			throw new IllegalArgumentException("Header is Blank");
		}
		if(!header.startsWith("#")){
			throw new IllegalArgumentException();
		}
		String subHeader = header.substring(1);
		
		String[] fieldNames = subHeader.split(",");
		
		if(fieldNames == null || fieldNames.length <= 0	){
			throw new IllegalArgumentException();
		}
		
		return fieldNames;
	}
	/**
	 * 获取Bean字段 字段名与字段类型的集合
	 * @param fieldNames
	 * @return
	 * @throws IllegalAccessException 
	 */
	private static Map<String,Class> getFieldTypeMap(String[] fieldNames,Class target)
			throws IllegalAccessException{
		Field[] fields = target.getDeclaredFields();
		Map<String,Class> targetFieldTypeMap = new HashMap<String, Class>();
		for(Field field:fields){
			targetFieldTypeMap.put(field.getName(), field.getType());
		}
		Map<String,Class> fieldType = new HashMap<String, Class>();
		for(String fieldName:fieldNames){
			if(!targetFieldTypeMap.containsKey(fieldName))
				throw new IllegalAccessException("fieldName: "+ fieldName + " doesn't exits");
			fieldType.put(fieldName,targetFieldTypeMap.get(fieldName));
		}
		return fieldType;
	}
}
