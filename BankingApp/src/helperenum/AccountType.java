package helperenum;

public enum AccountType {
	SAVINGS(1),
	SALARY(2),
	DEMAT(3);
	
	private final int typeCode ;
	
	AccountType(int typeCode ){
		this.typeCode=typeCode;
	}

	public int getTypeCode() {
		return typeCode;
	}
	
}
