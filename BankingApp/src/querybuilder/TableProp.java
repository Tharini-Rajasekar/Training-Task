package querybuilder;

public interface TableProp {

	String BRANCH_TABLE="branch_details";//table
	String BRANCH_ID="Branch_ID";
    String IFSC="IFSC_Code";
    String LOCATION="Location";
    String MANAGER="Manager";
    String USER_TABLE="user_details";//table
    String USER_ID="ID";
    String NAME = "Name";
    String DOB= "DOB";
    String GENDER="Gender";
    String EMAIL="Email";
    String MOBILE = "Mobile";
    String USER_LEVEL="User_Level";
    String USER_STATUS="User_Status";
    String PASSWORD="Password";
    String CUSTOMER_TABLE="customer_details";//table
    String AADHAR="Aadhar_Number";
    String PAN="PAN_Number";
    String EMPLOYEE_TABLE="employee_details";//table
    //String ACCESS_LEVEL="Access_Level";
    String ACCOUNT_TABLE="account_details";//table
    String ACCOUNT_NUMBER="Account_Number";
    String ACCOUNT_TYPE="Account_Type";
    String BALANCE="Balance";
    String ACCOUNT_STATUS="Account_Status";
    String TXN_TABLE="transaction_details"; 
    String TXN_ID="TXN_ID";
    String TXN_ID_CODE="BBTXN";
    String PRIMARY_ACCOUNT="Primary_Account";
    String SECONDARY_ACCOUNT="Secondary_Account";
    String TXN_TYPE="TXN_Type";
    String TIMEMILLISECONDS="TimeMilliSeconds";
    String DESCRIPTION="Description";
    String AMOUNT="Amount";
    String TXN_STATUS="TXN_Status";
    String SUCCESS="Success";
    String FAILED="Failed";
    String COUNTER_PROCEDURE="GetAndIncrementTransactionId";
    String ACTIVE="Active";
    String BLOCKED="Blocked";
    String CUSTOMER="Customer";
    String EMPLOYEE="Employee";
    String ADMIN="Admin";
    int NAME_LENGTH=20;
    int LOCATION_LENGTH=20;
    int LENGTH=10;
    int WHOLE=12;
    int FRACTION=2;
    int PWD_LENGTH=60;
    int GENDER_LENGTH=6;
    int EMAIL_LENGTH=30;
    int ACCOUNT_CODE=1000000000;
}
