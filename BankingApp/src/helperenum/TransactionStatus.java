package helperenum;

public enum TransactionStatus {
	SUCCESS(1),
	FAILED(2);
	
	private final int statusCode ;
	
	TransactionStatus(int statusCode ){
		this.statusCode=statusCode ;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
