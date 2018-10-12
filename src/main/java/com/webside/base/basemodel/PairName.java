package com.webside.base.basemodel;

import java.io.Serializable;


public class PairName implements Serializable{
	private static final long serialVersionUID = -875303893747050424L;
	
	private String id;
	//private String name;
	private String text;
	
	public PairName(){}
	public PairName(Long id, String val){
		this.id = String.valueOf(id);
		this.text = val;
	}
	public PairName(String id, String val){
		this.id = id;
		this.text = val;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setId(Long id) {
		this.id = String.valueOf(id);
	}
	public String getName() {
		return text;
	}
	public void setName(String name) {
		this.text = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
