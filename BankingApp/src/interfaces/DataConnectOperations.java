package interfaces;

import util.ApplicationException;

public interface DataConnectOperations {
	void setConnection() throws ApplicationException;
	void disconnect() throws ApplicationException;
}
