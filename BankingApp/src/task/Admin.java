package task;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import helperpojo.AccountDetails;
import helperpojo.BranchDetails;
import helperpojo.EmployeeDetails;
import helperpojo.UserDetails;
import querybuilder.SQLKeywords;
import querybuilder.SpecialCharacters;
import querybuilder.TableProp;
import util.ApplicationException;
import util.BankMessage;
import util.Helper;

public class Admin extends Employee{

	public void addBranch(List<BranchDetails> branches) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_TABLE).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.IFSC).append(SpecialCharacters.COMMA);
		query.append(TableProp.MANAGER).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_ID).append(SpecialCharacters.COMMA);
		query.append(TableProp.LOCATION).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			for(BranchDetails branch:branches) {
				prepStatement.setString(1,branch.getIfscCode());
				prepStatement.setString(2,branch.getManager());
				prepStatement.setInt(3,branch.getEmpId());
				prepStatement.setString(4,branch.getLocation());
				prepStatement.addBatch();
			}
			prepStatement.executeBatch();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
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
				prepStatement.setInt(5,user.getUserLevel());
				prepStatement.setLong(6,user.getMobile());
				prepStatement.addBatch();
			}
			prepStatement.executeBatch();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
	public void addEmployee(List<EmployeeDetails> employees) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(TableProp.EMPLOYEE_TABLE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(TableProp.USER_ID).append(SpecialCharacters.COMMA);
		query.append(TableProp.BRANCH_ID).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			for(EmployeeDetails employee:employees) {
				prepStatement.setInt(1,employee.getId());
				prepStatement.setInt(2,employee.getBranchId());
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
			prepStatement.setString(2,account.getAccountType());
			prepStatement.setDouble(3,account.getBalance());
			prepStatement.setInt(4,account.getBranchId());
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void deleteUser(int custId) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setLong(1,custId);
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void deleteBranch(int ifsc) throws ApplicationException {
		StringBuilder query=new StringBuilder(SQLKeywords.DELETE).append(SpecialCharacters.SPACE).append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.IFSC).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,ifsc);
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
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);

		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1,user.getUserStatus());
			prepStatement.setInt(2,user.getId());
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}
	public void updateBranchDetails(BranchDetails branch) throws ApplicationException {
		checkConnection();
		StringBuilder query=new StringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_TABLE).append(SpecialCharacters.SPACE).append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		query.append(TableProp.MANAGER).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query.append(TableProp.USER_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.BRANCH_ID).append(SpecialCharacters.EQUALS).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setString(1,branch.getManager());
			prepStatement.setInt(2,branch.getEmpId());
			prepStatement.setInt(3,branch.getBranchId());
			prepStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public void createTables() throws ApplicationException {
		String query1=TableCreator.buildUserTable();
		String query2=TableCreator.buildBranchTable();
		String query3=TableCreator.buildEmployeeTable();
		String query4=TableCreator.buildCustomerTable();
		String query5=TableCreator.buildAccountDetails();
		String query6=TableCreator.buildTranscationDetails();

		try(Statement statement=connect.createStatement()){
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.executeUpdate(query4);
			statement.executeUpdate(query5);
			statement.executeUpdate(query6);
		}
		catch(SQLException e) {
			throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
		}
	}

	public EmployeeDetails getEmployeeDetails(int empId) throws ApplicationException {
		checkConnection();
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.JOIN).append(SpecialCharacters.SPACE).append(TableProp.EMPLOYEE_TABLE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.PERIOD).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS);
		query.append(TableProp.EMPLOYEE_TABLE).append( SpecialCharacters.PERIOD).append(TableProp.USER_ID).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(TableProp.USER_TABLE).append(SpecialCharacters.PERIOD).append(TableProp.USER_ID).append(SpecialCharacters.EQUALS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE).append(SpecialCharacters.SEMICOLON);
		try(PreparedStatement prepStatement = connect.prepareStatement(query.toString())) {
			prepStatement.setLong(1,empId);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				EmployeeDetails employee=new EmployeeDetails();
				while(resultSet.next()) {
					employee.setName(resultSet.getString(TableProp.NAME));
					Date date=resultSet.getDate(TableProp.DOB);
					employee.setDob(date.toLocalDate());
					employee.setGender(resultSet.getString(TableProp.GENDER));
					employee.setEmail(resultSet.getString(TableProp.EMAIL));
					employee.setMobile(resultSet.getLong(TableProp.MOBILE));
					employee.setBranchId(resultSet.getInt(TableProp.BRANCH_ID));
					employee.setUserLevel(resultSet.getInt(TableProp.USER_LEVEL));
					employee.setUserStatus(resultSet.getInt(TableProp.USER_STATUS));
				}
				return employee;
			}
		}
			catch(SQLException e) {
				throw new ApplicationException(BankMessage.STATEMENT_ERROR.getMessage(),e);		
			}	
	}


}
