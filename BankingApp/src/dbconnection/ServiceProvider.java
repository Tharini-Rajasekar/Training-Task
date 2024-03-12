package dbconnection;

public enum ServiceProvider {
	MYSQL("mysql"),
	POSTGRESQL("postgresql");
	
	private final String serviceProvider ;
	
	ServiceProvider(String serviceProvider ){
		this.serviceProvider=serviceProvider ;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}
}
