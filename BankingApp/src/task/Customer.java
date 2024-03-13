package task;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import dbconnection.Connector;
import dbconnection.DBConnection;
import helperenum.Status;
import helperpojo.Transaction;
import querybuilder.SQLKeywords;
import querybuilder.SpecialCharacters;
import querybuilder.TableProp;
import util.ApplicationException;
import util.BankMessage;
import util.Helper;

public class Customer {
	protected int id;
	
	protected Connection connect=null;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	protected Connection connectToDB() throws ApplicationException {
		try {
			String driver=DBConnection.getDriver(Connector.SERVICE_PROVIDER.getValue());
			String url = DBConnection.getUrl();
			String userName=Connector.USERNAME.getValue();
			String password=Connector.PASSWORD.getValue();
			Class.forName(driver);
			return DriverManager.getConnection(url, userName, password);
		}
		catch(ClassNotFoundException | SQLException e){
			throw new ApplicationException(BankMessage.DATABASE_CONNECTION_ERROR.getMessage(),e);
		}
	}
	public void setConnection() throws ApplicationException{
		if(connect==null) {
			connect=connectToDB();
		}
	}
	protected void checkConnection() throws ApplicationException {
		if(connect==null) {
			throw new ApplicationException(BankMessage.DATABASE_CONNECTION_ERROR.getMessage());
		}
	}
	public void disconnectDB() throws ApplicationException{
		Helper.nullCheck(connect);
		try {
			connect.close();
		} 
		catch (SQLException e) {
			throw new ApplicationException(BankMessage.DISCONNECT_ERROR.getMessage(),e);
		}
	}

