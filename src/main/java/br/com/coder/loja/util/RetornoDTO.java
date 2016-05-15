package br.com.coder.loja.util;

import java.io.Serializable;

public class RetornoDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean ok;
	private T obj;
	private String msg;
	
	public RetornoDTO() {
		super();
	}
	public RetornoDTO(boolean ok, T obj, String msg) {
		super();
		this.ok = ok;
		this.obj = obj;
		this.msg = msg;
	}
	public RetornoDTO(boolean ok, String msg) {
		super();
		this.ok = ok;
		this.msg = msg;
	}
	public RetornoDTO(T obj) {
		super();
		this.ok = true;
		this.obj = obj;
	}
	public RetornoDTO(Exception e) {
		this.ok = false;
		this.obj = null;
		this.msg = e.getMessage();
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
