package com.wzg.server.mina.tcp.protocol;

public class ProtocolData {

	protected int function_id;
	protected String jsonString;

	public ProtocolData() {
		super();
	}

	public ProtocolData(int function_id, String jsonString) {
		super();
		this.function_id = function_id;
		this.jsonString = jsonString;
	}

	public int getFunction_id() {
		return function_id;
	}

	public void setFunction_id(int function_id) {
		this.function_id = function_id;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

}
