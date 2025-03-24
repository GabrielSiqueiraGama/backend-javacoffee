package com.JavaCoffee.BackEndJC.enums;

public enum Status {

	ATIVO("Ativo"), INATIVO("Inativo");
	
	private String value;
	
	private Status(String value){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
