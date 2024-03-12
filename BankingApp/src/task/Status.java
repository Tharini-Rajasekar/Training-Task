package task;

public enum Status {
	ACTIVE(1),
	BLOCKED(2);
	
	 private final int statusCode ;
		
		Status(int statusCode ){
			this.statusCode=statusCode ;
		}

		public int getStatusCode() {
			return statusCode;
		}

}
