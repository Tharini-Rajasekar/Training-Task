package task;

public enum Driver {
	MYSQL("com.mysql.jdbc.Driver"),
	POSTGRESQL("org.postgresql.Driver");

	private final String driverClassName;

	Driver(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
}
