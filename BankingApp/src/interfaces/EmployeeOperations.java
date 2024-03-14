package interfaces;

import java.util.List;
import helperpojo.AccountDetails;
import helperpojo.CustomerDetails;
import helperpojo.Transaction;
import helperpojo.UserDetails;
import util.ApplicationException;

public interface EmployeeOperations extends CustomerOperations{
	void createAccount(AccountDetails account) throws ApplicationException;
	void deleteAccount(long accountNumber) throws ApplicationException;
	void updateAccountStatus(AccountDetails account) throws ApplicationException;
	void depositFund(Transaction transfer) throws ApplicationException;
	void withdrawFund(Transaction transfer) throws ApplicationException;
	void addUser(List<UserDetails> users) throws ApplicationException;
	void addCustomer(List<CustomerDetails> customers) throws ApplicationException;
	int getCustomerId(long aadhar) throws ApplicationException;
	void deleteUser(int userId) throws ApplicationException;
	void updateMobile(UserDetails user) throws ApplicationException;
	void updateUserStatus(UserDetails user) throws ApplicationException;
	CustomerDetails getCustomerDetails(int custId) throws ApplicationException;
}

