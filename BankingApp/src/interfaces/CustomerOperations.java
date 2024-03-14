package interfaces;

import java.util.List;
import java.util.Map;

import helperpojo.AccountDetails;
import helperpojo.Transaction;
import util.ApplicationException;

public interface CustomerOperations extends DataConnectOperations{
	double checkBalance(long accountNumber) throws ApplicationException;
	Map<Long,AccountDetails> getAccounts(int customerId) throws ApplicationException;
	List<Transaction> getTransactionDetails(long accountNumber,int limit,int offset) throws ApplicationException;
	void transferFund(Transaction transfer) throws ApplicationException;
}
