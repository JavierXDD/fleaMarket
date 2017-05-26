package com.wenLi.util.entity;

import java.util.List;

public class Page<T> {

	private int pc;// 当前页码page code
//	private int tp;// 总页数total page
	private int tr;// 总记录数total record
	private int ps;// 每页记录数page size
	private List<T> beanList;// 当前页的记录
	private String url;//条件
	
	//计算总页数
	public int getTp() {
		// 通过总记录数和每页记录数来计算总页数
		int tp = tr / ps;
		return tr%ps==0 ? tp : tp+1;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "PageBean [pc=" + pc + ", tr=" + tr + ", ps=" + ps
				+ ", beanList=" + beanList + ", url=" + url + "]";
	}	
	
}
