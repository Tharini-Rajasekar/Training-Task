package task;

public enum ErrorMessage {
	NO_SERVICE_PROVIDER("No Available Service Provider."),
	DATABASE_CONNECTION_ERROR("Error Connecting to the Database."),
	STATEMENT_ERROR("Error in Preparing the Query Statement."),
	DATA_RETRIEVAL_ERROR("Error in Retrieving Data from the Database."),
	OPERATOR_ERROR("Invalid Operator Used."),
	DISCONNECT_ERROR("Error in Disconnecting."),
	COLUMN_ERROR("Column Not Found."),
	LOG_ERROR("Unable to Initialize Log.");
	
	private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
