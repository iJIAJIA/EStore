package org.estore.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtils {
	private static Logger logger = LoggerFactory.getLogger(PropsUtils.class);
	
	/**
	 * 讲props的键值加载到指定Constants的public static String字段上
	 * @param constant
	 * @param propsPath
	 */
	public static void loadProps2Constants(Class constant,String propsPath){
		InputStream in  = constant.getClass().getResourceAsStream(propsPath);
		Properties props = new Properties();
		try {
			props.load(in);
			Field[] fields = constant.getFields();
			logger.info("load constant[{}] from {}",constant.getName(),propsPath);
			for(Field field:fields){
				if(String.class.equals(field.getType())){
					String value = props.getProperty(field.getName());
					if(value == null){
						logger.warn("Could not found field[{}] in {}",field.getName(),propsPath);
						continue;
					}
					logger.debug("set value[{}] to field[{}]",value,field.getName());
					field.set(null, value);
				}
			}
			logger.info("end loading!");
			in.close();
		} catch (IOException e) {
			logger.error("Failed to load props",e);
		} catch(Exception e){
			logger.error("Exception occured while setting value to field ",e);
		}
		
	}
}
