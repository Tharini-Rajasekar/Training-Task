package helperpojo;

public class Transaction {
	private String txnId;
	private long primaryAccount;
	private long secondaryAccount;
	private String txnType;
	private double amount;
	private long timeInMillis;
	private String description;
	private String txnStatus;
	private double balance;
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public long getPrimaryAccount() {
		return primaryAccount;
	}
	public void setPrimaryAccount(long primaryAccount) {
		this.primaryAccount = primaryAccount;
	}
	public long getSecondaryAccount() {
		return secondaryAccount;
	}
	public void setSecondaryAccount(long secondaryAccount) {
		this.secondaryAccount = secondaryAccount;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public long getTimeInMillis() {
		return timeInMillis;
	}
	public void setTimeInMillis(long currentTimeMillis) {
		this.timeInMillis = currentTimeMillis;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getTxnStatus() {
		return txnStatus;
	}
	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
