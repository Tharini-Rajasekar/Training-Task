package dbconnection;

import querybuilder.SpecialCharacters;
import util.ApplicationException;
import util.BankMessage;

public class DBConnection {
	public static String getUrl() throws ApplicationException {
		StringBuilder url=new StringBuilder(Connector.JDBC.getValue());
		url.append(SpecialCharacters.COLON);
		url.append(Connector.SERVICE_PROVIDER.getValue());
		url.append(SpecialCharacters.COLON).append(SpecialCharacters.FORWARD_SLASH).append(SpecialCharacters.FORWARD_SLASH);
		url.append(Connector.LOCALHOST.getValue()).append(SpecialCharacters.COLON);
		url.append(Connector.PORT.getValue()).append(SpecialCharacters.FORWARD_SLASH).append(Connector.DB_NAME.getValue());
		return url.toString();
	}
	public static String getDriver(String serviceProvider) throws ApplicationException{
		if(ServiceProvider.MYSQL.getServiceProvider()==serviceProvider) {
			return Driver.MYSQL.getDriverClassName();
		}
		if(ServiceProvider.POSTGRESQL.getServiceProvider()==serviceProvider) {
			return Driver.POSTGRESQL.getDriverClassName();
		}
		throw new ApplicationException(BankMessage.NO_SERVICE_PROVIDER.getMessage());
	}

}
