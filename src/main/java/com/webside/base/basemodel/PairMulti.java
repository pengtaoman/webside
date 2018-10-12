package com.webside.base.basemodel;

import java.io.Serializable;


public class PairMulti implements Serializable{
	private static final long serialVersionUID = -875303893747050424L;
	
	private String id;
	//private String name;
	private Object val1;
	public Object getVal1() {
		if (this.val1 == null) {
			return "";
		}
		return val1;
	}
	public void setVal1(Object val1) {
		this.val1 = val1;
	}
	public Object getVal2() {
		if (this.val2 == null) {
			return "";
		}
		return val2;
	}
	public void setVal2(Object val2) {
		this.val2 = val2;
	}
	public Object getVal3() {
		if (this.val3 == null) {
			return "";
		}
		return val3;
	}
	public void setVal3(Object val3) {
		this.val3 = val3;
	}
	public Object getVal4() {
		if (this.val4 == null) {
			return "";
		}
		return val4;
	}
	public void setVal4(Object val4) {
		this.val4 = val4;
	}
	public Object getVal5() {
		if (this.val5 == null) {
			return "";
		}
		return val5;
	}
	public void setVal5(Object val5) {
		this.val5 = val5;
	}
	private Object val2;
	private Object val3;
	private Object val4;
	private Object val5;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
