package com.JavaCoffee.BackEndJC.enums;

public enum Category {
	BEBIDA("Bebida"), LANCHE("Lanche"), DUPLO("Duplo");
	
	private String value;
	
	private Category(String value){
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
