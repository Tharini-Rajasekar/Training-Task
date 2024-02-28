package task;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import util.Helper;
import util.ApplicationException;

public class DBTask {

	private Connection connect=null;

	private Connection connectToDB(DBConnector object) throws ApplicationException {
		try {
			Helper.nullCheck(object);
			String driver=DBUtilities.getDriver(object.getServiceProvider());
			String url = DBUtilities.getUrl(object);
			String userName=object.getUserName();
			String password=object.getPassword();
			Class.forName(driver);
			return DriverManager.getConnection(url, userName, password);
		}
		catch(ClassNotFoundException | SQLException e){
			throw new ApplicationException(ErrorMessage.DATABASE_CONNECTION_ERROR.getMessage(),e);
		}
	}
	public void setConnection(DBConnector object) throws ApplicationException{
		if(connect==null) {
			connect=connectToDB(object);
		}
	}
	private void checkConnection() throws ApplicationException {
		if(connect==null) {
			throw new ApplicationException(ErrorMessage.DATABASE_CONNECTION_ERROR.getMessage());
		}
	}
	public void createTable(String tableName) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(tableName);
		String query=DBUtilities.buildEmployeeTable(tableName);
		try (Statement statement = connect.createStatement()){
			statement.executeUpdate(query);
		} 
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public void createChildTable(String childTable,String parentTable) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(parentTable);
		Helper.nullCheck(childTable);
		String query=DBUtilities.buildDependentTable(childTable,parentTable);
		try (Statement statement = connect.createStatement()){
			statement.executeUpdate(query);
		} 
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public void insertAll(String tableName,List<Employee> employee) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(employee);
		Helper.nullCheck(tableName);
		StringBuilder query1=Helper.getStringBuilder(SQLKeywords.INSERT_INTO);
		query1.append(SpecialCharacters.SPACE);
		query1.append(tableName).append(SpecialCharacters.OPEN_PARENTHESIS);
		query1.append(TableDetails.NAME).append(SpecialCharacters.COMMA);
		query1.append(TableDetails.DEPARTMENT).append(SpecialCharacters.COMMA);
		query1.append(TableDetails.EMAIL).append(SpecialCharacters.COMMA);
		query1.append(TableDetails.MOBILE).append(SpecialCharacters.CLOSE_PARENTHESIS);
		query1.append(SpecialCharacters.SPACE).append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql = query1.toString();
		try(PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			for (Employee record : employee) {
				prepStatement.setString(1, record.getName());
				prepStatement.setString(2, record.getDepartment());
				prepStatement.setString(3, record.getEmail());
				prepStatement.setLong(4, record.getMobile());
				prepStatement.addBatch();
			}
			prepStatement.executeBatch();
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public void insertInChild(String tableName,List<Dependent> dependent) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(dependent);
		Helper.nullCheck(tableName);
		StringBuilder query1=Helper.getStringBuilder(SQLKeywords.INSERT_INTO);
		query1.append(SpecialCharacters.SPACE);
		query1.append(tableName).append(SpecialCharacters.OPEN_PARENTHESIS);
		query1.append(TableDetails.EMP_ID).append(SpecialCharacters.COMMA);
		query1.append(TableDetails.DEPENDENT_NAME).append(SpecialCharacters.COMMA);
		query1.append(TableDetails.AGE).append(SpecialCharacters.COMMA);
		query1.append(TableDetails.RELATION).append(SpecialCharacters.CLOSE_PARENTHESIS);
		query1.append(SpecialCharacters.SPACE).append(SQLKeywords.VALUES).append(SpecialCharacters.OPEN_PARENTHESIS);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.COMMA);
		query1.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SEMICOLON);
		String sql = query1.toString();
		try (PreparedStatement prepStatement = connect.prepareStatement(sql)){
			for(Dependent record:dependent) {
				prepStatement.setInt(1, record.getEmpId());
				prepStatement.setString(2,record.getName());
				prepStatement.setInt(3,record.getAge());
				prepStatement.setString(4,record.getRelation());
				prepStatement.addBatch();
			}
			prepStatement.executeBatch();
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public List<Employee> getRecord(Map<String,String> condition,String tableName,String operator) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(condition);
		Helper.nullCheck(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT);
		query.append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		String query2=buildWhereCondition(tableName,condition,operator);
		query.append(query2).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try (PreparedStatement prepStatement = connect.prepareStatement(sql);
				ResultSet resultSet = prepStatement.executeQuery()){
			List<Employee> result=getRecord(resultSet);
			return result;
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(),e);
		}
	}
	private String buildWhereCondition(String tableName,Map<String,String>condition,String operator) throws ApplicationException {
		Map<String, String> details = getFieldDetails(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		int count=0;
		int size=Helper.getSize(condition);
		String key;
		String value;
		String dataType;
		for(Map.Entry<String, String> entry : condition.entrySet())	{
			key=entry.getKey();
			value=entry.getValue();
			query.append(key).append(SpecialCharacters.EQUALS);
			dataType=getDataType(details,key);
			if (SQLDataTypes.VARCHAR.equalsIgnoreCase(dataType)) {
				query.append(SpecialCharacters.SINGLE_QUOTE).append(value).append(SpecialCharacters.SINGLE_QUOTE);
			} 
			else {
				query.append(value);
			}
			count++;
			if (count < size) {
				Helper.nullCheck(operator);
				query.append(SpecialCharacters.SPACE);
				if(operator.equalsIgnoreCase(SQLKeywords.AND)) {
					query.append(SQLKeywords.AND);
				}
				else if(operator.equalsIgnoreCase(SQLKeywords.OR)) {
					query.append(SQLKeywords.OR);
				}
				else {
					throw new ApplicationException(ErrorMessage.OPERATOR_ERROR.getMessage());
				}
				query.append(SpecialCharacters.SPACE);
			}
		}
		return query.toString();
	}
	public List<Employee> getRecord(String query,int limit) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(query);
		try(PreparedStatement prepStatement = connect.prepareStatement(query)) {
			prepStatement.setInt(1,limit);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				List<Employee> result=getRecord(resultSet);
				return result;
			}
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(),e);
		}
	}
	private List<Employee> getRecord(ResultSet resultSet) throws ApplicationException{
		Helper.nullCheck(resultSet);
		List<Employee> result=Helper.getArrayList();
		try {
			while (resultSet.next()) {
				Employee employee=new Employee();
				employee.setId(resultSet.getInt(TableDetails.EMP_ID));
				employee.setName(resultSet.getString(TableDetails.NAME));
				employee.setDepartment(resultSet.getString(TableDetails.DEPARTMENT));
				employee.setEmail(resultSet.getString(TableDetails.EMAIL));
				employee.setMobile(resultSet.getLong(TableDetails.MOBILE));
				Helper.addElement(result,employee);
			}
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(),e);
		}
		return result;
	}
	public List<Employee> getRecords(int limit,String tableName) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		List<Employee> result=getRecord(sql,limit);
		return result;
	}
	public List<Employee> sortAsc(String tableName,int limit,String column) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(column);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ORDER_BY).append(SpecialCharacters.SPACE);
		query.append(column).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		List<Employee> result=getRecord(sql,limit);
		return result;
	}
	public List<Employee> sortDesc(String tableName,int limit,String column) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(column);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ORDER_BY).append(SpecialCharacters.SPACE);
		query.append(column).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DESCENDING).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		List<Employee> result=getRecord(sql,limit);
		return result;
	}	

	//above using POJO

	public void updateRecord(String tableName,Map<String,String>set,Map<String,String>condition,String operator)throws ApplicationException{
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(set);
		Helper.nullCheck(condition);
		Map<String, String> details = getFieldDetails(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.UPDATE).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.SET).append(SpecialCharacters.SPACE);
		int count=0;
		int size=Helper.getSize(set);
		String key;
		String value;
		String dataType;
		for(Map.Entry<String, String> entry : set.entrySet())	{
			key=entry.getKey();
			value=entry.getValue();
			query.append(key).append(SpecialCharacters.EQUALS);
			dataType=getDataType(details,key);
			if (SQLDataTypes.VARCHAR.equalsIgnoreCase(dataType)) {
				query.append(SpecialCharacters.SINGLE_QUOTE).append(value).append(SpecialCharacters.SINGLE_QUOTE);
			} 
			else {
				query.append(value);
			}
			count++;
			if (count < size) {
				query.append(SpecialCharacters.COMMA);
			}
		}
		query.append(SpecialCharacters.SPACE);
		String query2=buildWhereCondition(tableName,condition,operator);
		query.append(query2).append(SpecialCharacters.SEMICOLON);
		String sql = query.toString();
		try(Statement statement = connect.createStatement()) {
			statement.executeUpdate(sql);
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public void deleteRecord(String tableName,Map<String,String>condition,String operator) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(condition);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.DELETE).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append( SpecialCharacters.SPACE);
		String query2=buildWhereCondition(tableName,condition,operator);
		query.append(query2).append(SpecialCharacters.SEMICOLON);
		String sql = query.toString();
		try (Statement statement = connect.createStatement()){
			statement.executeUpdate(sql);
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	private Map<Integer, List<Dependent>> getJoinRecord(ResultSet resultSet) throws ApplicationException {
	    Helper.nullCheck(resultSet);
	    Map<Integer, List<Dependent>> result = Helper.getHashMap();
	    try {
	        while (resultSet.next()) {
	            Dependent dependent = new Dependent();
	            dependent.setDepId(resultSet.getInt(TableDetails.DEP_ID));
	            dependent.setName(resultSet.getString(TableDetails.DEPENDENT_NAME));
	            dependent.setEmpId(resultSet.getInt(TableDetails.EMP_ID));
	            dependent.setAge(resultSet.getInt(TableDetails.AGE));
	            dependent.setRelation(resultSet.getString(TableDetails.RELATION));
                int id=dependent.getEmpId();
                List<Dependent> dependents=result.get(id);
//	            if (result.containsKey(id)) {
//	                result.get(id).add(dependent);
//	            } else {
//	                List<Dependent> dependents =Helper.getArrayList();
//	                dependents.add(dependent);
//	                result.put(id,dependents);
//	            }
	            if(dependents==null) {
	            	dependents=Helper.getArrayList();
	            	result.put(id,dependents);
	            }
	            dependents.add(dependent);
	            
	        }
	    } 
	    catch (SQLException e) {
	        throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(), e);
	    }
	    return result;
	}

	public Map<Integer,List<Dependent>> getJoinRecord(String parentTable,String childTable,int empId) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(parentTable);
		Helper.nullCheck(childTable);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE);
		query.append(parentTable).append(SpecialCharacters.PERIOD).append(TableDetails.EMP_ID).append(SpecialCharacters.COMMA).append(SpecialCharacters.SPACE);
		query.append(childTable).append(SpecialCharacters.PERIOD).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(parentTable).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.JOIN).append(SpecialCharacters.SPACE).append(childTable).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(parentTable).append(SpecialCharacters.PERIOD).append(TableDetails.EMP_ID).append(SpecialCharacters.EQUALS);
		query.append(childTable).append( SpecialCharacters.PERIOD).append( TableDetails.EMP_ID).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		query.append(parentTable).append(SpecialCharacters.PERIOD).append(TableDetails.EMP_ID).append(SpecialCharacters.EQUALS);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SPACE).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		try (PreparedStatement prepStatement= connect.prepareStatement(sql)){
			prepStatement.setInt(1, empId);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				Map<Integer,List<Dependent>> result=getJoinRecord(resultSet);
				return result;
			} 	
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	
	}
	public Map<Integer,List<Dependent>> getJoinRecord(String parentTable,String childTable,String column,int limit) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(parentTable);
		Helper.nullCheck(childTable);
		Helper.nullCheck(column);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS);
		query.append(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(parentTable).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ORDER_BY).append(SpecialCharacters.SPACE);
		query.append(column).append(SpecialCharacters.SPACE).append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE).append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.SUBQUERY).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.JOIN).append(SpecialCharacters.SPACE).append(childTable).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ON).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.SUBQUERY).append(SpecialCharacters.PERIOD).append(TableDetails.EMP_ID).append(SpecialCharacters.EQUALS);
		query.append(childTable).append( SpecialCharacters.PERIOD).append(TableDetails.EMP_ID).append(SpecialCharacters.SEMICOLON);
		
