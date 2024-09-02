package kr.ac.kopo.account.vo;

public class AccountVO {

	private String accountNum;
	private String memberId;
	private String accountName;
	private String accountDescribe;
	private long balance;
	private String accountType;
	private String regDate;
	private String accountPwd;
	
	public AccountVO() {
		
	}
	
	public AccountVO(String accountNum, String memberId, String accountName, String accountDescribe, long balance) {
		this.accountNum = accountNum;
		this.memberId = memberId;
		this.accountName = accountName;
		this.accountDescribe = accountDescribe;
		this.balance = balance;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountDescribe() {
		return accountDescribe;
	}
	public void setAccountDescribe(String accountDescribe) {
		this.accountDescribe = accountDescribe;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getAccountPwd() {
		return accountPwd;
	}
	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}
	@Override
	public String toString() {
		return "AccountVO [accountNum=" + accountNum + ", memberId=" + memberId + ", accountName=" + accountName
				+ ", accountDescribe=" + accountDescribe + ", balance=" + balance + ", accountType=" + accountType
				+ ", regDate=" + regDate + ", accountPwd=" + accountPwd + "]";
	}
	
	
	
}
