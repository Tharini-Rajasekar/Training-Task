package task;

public enum ServiceProvider {
	MYSQL(1),
	POSTGRESQL(2);
	
	private final int serviceProvider ;
	
	ServiceProvider(int serviceProvider ){
		this.serviceProvider=serviceProvider ;
	}

	public int getServiceProvider() {
		return serviceProvider;
	}

}
