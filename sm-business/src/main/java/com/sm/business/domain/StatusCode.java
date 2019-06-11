package com.sm.business.domain;

public enum StatusCode{
	SUCCESS("0"), 
	FAILURE("1"), 
	TIMEOUT("301"), 
	NOTFOUND("404"),
	EXCEPTION("500");

	private String desc;

	private StatusCode(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
