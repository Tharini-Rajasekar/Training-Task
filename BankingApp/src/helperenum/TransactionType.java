package helperenum;

public enum TransactionType {
	TRANSFER(1),
	DEPOSIT(2),
	WITHDRAW(3);
	
    private final int typeCode ;
	
	TransactionType(int typeCode ){
		this.typeCode=typeCode;
	}

	public int getTypeCode() {
		return typeCode;
	}
}
