package task;

import util.Helper;
import util.ApplicationException;

public class DBUtilities {

	public static String getUrl(DBConnector object) throws ApplicationException {
		Helper.nullCheck(object);
		String host=object.getHost();
		String name=object.getDatabaseName();
		Helper.nullCheck(host);
		Helper.nullCheck(name);
		int serviceProvider=object.serviceProvider;
		StringBuilder url=new StringBuilder(ConnectionDetails.JDBC);
		url.append(SpecialCharacters.COLON);
		if(ServiceProvider.MYSQL.getServiceProvider()==serviceProvider) {
			url.append(ConnectionDetails.MYSQL);
		}
		else if(ServiceProvider.POSTGRESQL.getServiceProvider()==serviceProvider) {
			url.append(ConnectionDetails.POSTGRESQL);
		}
		else {
			throw new ApplicationException(ErrorMessage.NO_SERVICE_PROVIDER.getMessage());
		}
		url.append(SpecialCharacters.COLON).append(SpecialCharacters.FORWARD_SLASH).append(SpecialCharacters.FORWARD_SLASH);
		url.append(host.toLowerCase()).append(SpecialCharacters.COLON);
		url.append(object.getPort()).append(SpecialCharacters.FORWARD_SLASH).append(name);
		return url.toString();
	}
	public static String getDriver(int serviceProvider) throws ApplicationException{
		if(ServiceProvider.MYSQL.getServiceProvider()==serviceProvider) {
			return Driver.MYSQL.getDriverClassName();
		}
		if(ServiceProvider.POSTGRESQL.getServiceProvider()==serviceProvider) {
			return Driver.POSTGRESQL.getDriverClassName();
		}
		throw new ApplicationException(ErrorMessage.NO_SERVICE_PROVIDER.getMessage());
	}
	public static String buildEmployeeTable(String tableName) throws ApplicationException {
		Helper.nullCheck(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableDetails.EMP_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.AUTO_INCREMENT).append(SpecialCharacters.SPACE).append(SQLKeywords.PRIMARY_KEY).append(SpecialCharacters.COMMA);
		query.append(TableDetails.NAME).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.NAME_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableDetails.DEPARTMENT).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.DEPT_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableDetails.EMAIL).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.EMAIL_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.UNIQUE_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.COMMA);
		query.append(TableDetails.MOBILE).append(SpecialCharacters.SPACE).append(SQLDataTypes.NUMERIC).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.UNIQUE_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
	public static String buildDependentTable(String childTable,String parentTable) throws ApplicationException {
		Helper.nullCheck(parentTable);
		Helper.nullCheck(childTable);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(childTable).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableDetails.DEP_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.AUTO_INCREMENT).append(SpecialCharacters.SPACE).append(SQLKeywords.PRIMARY_KEY).append(SpecialCharacters.COMMA);
		query.append(TableDetails.DEPENDENT_NAME).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.NAME_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableDetails.AGE).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(TableDetails.RELATION).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.RELATION_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableDetails.EMP_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.EMP_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(parentTable).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableDetails.EMP_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.CASCADE).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
}




