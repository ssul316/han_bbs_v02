package org.han.vo;

import java.sql.Date;

import org.springframework.stereotype.Service;

@Service
public class BbsVO {
	
	private Integer bno; 		// �Խñ� ��ȣ
	private String title; 		// �Խñ� ����
	private String userid; 		// �۾��� id
	private String cont; 		// �� ����
	private Date regdate; 		// �ۼ���
	private Integer vcount; 	// �� ī��Ʈ
	private Integer rcount; 	// ���� ī��Ʈ
	private String contfile;	// ÷������
	private Integer cnt;		// ����¡ ������ �ʿ��� �� ������ ��

	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Integer getVcount() {
		return vcount;
	}
	public void setVcount(Integer vcount) {
		this.vcount = vcount;
	}
	public Integer getRcount() {
		return rcount;
	}
	public void setRcount(Integer rcount) {
		this.rcount = rcount;
	}
	public String getContfile() {
		return contfile;
	}
	public void setContfile(String contfile) {
		this.contfile = contfile;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "BbsVO [bno=" + bno + ", title=" + title + ", userid=" + userid
				+ ", cont=" + cont + ", regdate=" + regdate + ", vcount="
				+ vcount + ", rcount=" + rcount + ", contfile=" + contfile
				+ "]";
	}

}
