package org.estore.web.model;

public class IdentityCode {
	
	private String code;
	
	private Long deadTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Long deadTime) {
		this.deadTime = deadTime;
	}

	@Override
	public String toString() {
		return "IdentityCode [code=" + code + ", deadTime=" + deadTime + "]";
	}

	
	
	
}
