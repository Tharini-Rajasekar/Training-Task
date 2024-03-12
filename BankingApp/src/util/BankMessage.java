package util;

public enum BankMessage {
	INVALID_ACCOUNT("Invalid Account Number"),
	BALANCE_ISSUE("Insufficient Balance"),
	ROLLBACK_ERROR("Error Occurred During Transaction Rollback"),
	COMMIT_ERROR("Error Resetting the AutoCommit"),
	UPDATE_ERROR("Updation Error"),
	TRANSACTION_DECLINED("Your Transaction Declined"),
	INVALID_USERNAME("Check Your UserName"),
	INVALID_USER("Invalid User"),
	INVALID_PWD("Invalid Password"),
	ACCOUNT_DELETE_ERROR("Couldn't Delete"),
	USER_BLOCKED("User is Blocked"),
	
	NO_SERVICE_PROVIDER("No Available Service Provider."),
	DATABASE_CONNECTION_ERROR("Error Connecting to the Database."),
	STATEMENT_ERROR("Error in Preparing the Query Statement."),
	DATA_RETRIEVAL_ERROR("Error in Retrieving Data from the Database."),
	OPERATOR_ERROR("Invalid Operator Used."),
	DISCONNECT_ERROR("Error in Disconnecting."),
	COLUMN_ERROR("Column Not Found."),
	LOG_ERROR("Unable to Initialize Log.");
	
	private final String message;

    BankMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
