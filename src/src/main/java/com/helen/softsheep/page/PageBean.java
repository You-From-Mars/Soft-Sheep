package com.helen.softsheep.page;

import java.util.List;

public class PageBean<T> {
	private int pageNum;
	private int pageSize;
	private int totalRecord;
	private int totalPage;
	private List<T> list;
	public PageBean(int pageNum,int pageSize, int totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		if(totalRecord%pageSize == 0){
			this.pageNum = this.totalRecord % this.pageSize;
		} else {
			this.pageNum = this.totalRecord % this.pageSize + 1;
		}
	}
	public int getPageNum() {
		 return pageNum;
	}
	public void setPageNum(int pageNum) {
		 this.pageNum = pageNum;
	}
	public int getPageSize() {
		 return pageSize;
	}
	public void setPageSize(int pageSize) {
		 this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		 return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		 this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		 return totalPage;
	}
	public void setTotalPage(int totalPage) {
		 this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
