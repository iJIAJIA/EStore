package org.estore.common.secure;

import java.util.UUID;

public class ActivatecodeUtils {
	
	//通过UUID生成激活码
	public static String generateActivatecode(){
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}
