package dbconnection;

public enum Connector {
	SERVICE_PROVIDER("mysql"),
	JDBC("jdbc"),
	LOCALHOST("localhost"),
	PORT("3306"),
	DB_NAME("BBank"),
	USERNAME("root"),
	PASSWORD("");
	
	private final String value;

    Connector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
