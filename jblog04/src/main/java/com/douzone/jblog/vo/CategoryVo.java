package com.douzone.jblog.vo;

public class CategoryVo {
	
	private Long no;
	private String name;
	private String descs;
	private String regDate;
	private String blogId;
	private int count;
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
	public String getDescs() {
		return descs;
	}
	public void setDescs(String descs) {
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", descs=" + descs + ", regDate=" + regDate + ", blogId="
				+ blogId + ", count=" + count + "]";
	}
}