	public List<Long> getAccounts() throws ApplicationException{
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,id);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				List<Long> accounts=Helper.getArrayList();
				while(resultSet.next()) {
					long accountNumber=resultSet.getLong(TableProp.ACCOUNT_NUMBER);
					Helper.addElement(accounts,accountNumber);
				}
				return accounts;
			}
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public double checkBalance(long accountNumber) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.BALANCE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,accountNumber);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				if(resultSet.next()) {
					return resultSet.getDouble(TableProp.BALANCE);
				}
				else {
					throw new ApplicationException(BankMessage.INVALID_ACCOUNT.getMessage());
				}
			}
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public List<Transaction> getTransactionDetails(long accountNumber,int limit,int offset) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.TXN_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.PRIMARY_ACCOUNT).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ORDER_BY).append(SpecialCharacters.SPACE).append(TableProp.TIMEMILLISECONDS).append(SpecialCharacters.SPACE).append(SQLKeywords.DESCENDING).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.OFFSET).append(SpecialCharacters.SPACE).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		List<Transaction> transactions=Helper.getArrayList();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,accountNumber);
			prepStatement.setInt(2,limit);
			prepStatement.setInt(3,offset);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				while(resultSet.next()) {
					Transaction transaction=new Transaction();
					transaction.setTxnId(resultSet.getString(TableProp.TXN_ID));
					transaction.setPrimaryAccount(resultSet.getLong(TableProp.PRIMARY_ACCOUNT));
					transaction.setSecondaryAccount(resultSet.getLong(TableProp.SECONDARY_ACCOUNT));
					transaction.setTxnType(resultSet.getString(TableProp.TXN_TYPE));
					transaction.setAmount(resultSet.getDouble(TableProp.AMOUNT));
					transaction.setTimeInMillis(resultSet.getLong(TableProp.TIMEMILLISECONDS));
					transaction.setDescription(resultSet.getString(TableProp.DESCRIPTION));
					transaction.setTxnStatus(resultSet.getString(TableProp.TXN_STATUS));
					transaction.setBalance(resultSet.getDouble(TableProp.BALANCE));
					
					Helper.addElement(transactions,transaction);
				}
			} 
		} 
		catch (SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
		return transactions;
	}

	public void transferFund(Transaction transfer) throws ApplicationException{
		checkConnection();
		long sender=transfer.getPrimaryAccount();
		long receiver=transfer.getSecondaryAccount();
		double transferAmount=transfer.getAmount();
		int id=getTransactionId();
		StringBuilder txnId=new StringBuilder(TableProp.TXN_ID_CODE).append(id); 
		transfer.setTxnId(txnId.toString());
		try {
			connect.setAutoCommit(false);
			if(sender!=receiver&&checkAccount(sender)&&checkAccount(receiver)) {
			withdrawAmount(sender,transferAmount);
			depositAmount(receiver,transferAmount);
			connect.commit();
			transfer.setTxnStatus(TableProp.SUCCESS);
			addTransaction(transfer);
			transfer.setPrimaryAccount(receiver);
			transfer.setSecondaryAccount(sender);
			addTransaction(transfer);
			}
			else {
					throw new ApplicationException(BankMessage.INVALID_ACCOUNT.getMessage());
			}	
		}			
		catch(SQLException e) {
			try {
				connect.rollback();
				transfer.setTxnStatus(TableProp.FAILED);
				addTransaction(transfer);
				transfer.setPrimaryAccount(receiver);
				transfer.setSecondaryAccount(sender);
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
			} catch (SQLException e) {
				throw new ApplicationException(BankMessage.COMMIT_ERROR.getMessage(),e);
			}
		}	
	}
	public void transferFundAcrossBank(Transaction transfer) throws ApplicationException{
		checkConnection();
		long sender=transfer.getPrimaryAccount();
		long receiver=transfer.getSecondaryAccount();
		double transferAmount=transfer.getAmount();
		int id=getTransactionId();
		StringBuilder txnId=new StringBuilder(TableProp.TXN_ID_CODE).append(id); 
		transfer.setTxnId(txnId.toString());
		try {
			connect.setAutoCommit(false);
			withdrawAmount(sender,transferAmount);
			//depositAmount(receiver,transferAmount);
			connect.commit();
			transfer.setTxnStatus(TableProp.SUCCESS);
			addTransaction(transfer);
			//transfer.setPrimaryAccount(receiver);
			//transfer.setSecondaryAccount(sender);
			//addTransaction(transfer);
		}			
		catch(SQLException e) {
			try {
				connect.rollback();
				transfer.setTxnStatus(TableProp.FAILED);
				addTransaction(transfer);
				//transfer.setPrimaryAccount(receiver);
				//transfer.setSecondaryAccount(sender);
				//addTransaction(transfer);
			} catch (SQLException e1) {
				throw new ApplicationException(BankMessage.ROLLBACK_ERROR.getMessage(),e1);
			}
			throw new ApplicationException(BankMessage.TRANSACTION_DECLINED.getMessage(),e);		
		}
		finally {
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				throw new ApplicationException(BankMessage.COMMIT_ERROR.getMessage(),e);
			}
		}	
	}
	protected void depositAmount(long accountNumber,double amount) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.BALANCE).append(SpecialCharacters.EQUALS).append(TableProp.BALANCE).append(SpecialCharacters.PLUS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setDouble(1,amount);
			prepStatement.setLong(2,accountNumber);
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
	protected void withdrawAmount(long accountNumber,double amount) throws ApplicationException {
		checkConnection();
		double balance=checkBalance(accountNumber);
		if(balance>=amount) {
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.BALANCE).append(SpecialCharacters.EQUALS).append(TableProp.BALANCE).append(SpecialCharacters.MINUS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setDouble(1,amount);
			prepStatement.setLong(2,accountNumber);
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
		}
		else {
			throw new ApplicationException(BankMessage.BALANCE_ISSUE.getMessage());		
		}
	}
	protected void addTransaction(Transaction transaction) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(transaction);
		long accountNumber=transaction.getPrimaryAccount();
		double balance=checkBalance(accountNumber);
		StringBuilder query=new StringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(TableProp.TXN_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.TXN_ID).append(SpecialCharacters.COMMA);
		query.append(TableProp.PRIMARY_ACCOUNT).append(SpecialCharacters.COMMA);
		query.append(TableProp.SECONDARY_ACCOUNT).append(SpecialCharacters.COMMA);
		query.append(TableProp.AMOUNT).append(SpecialCharacters.COMMA);
		query.append(TableProp.TXN_TYPE).append(SpecialCharacters.COMMA);
		query.append(TableProp.DESCRIPTION).append(SpecialCharacters.COMMA);
		query.append(TableProp.TXN_STATUS).append(SpecialCharacters.COMMA);
		query.append(TableProp.BALANCE).append(SpecialCharacters.COMMA);
		query.append(TableProp.TIMEMILLISECONDS).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement preparedStatement = connect.prepareStatement(sql)){
			preparedStatement.setString(1,transaction.getTxnId());
			preparedStatement.setLong(2,accountNumber);
			preparedStatement.setLong(3,transaction.getSecondaryAccount());
			preparedStatement.setDouble(4,transaction.getAmount());
			preparedStatement.setString(5,transaction.getTxnType());
			preparedStatement.setString(6,transaction.getDescription());
			preparedStatement.setString(7,transaction.getTxnStatus());
			preparedStatement.setDouble(8,balance);
			preparedStatement.setLong(9,transaction.getTimeInMillis());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
	protected int getTransactionId() throws ApplicationException {
		checkConnection();
        int transactionId = 0;
        StringBuilder query=new StringBuilder(SpecialCharacters.OPEN_CURLY_BRACKET).append(SQLKeywords.CALL).append(SpecialCharacters.SPACE);
        query.append(TableProp.COUNTER_PROCEDURE).append(SpecialCharacters.OPEN_PARENTHESIS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS);
        query.append(SpecialCharacters.CLOSE_CURLY_BRACKET);
        try (CallableStatement callableStmt = connect.prepareCall(query.toString())) {
            callableStmt.registerOutParameter(1, Types.INTEGER);
            callableStmt.execute();
            transactionId = callableStmt.getInt(1);
        } 
        catch (SQLException e) {
        	throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);	
        }
        return transactionId;
    }
	protected boolean checkAccount(long accountNumber) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.ACCOUNT_STATUS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.ACCOUNT_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.ACCOUNT_NUMBER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		try(PreparedStatement preparedStatement = connect.prepareStatement(query.toString())){
			preparedStatement.setLong(1,accountNumber);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					if(resultSet.getInt(TableProp.ACCOUNT_STATUS)==Status.BLOCKED.getStatusCode()) {
						return false;
					}
					return true;
				}
				else {
					return false;
				}
			}
		}
		catch (SQLException e) {
        	throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);	
        }
	}

}
