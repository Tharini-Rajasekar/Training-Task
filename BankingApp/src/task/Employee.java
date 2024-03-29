package task;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import helperenum.TransactionStatus;
import helperenum.TransactionType;
import helperenum.UserLevel;
import helperpojo.AccountDetails;
import helperpojo.CustomerDetails;
import helperpojo.Transaction;
import helperpojo.UserDetails;
import interfaces.EmployeeOperations;
import querybuilder.SQLKeywords;
import querybuilder.SpecialCharacters;
import querybuilder.TableProp;
import util.ApplicationException;
import util.BankMessage;
import util.Helper;

public class Employee extends Customer implements EmployeeOperations{


	public void addUser(List<UserDetails> users) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.NAME).append(SpecialCharacters.COMMA);
		query.append(TableProp.DOB).append(SpecialCharacters.COMMA);
		query.append(TableProp.GENDER).append(SpecialCharacters.COMMA);
		query.append(TableProp.EMAIL).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_LEVEL).append(SpecialCharacters.COMMA);
		query.append(TableProp.MOBILE).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			for(UserDetails user:users){
				prepStatement.setString(1,user.getName());
				Date date=Date.valueOf(user.getDob());
				prepStatement.setDate(2, date);
				prepStatement.setString(3,user.getGender());
				prepStatement.setString(4,user.getEmail());
				prepStatement.setInt(5,UserLevel.CUSTOMER.getLevelCode());
				prepStatement.setLong(6,user.getMobile());
				prepStatement.addBatch();
			}
			prepStatement.executeBatch();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void addCustomer(List<CustomerDetails> customers) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(TableProp.CUSTOMER_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.USER_ID).append(SpecialCharacters.COMMA);
		query.append(TableProp.AADHAR).append(SpecialCharacters.COMMA);
		query.append(TableProp.PAN).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			for(CustomerDetails customer:customers) {
				prepStatement.setInt(1,customer.getId());
				prepStatement.setLong(2,customer.getAadhar());
				prepStatement.setString(3,customer.getPan());
				prepStatement.addBatch();
			}
			prepStatement.executeBatch();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void createAccount(AccountDetails account) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.USER_ID).append(SpecialCharacters.COMMA);
		query.append(TableProp.ACCOUNT_TYPE).append(SpecialCharacters.COMMA);
		query.append(TableProp.BALANCE).append(SpecialCharacters.COMMA);
		query.append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,account.getId());
			prepStatement.setInt(2,account.getAccountType());
			prepStatement.setDouble(3,account.getBalance());
			prepStatement.setInt(4,account.getBranchId());
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.ACCOUNT_CREATE_ERROR.getMessage(),e);		
		}
	}
	public int getCustomerId(long aadhar) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.USER_ID).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.CUSTOMER_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.AADHAR).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,aadhar);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				if(resultSet.next()) {
					return resultSet.getInt(TableProp.USER_ID);
				}
				else {
					throw new ApplicationException(BankMessage.INVALID_USER.getMessage());
				}
			}
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void deleteAccount(long accountNumber) throws ApplicationException {
		checkConnection();
		double balance=checkBalance(accountNumber);
		if(balance<1) {
			StringBuilder query=new StringBuilder(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
			query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
			query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
			String sql=query.toString();
			try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
				prepStatement.setLong(1,accountNumber);
				prepStatement.executeUpdate();
			}
			catch(SQLException e) {
				throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
			}
		}
		else {
			throw new ApplicationException(BankMessage.ACCOUNT_DELETE_ERROR.getMessage());
		}
	}
	public void deleteUser(int custId) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE).append(SQLKeywords.AND);
		query.append(TableProp.USER_LEVEL).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,custId);
			prepStatement.setString(2,TableProp.CUSTOMER);
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void updateMobile(UserDetails user) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.MOBILE).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,user.getMobile());
			prepStatement.setInt(2,user.getId());
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void updateUserStatus(UserDetails user) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_STATUS).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE).append(SQLKeywords.AND);
		query.append(TableProp.USER_LEVEL).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);

		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,user.getUserStatus());
			prepStatement.setInt(2,user.getId());
			prepStatement.setString(3,TableProp.CUSTOMER);
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
	public void updateAccountStatus(AccountDetails account) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_STATUS).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,account.getStatus());
			prepStatement.setLong(2,account.getAccountNumber());
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
	public void depositFund(Transaction transfer) throws ApplicationException{
		checkConnection();
		long account=transfer.getPrimaryAccount();
		double depositAmount=transfer.getAmount();
		int id=getTransactionId();
		StringBuilder txnId=new StringBuilder(TableProp.TXN_ID_CODE).append(id); 
		transfer.setTxnId(txnId.toString());
		transfer.setTxnType(TransactionType.DEPOSIT.getTypeCode());
		try {
			connect.setAutoCommit(false);
			if(checkAccount(account)) {
				depositAmount(account,depositAmount);
				connect.commit();
				transfer.setTxnStatus(TransactionStatus.SUCCESS.getStatusCode());
				addTransaction(transfer);
			}
			else {
				throw new ApplicationException(BankMessage.INVALID_ACCOUNT.getMessage());
			}	
		}			
		catch(SQLException e) {
			try {
				connect.rollback();
				transfer.setTxnStatus(TransactionStatus.FAILED.getStatusCode());
				addTransaction(transfer);
			} 
			catch (SQLException e1) {
				throw new ApplicationException(BankMessage.ROLLBACK_ERROR.getMessage(),e1);
			}
			throw new ApplicationException(BankMessage.TRANSACTION_DECLINED.getMessage(),e);		
		}
		finally {
			try {
				connect.setAutoCommit(true);
			} 
			catch (SQLException e) {
				throw new ApplicationException(BankMessage.COMMIT_ERROR.getMessage(),e);
			}
		}	
	}
	public void withdrawFund(Transaction transfer) throws ApplicationException{
		checkConnection();
		long account=transfer.getPrimaryAccount();
		double withdrawAmount=transfer.getAmount();
		int id=getTransactionId();
		StringBuilder txnId=new StringBuilder(TableProp.TXN_ID_CODE).append(id); 
		transfer.setTxnId(txnId.toString());
		transfer.setTxnType(TransactionType.WITHDRAW.getTypeCode());
		try {
			connect.setAutoCommit(false);
			if(checkAccount(account)) {
				withdrawAmount(account,withdrawAmount);
				connect.commit();
				transfer.setTxnStatus(TransactionStatus.SUCCESS.getStatusCode());
				addTransaction(transfer);
			}
			else {
				throw new ApplicationException(BankMessage.INVALID_ACCOUNT.getMessage());
			}	
		}			
		catch(SQLException e) {
			try {
				connect.rollback();
				transfer.setTxnStatus(TransactionStatus.FAILED.getStatusCode());
				addTransaction(transfer);
			} 
			catch (SQLException e1) {
				throw new ApplicationException(BankMessage.ROLLBACK_ERROR.getMessage(),e1);
			}
			throw new ApplicationException(BankMessage.TRANSACTION_DECLINED.getMessage(),e);		
		}
		finally {
			try {
				connect.setAutoCommit(true);
			} 
			catch (SQLException e) {
				throw new ApplicationException(BankMessage.COMMIT_ERROR.getMessage(),e);
			}
		}
	}
		public CustomerDetails getCustomerDetails(int custId) throws ApplicationException {
			checkConnection();
			StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE);
			query.append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
			query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
			query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE);
			query.append(SQLKeywords.JOIN).append(SpecialCharacters.SPACE).append(TableProp.CUSTOMER_TABLE).append(SpecialCharacters.SPACE);
			query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
			query.append(TableProp.USER_TABLE).append(SpecialCharacters.PERIOD).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS);
			query.append(TableProp.CUSTOMER_TABLE).append( SpecialCharacters.PERIOD).append(TableProp.USER_ID).append(SpecialCharacters.SPACE);
			query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
			query.append(TableProp.USER_TABLE).append(SpecialCharacters.PERIOD).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS);
			query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE).append(SpecialCharacters.SEMICOLON);
			try(PreparedStatement prepStatement = connect.prepareStatement(query.toString())) {
				prepStatement.setLong(1,custId);
				try(ResultSet resultSet = prepStatement.executeQuery()){
					CustomerDetails customer=new CustomerDetails();
					while(resultSet.next()) {
						customer.setName(resultSet.getString(TableProp.NAME));
						Date date=resultSet.getDate(TableProp.DOB);
						customer.setDob(date.toLocalDate());
						customer.setGender(resultSet.getString(TableProp.GENDER));
						customer.setEmail(resultSet.getString(TableProp.EMAIL));
						customer.setMobile(resultSet.getLong(TableProp.MOBILE));
						customer.setAadhar(resultSet.getLong(TableProp.AADHAR));
						customer.setPan(resultSet.getString(TableProp.PAN));
						customer.setUserStatus(resultSet.getInt(TableProp.USER_STATUS));
					}
					return customer;
				}
			}
				catch(SQLException e) {
					throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
				}	
		}

}
