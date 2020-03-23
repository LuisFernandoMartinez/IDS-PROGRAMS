package com.example.demo.models;

public class Entity {
	
	private String pNombre;
	private String sNombre;
	private String phone;
	
	public Entity() {
		super();
	}

	public Entity(String pNombre, String sNombre, String phone) {
		super();
		this.pNombre = pNombre;
		this.sNombre = sNombre;
		this.phone = phone;
	}

	public String getpNombre() {
		return pNombre;
	}

	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}

	public String getsNombre() {
		return sNombre;
	}

	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Entity [pNombre=" + pNombre + ", sNombre=" + sNombre + ", phone=" + phone + "]";
	}

	
	
	
}