//	 SELECT * FROM (SELECT * FROM Employee ORDER BY name LIMIT 3 ) Subquery JOIN Dependent ON Subquery.Emp_ID = Dependent.Emp_ID;

		String sql=query.toString();
		try(PreparedStatement prepStatement= connect.prepareStatement(sql)) {
			prepStatement.setInt(1, limit);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				Map<Integer,List<Dependent>> result=getJoinRecord(resultSet);
				return result;
			} 
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public void disconnectDB() throws ApplicationException{
		Helper.nullCheck(connect);
		try {
			connect.close();
		} 
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DISCONNECT_ERROR.getMessage(),e);
		}
	}

	//to get details

	private Map<String, String> getFieldDetails(String tableName) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(tableName);
		Map<String, String> fieldDetails = Helper.getHashMap();   
		String columnName;
		String dataType;
		try {
			DatabaseMetaData metaData = connect.getMetaData();
			try(ResultSet resultSet = metaData.getColumns(null, null, tableName, null)){
				while (resultSet.next()) {
					columnName = resultSet.getString(ConnectionDetails.COLUMN_NAME);
					dataType = resultSet.getString(ConnectionDetails.TYPE_NAME);
					Helper.addElement(fieldDetails,columnName,dataType);
				}
			}
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(),e);
		}
		return fieldDetails;
	}
	private String getDataType(Map<String, String> map, String field) throws ApplicationException{
		Helper.nullCheck(map);
		Helper.nullCheck(field);
		for (String columnName : map.keySet()) {
			if (columnName.equalsIgnoreCase(field)) {
				return map.get(columnName);
			}
		}
		throw new ApplicationException(ErrorMessage.COLUMN_ERROR.getMessage());
	}


	//Repeated

	public List<Map<String, String>> getRecord(String tableName, int limit,String sql) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(sql);
		Map<String, String> details = getFieldDetails(tableName);
		List<Map<String, String>> resultList = Helper.getArrayList();
		try (PreparedStatement prepStatement = connect.prepareStatement(sql)) {
			prepStatement.setInt(1, limit);
			try(ResultSet resultSet = prepStatement.executeQuery()){
				while (resultSet.next()) {
					Map<String, String> row = Helper.getHashMap();
					for (String columnName : details.keySet()) {
						String columnValue = resultSet.getString(columnName);
						Helper.addElement(row, columnName, columnValue);
					}
					resultList=Helper.addElement(resultList, row);
				}
			} 
		}
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(),e);
		}
		return resultList;
	}
	public List<Map<String, String>> getRecords(String tableName, int limit) throws ApplicationException{ 
		Helper.nullCheck(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		return getRecord(tableName,limit,sql);
	}
	public List<Map<String, String>>  sortRecordAsc(String tableName, int limit,String column) throws ApplicationException{ 
		Helper.nullCheck(tableName);
		Helper.nullCheck(column);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ORDER_BY).append(SpecialCharacters.SPACE);
		query.append(column).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ASCENDING).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		return getRecord(tableName,limit,sql);   
	}
	public List<Map<String, String>>  sortRecordDes(String tableName, int limit,String column) throws ApplicationException{ 
		Helper.nullCheck(tableName);
		Helper.nullCheck(column);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.ORDER_BY).append(SpecialCharacters.SPACE);
		query.append(column).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.DESCENDING).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.LIMIT).append(SpecialCharacters.SPACE);
		query.append(SpecialCharacters.PLACEHOLDER).append(SpecialCharacters.SEMICOLON);
		String sql=query.toString();
		return getRecord(tableName, limit,sql);  
	}
	public void insert(String tableName, List<String> field, List<List<String>> listOfList) throws ApplicationException {
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(field);
		Helper.nullCheck(listOfList);
		Map<String, String> details = getFieldDetails(tableName);
		StringBuilder query =Helper.getStringBuilder(SQLKeywords.INSERT_INTO).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE).append(SpecialCharacters.OPEN_PARENTHESIS);
		int fieldCount = Helper.getSize(field);
		int count = 0;
		for (String columnName : field) {
			query.append(columnName);
			count++;
			if (count < fieldCount) {
				query.append(SpecialCharacters.COMMA);
			}
		}
		query.append(SpecialCharacters.CLOSE_PARENTHESIS).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.VALUES).append(SpecialCharacters.SPACE);
		count = 0;
		int entries=Helper.getSize(listOfList);
		for (List<String> list : listOfList) {
			query.append(SpecialCharacters.OPEN_PARENTHESIS);
			int valueIndex = 0;
			String columnName;
			String dataType;
			for (String value : list) {
				columnName= Helper.getElement(field, valueIndex);
				dataType = getDataType(details, columnName);
				if (SQLDataTypes.VARCHAR.equalsIgnoreCase(dataType)) {
					query.append(SpecialCharacters.SINGLE_QUOTE).append(value).append(SpecialCharacters.SINGLE_QUOTE);
				} 
				else {
					query.append(value);
				}
				valueIndex++;
				if (valueIndex < fieldCount) {
					query.append(SpecialCharacters.COMMA);
				}
			}
			query.append(SpecialCharacters.CLOSE_PARENTHESIS);
			count++;
			if (count < entries) {
				query.append(SpecialCharacters.COMMA);
			}
		}
		query.append(SpecialCharacters.SEMICOLON);
		String sql = query.toString();
		try(Statement statement = connect.createStatement()) {
			statement.executeUpdate(sql);
		} 
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.STATEMENT_ERROR.getMessage(),e);
		}
	}
	public List<Map<String, String>> getRecord(String tableName,Map<String,String> map,String operator) throws ApplicationException{
		checkConnection();
		Helper.nullCheck(tableName);
		Helper.nullCheck(map);
		Map<String, String> details = getFieldDetails(tableName);
		StringBuilder query=Helper.getStringBuilder(SQLKeywords.SELECT).append(SpecialCharacters.SPACE).append(SpecialCharacters.ASTERISK).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.FROM).append(SpecialCharacters.SPACE);
		query.append(tableName).append(SpecialCharacters.SPACE);
		query.append(SQLKeywords.WHERE).append(SpecialCharacters.SPACE);
		int count=0;
		int size=Helper.getSize(map);
		String key;
		String value;
		String dataType;
		for(Map.Entry<String, String> entry : map.entrySet())	{
			key=entry.getKey();
			value=entry.getValue();
			query.append(key).append(SpecialCharacters.EQUALS);
			dataType=getDataType(details,key);
			if (SQLDataTypes.VARCHAR.equalsIgnoreCase(dataType)) {
				query.append(SpecialCharacters.SINGLE_QUOTE).append(value).append(SpecialCharacters.SINGLE_QUOTE);
			} 
			else {
				query.append(value);
			}
			count++;
			if (count < size) {
				Helper.nullCheck(operator);
				query.append(SpecialCharacters.SPACE);
				if(operator.equalsIgnoreCase(SQLKeywords.AND)) {
					query.append(SQLKeywords.AND);
				}
				else if(operator.equalsIgnoreCase(SQLKeywords.OR)) {
					query.append(SQLKeywords.OR);
				}
				else {
					throw new ApplicationException(ErrorMessage.OPERATOR_ERROR.getMessage());
				}
				query.append(SpecialCharacters.SPACE);
			}
		}
		query.append(SpecialCharacters.SEMICOLON);
		String sql = query.toString();
		List<Map<String,String>>result=Helper.getArrayList();
		try (Statement statement = connect.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)){
			String columnValue;
			while (resultSet.next()) {
				Map<String,String> record =Helper.getHashMap();
				for (String columnName : details.keySet()) {
					columnValue = resultSet.getString(columnName);
					Helper.addElement(record, columnName, columnValue);
				}
				Helper.addElement(result, record);
			}
		} 
		catch (SQLException e) {
			throw new ApplicationException(ErrorMessage.DATA_RETRIEVAL_ERROR.getMessage(),e);
		}
		return result;
	}	
}


