package task;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import dbconnection.Connector;
import dbconnection.DBConnection;
import helperenum.Status;
import helperpojo.UserDetails;
import interfaces.LoginOperations;
import querybuilder.SQLKeywords;
import querybuilder.SpecialCharacters;
import querybuilder.TableProp;
import util.ApplicationException;
import util.BankMessage;
import util.Helper;

public class Login implements LoginOperations{
	private Connection connect=null;
	private Connection connectToDB() throws ApplicationException {
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
	private void checkConnection() throws ApplicationException {
		if(connect==null) {
			throw new ApplicationException(BankMessage.DATABASE_CONNECTION_ERROR.getMessage());
		}
	}
	public void disconnect() throws ApplicationException{
		Helper.nullCheck(connect);
		try {
			connect.close();
		} 
		catch (SQLException e) {
			throw new ApplicationException(BankMessage.DISCONNECT_ERROR.getMessage(),e);
		}
	}
	public boolean login(UserDetails user) throws ApplicationException {
		String password=signIn(user.getId());
		return checkPassword(user.getPassword(),password);
	}
	
	public String signIn(int userId) throws ApplicationException {
		checkConnection();
		checkUserStatus(userId);
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.PASSWORD).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,userId);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				if(resultSet.next()) {
					return resultSet.getString(TableProp.PASSWORD);
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
	
	public int getUserLevel(int userId) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.USER_LEVEL).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,userId);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				if(resultSet.next()) {
					return resultSet.getInt(TableProp.USER_LEVEL);
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

	public int getBranchId(int userId) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.BRANCH_ID).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.EMPLOYEE_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,userId);
			try (ResultSet resultSet = prepStatement.executeQuery()) {
				if(resultSet.next()) {
					return resultSet.getInt(TableProp.BRANCH_ID);					
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
	public void signUp(UserDetails user) throws ApplicationException {
		Helper.nullCheck(user);
		checkUser(user);		
		addLoginPassword(user);
	}

	private boolean checkUser(UserDetails user) throws ApplicationException {
		Helper.nullCheck(user);
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE).append(TableProp.DOB).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.AND).append(SpecialCharacters.SPACE).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			Date date=Date.valueOf(user.getDob());
			prepStatement.setDate(1,date);
			prepStatement.setInt(2,user.getId());
			try(ResultSet resultSet = prepStatement.executeQuery()){
				if(resultSet.next()) {
					return true;
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

	private void addLoginPassword(UserDetails user) throws ApplicationException  {
		checkConnection();
		Helper.nullCheck(user);
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.PASSWORD).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement preparedStatement = connect.prepareStatement(sql)){
			preparedStatement.setString(1,user.getPassword());
			preparedStatement.setInt(2,user.getId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	private void checkUserStatus(int id) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(TableProp.USER_STATUS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		try(PreparedStatement preparedStatement = connect.prepareStatement(query.toString())){
			preparedStatement.setInt(1,id);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					if(resultSet.getInt(TableProp.USER_STATUS)==Status.BLOCKED.getStatusCode()) {
						throw new ApplicationException(BankMessage.USER_BLOCKED.getMessage());
					}
				}
			}
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	
	private String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	private boolean checkPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}

}
