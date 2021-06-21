package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class PostVo {
	
	private Long no;
	
	@NotEmpty
	@Length(min=1,max=15)
	private String title;
	
	@NotEmpty
	@Length(min=1,max=100)
	private String contents;
	private String regDate;
	private Long categoryNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		String abc = regDate.substring(0,10);
		this.regDate = abc;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
}