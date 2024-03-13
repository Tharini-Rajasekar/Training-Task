package task;

import querybuilder.SQLDataTypes;
import querybuilder.SQLKeywords;
import querybuilder.SpecialCharacters;
import querybuilder.TableProp;

public class TableCreator {
	
	public static String buildUserTable() {
		StringBuilder query=new StringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.USER_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.AUTO_INCREMENT).append(SpecialCharacters.SPACE).append(SQLKeywords.PRIMARY_KEY).append(SpecialCharacters.COMMA);
		query.append(TableProp.NAME).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.NAME_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.DOB).append(SpecialCharacters.SPACE).append(SQLDataTypes.DATE).append(SpecialCharacters.COMMA);
		query.append(TableProp.GENDER).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.GENDER_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.EMAIL).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.EMAIL_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.MOBILE).append(SpecialCharacters.SPACE).append(SQLDataTypes.NUMERIC).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.UNIQUE_KEY).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_LEVEL).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_STATUS).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DEFAULT).append(SpecialCharacters.SPACE).append(SpecialCharacters.SINGLE_QUOTE).append(TableProp.ACTIVE).append(SpecialCharacters.SINGLE_QUOTE).append(SpecialCharacters.COMMA);
		query.append(TableProp.PASSWORD).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.PWD_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.CLOSE_PARENTHESIS);
		query.append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
	
	public static String buildBranchTable() {
		StringBuilder query=new StringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.BRANCH_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.AUTO_INCREMENT).append(SpecialCharacters.SPACE).append(SQLKeywords.PRIMARY_KEY).append(SpecialCharacters.COMMA);
		query.append(TableProp.IFSC).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.MANAGER).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.NAME_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(TableProp.LOCATION).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LOCATION_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE).append(SQLKeywords.NULL).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
	    return query.toString();
	}
	
	public static String buildEmployeeTable() {
		StringBuilder query=new StringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(TableProp.EMPLOYEE_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.USER_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(TableProp.BRANCH_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.CASCADE).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE).append(SQLKeywords.NULL).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
	
	public static String buildCustomerTable() {
		StringBuilder query=new StringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(TableProp.CUSTOMER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.USER_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(TableProp.AADHAR).append(SpecialCharacters.SPACE).append(SQLDataTypes.BIGINT).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.UNIQUE_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.COMMA);
		query.append(TableProp.PAN).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.UNIQUE_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.CASCADE).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
	
	public static String buildAccountDetails() {
		StringBuilder query=new StringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.SPACE).append(SQLDataTypes.BIGINT).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.AUTO_INCREMENT).append(SpecialCharacters.SPACE).append(SQLKeywords.PRIMARY_KEY).append(SpecialCharacters.COMMA);
		query.append(TableProp.ACCOUNT_TYPE).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.NAME_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.BALANCE).append(SpecialCharacters.SPACE).append(SQLDataTypes.DECIMAL);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.WHOLE).append(SpecialCharacters.COMMA).append(TableProp.FRACTION).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(TableProp.BRANCH_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.INT).append(SpecialCharacters.COMMA);
		query.append(TableProp.ACCOUNT_STATUS).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DEFAULT).append(SpecialCharacters.SPACE).append(SpecialCharacters.SINGLE_QUOTE).append(TableProp.ACTIVE).append(SpecialCharacters.SINGLE_QUOTE).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.CASCADE).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE).append(SQLKeywords.NULL).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.UNIQUE_KEY).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.USER_ID).append(SpecialCharacters.COMMA).append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS);
		query.append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE).append(SQLKeywords.AUTO_INCREMENT).append(SpecialCharacters.EQUALS);
		query.append(TableProp.ACCOUNT_CODE).append(SpecialCharacters.SPACE).append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
	
	public static String buildTranscationDetails() {
		StringBuilder query=new StringBuilder(SQLKeywords.CREATE_TABLE).append(SpecialCharacters.SPACE);
		query.append(TableProp.TXN_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.TXN_ID).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.PRIMARY_ACCOUNT).append(SpecialCharacters.SPACE).append(SQLDataTypes.BIGINT).append(SpecialCharacters.COMMA);
		query.append(TableProp.SECONDARY_ACCOUNT).append(SpecialCharacters.SPACE).append(SQLDataTypes.BIGINT).append(SpecialCharacters.COMMA);
		query.append(TableProp.AMOUNT).append(SpecialCharacters.SPACE).append(SQLDataTypes.DECIMAL);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.WHOLE).append(SpecialCharacters.COMMA).append(TableProp.FRACTION).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.TXN_TYPE).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);	
		query.append(TableProp.DESCRIPTION).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.EMAIL_LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.TXN_STATUS).append(SpecialCharacters.SPACE).append(SQLDataTypes.VARCHAR).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.LENGTH).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(TableProp.TIMEMILLISECONDS).append(SpecialCharacters.SPACE).append(SQLDataTypes.BIGINT).append(SpecialCharacters.COMMA);
		query.append(TableProp.BALANCE).append(SpecialCharacters.SPACE).append(SQLDataTypes.DECIMAL);
		query.append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.WHOLE).append(SpecialCharacters.COMMA).append(TableProp.FRACTION).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.COMMA);
		query.append(SQLKeywords.FOREIGN_KEY).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.PRIMARY_ACCOUNT).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.REFERENCES).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS).append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		return query.toString();
	}
	


}
