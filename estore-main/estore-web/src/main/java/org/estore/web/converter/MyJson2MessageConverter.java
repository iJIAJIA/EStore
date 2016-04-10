package org.estore.web.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


public class MyJson2MessageConverter extends
		MappingJackson2HttpMessageConverter {
	private static Logger logger = LoggerFactory.getLogger(MyJson2MessageConverter.class);
	
	@Override
	public boolean canWrite(Class<?> arg0, MediaType arg1) {
		logger.info("canWrite invoke...");
		return super.canWrite(arg0, arg1);
	}
	
	@Override
	public boolean canRead(Type arg0, Class<?> arg1, MediaType arg2) {
		logger.info("canRead invoke...");
		return super.canRead(arg0, arg1, arg2);
	}
	
	@Override
	public Object read(Type type, Class<?> contextClass,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		logger.info("read invoke...");
		return super.read(type, contextClass, inputMessage);
	}
	
	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		logger.debug("writeInternal invoke...");
		super.writeInternal(object, outputMessage);
	}

}
