package com.douzone.jblog.vo;

public class CategoryVo {
	
	private Long no;
	private String name;
	private String descs;
	private String regDate;
	private String blogId;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return descs;
	}
	public void setDesc(String descs) {
		this.descs = descs;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", descs=" + descs + ", regDate=" + regDate + ", blogId="
				+ blogId + "]";
	}
}