package kr.ac.kopo.vo;

import java.io.Serializable;

public class MemberVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String pwd;
	private String name;
	private String hp;
	private String personalNum;
	private String joinDate;
	
	public MemberVO() {
		
	}
	
	public MemberVO(String id, String pwd, String name, String hp, String personalNum) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.hp = hp;
		this.personalNum = personalNum;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getPersonalNum() {
		return personalNum;
	}
	public void setPersonalNum(String personalNum) {
		this.personalNum = personalNum;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", hp=" + hp + ", personalNum=" + personalNum
				+ ", joinDate=" + joinDate + "]";
	}
	
}
