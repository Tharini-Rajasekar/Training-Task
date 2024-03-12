package task;

public enum UserLevel {
	CUSTOMER(1),
	EMPLOYEE(2),
	ADMIN(3);
	
    private final int levelCode ;
	
	UserLevel(int levelCode ){
		this.levelCode=levelCode ;
	}

	public int getLevelCode() {
		return levelCode;
	}

}
