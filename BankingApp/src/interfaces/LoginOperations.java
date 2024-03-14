package interfaces;

import helperpojo.UserDetails;
import util.ApplicationException;

public interface LoginOperations extends DataConnectOperations {
	String signIn(int userId) throws ApplicationException;
	int getUserLevel(int userId) throws ApplicationException;
	int getBranchId(int userId) throws ApplicationException;
	void signUp(UserDetails user) throws ApplicationException;
}
