package org.estore.web.model;
/**
 * 返回json结果类载体
 * @author iJIAJIA
 *
 */
public class ReturnJsonResult {
	
	private String returnCode;
	
	private String returnMsg;
	
	private Object returnData;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	
	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}

	@Override
	public String toString() {
		return "ReturnJsonResult [returnCode=" + returnCode + ", returnMsg="
				+ returnMsg + ", returnData=" + returnData + "]";
	}

	
}
