package test;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.mindrot.jbcrypt.BCrypt;

import helperenum.Status;
import helperenum.UserLevel;
import helperpojo.AccountDetails;
import helperpojo.BranchDetails;
import helperpojo.CustomerDetails;
import helperpojo.EmployeeDetails;
import helperpojo.Transaction;
import helperpojo.UserDetails;
import querybuilder.TableProp;
import task.Admin;
import task.Customer;
import task.Employee;
import task.Login;
import util.ApplicationException;
import util.Helper;

public class BankTest {
	private static final Logger log = Logger.getLogger(BankTest.class.getName());
	public static void main(String[] args) {

		Scanner scanner=new Scanner(System.in);
		System.out.println(" WELCOME TO BILLIONAIRE BANK ");
		Login login=new Login();
		try {
			login.setConnection();
		} 
		catch (ApplicationException e) {
			logCause(e);
			System.out.println(e.getMessage());
		}
		int select1=1;
		while(select1>0) {
			System.out.println("Select Login Mode \n1.Sign Up \n2.Sign In");
			System.out.print("Enter your Preference: ");
			select1=scanner.nextInt();
			switch(select1) {
			case 1:{
				System.out.println("Enter your Credentials to Sign up");
				System.out.print("Enter your User ID: ");
				int id=scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter your Date of Birth (YYYY-MM-DD): ");
				String dob=scanner.nextLine();
				System.out.print("Enter your Password: ");
				String password=scanner.nextLine();		
				try {
					if(passwordValidation(password)) {
						String hashedPassword = hashPassword(password);
						UserDetails user=new UserDetails();
						user.setId(id);
						user.setDob(LocalDate.parse(dob));
						user.setPassword(hashedPassword);
						login.signUp(user);
						System.out.println("Successfully Signed In");
					}
					else {
						System.out.println("Password Should be atleast 8 characters and Consists of atleast one UpperCase,Number and SpecialCharacter");
					}
				} 
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;
			}
			case 2:{
				System.out.println("Enter your Credentials to Sign In");
				System.out.print("Enter your User ID: ");
				int userId=scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter your Password: ");
				String password=scanner.nextLine();
				try {
					String hashedPassword=login.signIn(userId);
					boolean passwordMatch = checkPassword(password, hashedPassword);
					if(passwordMatch) {
						int userLevel=login.getUserLevel(userId);
						if(userLevel==UserLevel.CUSTOMER.getLevelCode()) {
							System.out.println("Logged In Succesfully");
							Customer user=new Customer();
							user.setId(userId);
							user.setConnection();
							System.out.println("Select your Service: \n1.Check Balance \n2.Tranfer Fund \n3.Transaction History");
							int service=1;
							while(service>0) {
								System.out.print("Enter your Service: ");
								service=scanner.nextInt();
								scanner.nextLine();
								switch(service) {
								case 1:{
									try {
										System.out.println("Your Accounts:");
										List<Long> accounts=user.getAccounts();
										int count=0;
										for(Long account:accounts) {
											count++;
											System.out.println(count+" : "+account);
										}
										System.out.print("Choose your account");
										int account=scanner.nextInt();
										scanner.nextLine();
										long accountNumber=Helper.getElement(accounts, account-1);
										System.out.println("Your Available Account Balance :");
										System.out.println(user.checkBalance(accountNumber));
									}
									catch(ApplicationException e) {
										logCause(e);
										System.out.println(e.getMessage());
									}
									break;
								}
								case 2:{
									System.out.println("\n1.Within Bank \n2.Different Bank");
									int select=scanner.nextInt();
									scanner.nextLine();
									if(select==1) {
									try {
										System.out.println("Your Accounts:");
										List<Long> accounts=user.getAccounts();
										int count=0;
										for(Long account:accounts) {
											count++;
											System.out.println(count+" : "+account);
										}
										System.out.print("Choose your account");
										int account=scanner.nextInt();
										scanner.nextLine();
										long senderAccount=Helper.getElement(accounts, account-1);
										System.out.print("Enter Receiver Account: ");
										long receiverAccount=scanner.nextLong();
										System.out.print("Enter Your Amount to Transfer: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										System.out.print("Enter Your Description: ");
										String note=scanner.nextLine();
										Transaction transaction=new Transaction();
										transaction.setPrimaryAccount(senderAccount);
										transaction.setSecondaryAccount(receiverAccount);
										transaction.setAmount(amount);
										transaction.setDescription(note);
										transaction.setTimeInMillis(System.currentTimeMillis());
										transaction.setTxnType("Transfer");
										user.transferFund(transaction);
										System.out.println("Transaction Successful");   
									}
									catch(ApplicationException e) {
										logCause(e);
										System.out.println(e.getMessage());
									}
									}
									if(select==2) {
										System.out.println("1.Millionaire Bank \nEnter your preference");
										int bank=scanner.nextInt();
										switch(bank) {
										case 1:{
											try {
												System.out.println("Your Accounts:");
												List<Long> accounts=user.getAccounts();
												int count=0;
												for(Long account:accounts) {
													count++;
													System.out.println(count+" : "+account);
												}
												System.out.print("Choose your account");
												int account=scanner.nextInt();
												scanner.nextLine();
												long senderAccount=Helper.getElement(accounts, account-1);
												System.out.print("Enter Receiver Account: ");
												long receiverAccount=scanner.nextLong();
												System.out.print("Enter Your Amount to Transfer: ");
												double amount=scanner.nextDouble();
												scanner.nextLine();
												System.out.print("Enter Your Description: ");
												String note=scanner.nextLine();
												Transaction transaction=new Transaction();
												transaction.setPrimaryAccount(senderAccount);
												transaction.setSecondaryAccount(receiverAccount);
												transaction.setAmount(amount);
												transaction.setDescription(note);
												transaction.setTimeInMillis(System.currentTimeMillis());
												transaction.setTxnType("Transfer");
												user.transferFundAcrossBank(transaction);
												System.out.println("Transaction Successful");   
											}
											catch(ApplicationException e) {
												logCause(e);
												System.out.println(e.getMessage());
											}
											break;
										}
										default:{
											System.out.println("Invalid Option");
										}
										}
									}
									else {
										System.out.println("Invalid Option");
									}
									break;
									
								}
								case 3:{
									try {
										System.out.println("Your Accounts:");
										List<Long> accounts=user.getAccounts();
										int count=0;
										for(Long account:accounts) {
											count++;
											System.out.println(count+" : "+account);
										}
										System.out.print("Choose your account");
										int account=scanner.nextInt();
										scanner.nextLine();
										long accountNumber=Helper.getElement(accounts, account-1);
										int pageSize = 10; // Number of rows per page
										int offset = 0;
										List<Transaction> transactions=user.getTransactionDetails(accountNumber, pageSize, offset);
										for(Transaction transaction:transactions) {
											System.out.print("Transaction ID: "+transaction.getTxnId()+" | ");
											System.out.print("Account Number: "+transaction.getPrimaryAccount()+" | ");
											System.out.print("Secondary Account: "+transaction.getSecondaryAccount()+" | ");
											System.out.print("Transaction Type: "+transaction.getTxnType()+"  | ");
											System.out.print("Amount: "+transaction.getAmount()+"  | ");
											System.out.println("DateTime: "+getDateTime(transaction.getTimeInMillis())+" | ");
											System.out.print("Status: "+transaction.getTxnStatus()+" | ");
											System.out.print("Description: "+transaction.getDescription()+" | ");
											System.out.println("Account Balance: "+transaction.getBalance());
										}
									}
									catch(ApplicationException e) {
										logCause(e);
										System.out.println(e.getMessage());
									}
									break;
								}
								default:{
									System.out.println("Invalid Option");
								}
								}
							}
							user.disconnectDB();
						}
							if(userLevel==UserLevel.EMPLOYEE.getLevelCode()) {
								System.out.println("Logged In Succesfully");
								Employee user=new Employee();
								user.setId(userId);
								user.setBranchId(login.getBranchId(userId));
								user.setConnection();
								System.out.println("Select your Service: \n1.Add New User \n2.Add Customer \n3.Create New Account ");
								System.out.println("4.Check Balance \n5.Tranfer Fund \n6.Transaction History \n7.Deposit Fund");
								System.out.println("8.Withdraw Amount \n9.Customer ID \n10.Update Mobile \n11.Delete Account \n12.Delete Customer");
								System.out.println("13.Update User Status \n14.Update Account Status \n15.Get Accounts \n16.Get Customer Details");
								int service=1;
								while(service>0) {
									System.out.print("Enter your Service: ");
									service=scanner.nextInt();
									scanner.nextLine();
									switch(service) {
									case 1:{
										try {
											List<UserDetails> users=Helper.getArrayList();
											System.out.print("Enter the Number of Customers to add: ");
											int count=scanner.nextInt();
											scanner.nextLine();
											while(count>0) {
												UserDetails newUser=new UserDetails();
												System.out.print("Enter User Name: ");
												String name=scanner.nextLine();
												System.out.print("Enter User DOB(YYYY-MM-DD): ");
												String dob=scanner.nextLine();
												System.out.print("Enter User Gender ");
												String gender=scanner.nextLine();
												System.out.print("Enter User Email: ");
												String email=scanner.nextLine();
												System.out.print("Enter User Mobile: ");
												long mobile=scanner.nextLong();
												scanner.nextLine();

												newUser.setName(name);
												newUser.setDob(LocalDate.parse(dob));
												newUser.setGender(gender);
												newUser.setEmail(email);
												newUser.setMobile(mobile);
												Helper.addElement(users,newUser);
												count--;
											}
											user.addUser(users);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 2:{
										try {
											List<CustomerDetails> customers=Helper.getArrayList();
											System.out.print("Enter the Number of Customers to add: ");
											int count=scanner.nextInt();
											scanner.nextLine();
											while(count>0) {
												CustomerDetails customer=new CustomerDetails();
												System.out.print("Enter Customer ID: ");
												int custId=scanner.nextInt();
												scanner.nextLine();
												System.out.print("Enter Customer PAN Number: ");
												String pan=scanner.nextLine();
												System.out.print("Enter Customer Aadhar Number: ");
												long aadhar=scanner.nextLong();
												scanner.nextLine();

												customer.setId(custId);
												customer.setPan(pan);
												customer.setAadhar(aadhar);
												Helper.addElement(customers,customer);
												count--;
											}
											user.addCustomer(customers);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 3:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										System.out.print("Enter Account Type: ");
										String type=scanner.nextLine();
										System.out.print("Enter Initial Deposited: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										AccountDetails account=new AccountDetails();
										account.setId(custId);
										account.setAccountType(type);
										account.setBalance(amount);
										try {
											user.createAccount(account);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 4:{
										System.out.print("Enter the account Number: ");
										long accountNumber=scanner.nextLong();
										scanner.nextLine();
										try {
											System.out.println("Available Account Balance: "+user.checkBalance(accountNumber));
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 5:{
										System.out.println("Enter Sender AccountNumber: ");
										long senderAccount=scanner.nextLong();
										System.out.print("Enter Receiver Account: ");
										long receiverAccount=scanner.nextLong();
										System.out.print("Enter Your Amount to Transfer: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										System.out.print("Enter Your Description: ");
										String note=scanner.nextLine();
										Transaction transaction=new Transaction();
										transaction.setPrimaryAccount(senderAccount);
										transaction.setSecondaryAccount(receiverAccount); 
										transaction.setAmount(amount);
										transaction.setDescription(note);
										transaction.setTimeInMillis(System.currentTimeMillis());
										transaction.setTxnType("Transfer");
										try {
											user.transferFund(transaction);
											System.out.println("Transaction Successful");   
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 6:{
										try {
											System.out.print("Enter account Number: ");
											int accountNumber=scanner.nextInt();
											scanner.nextLine();
											int pageSize = 10; // Number of rows per page
											int offset=0;
											int pageNumber = 1; // Desired page number
											boolean nextFlag=true;
											while(nextFlag) {
												offset = (pageNumber - 1) * pageSize;
												pageNumber++;
												List<Transaction> transactions=user.getTransactionDetails(accountNumber, pageSize, offset);
												for(Transaction transaction:transactions) {
													System.out.print("Transaction ID: "+transaction.getTxnId()+" | ");
													System.out.print("Account Number: "+transaction.getPrimaryAccount()+" | ");
													System.out.print("Secondary Account: "+transaction.getSecondaryAccount()+" | ");
													System.out.print("Transaction Type: "+transaction.getTxnType()+"  | ");
													System.out.print("Amount: "+transaction.getAmount()+"  | ");
													System.out.println("DateTime: "+getDateTime(transaction.getTimeInMillis())+" | ");
													System.out.print("Status: "+transaction.getTxnStatus()+" | ");
													System.out.print("Description: "+transaction.getDescription()+" | ");
													System.out.println("Account Balance: "+transaction.getBalance());
												}
												System.out.println("Enter 1 for next page");
												System.out.println("Enter 2 to exit");
												int next=scanner.nextInt();
												scanner.nextLine();
												if(next!=1) {
													nextFlag=false;
												}
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;

									}
									case 7:{
										System.out.print("Enter Customer Account Number: ");
										long accountNumber=scanner.nextLong();
										System.out.print("Enter Amount deposited: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										Transaction deposit=new Transaction();
										deposit.setPrimaryAccount(accountNumber);
										deposit.setSecondaryAccount(accountNumber);
										deposit.setAmount(amount);
										deposit.setTimeInMillis(System.currentTimeMillis());
										deposit.setTxnType("Deposited");
										try {
											user.depositFund(deposit);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 8:{
										System.out.print("Enter Customer Account Number: ");
										long accountNumber=scanner.nextLong();
										System.out.print("Enter Amount To Withdraw: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										Transaction withdraw=new Transaction();
										withdraw.setPrimaryAccount(accountNumber);
										withdraw.setSecondaryAccount(accountNumber);
										withdraw.setAmount(amount);
										withdraw.setTimeInMillis(System.currentTimeMillis());
										withdraw.setTxnType("Withdrawal");
										try {
											user.withdrawFund(withdraw);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 9:{
										System.out.print("Enter Customer Aadhar Number: ");
										long aadhar=scanner.nextLong();
										scanner.nextLine();
										try {
											System.out.println("The Customer Id is: "+user.getCustomerId(aadhar));
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 10:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										System.out.print("Enter New Mobile Number: ");
										long mobile=scanner.nextLong();
										scanner.nextLine();
										CustomerDetails customer=new CustomerDetails();
										customer.setId(custId);
										customer.setMobile(mobile);
										try {
											user.updateMobile(customer);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 11:{
										System.out.print("Enter Customer Account Number: ");
										long accountNumber=scanner.nextLong();
										scanner.nextLine();
										try {
											user.deleteAccount(accountNumber);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 12:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										try {
											user.deleteCustomer(custId);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 13:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										System.out.println("Update Status \n1.Active \n2.Block");
										System.out.print("Select Status: ");
										int status=scanner.nextInt();
										scanner.nextLine();
										UserDetails customer=new UserDetails();
										customer.setId(custId);
										if(status==1) {
											customer.setUserStatus(Status.ACTIVE.getStatusCode());
										}
										if(status==2) {
											customer.setUserStatus(Status.BLOCKED.getStatusCode());
										}
										else {
											System.out.println("Invalid Status");
										}
										try {
											user.updateUserStatus(customer);
											System.out.println("Status Updated");
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 14:{
										System.out.print("Enter Account Number: ");
										long accountNumber=scanner.nextLong();
										System.out.println("Update Status \n1.Active \n2.Block");
										System.out.print("Select Status: ");
										int status=scanner.nextInt();
										scanner.nextLine();
										AccountDetails account=new AccountDetails();
										account.setAccountNumber(accountNumber);
										if(status==1) {
											account.setStatus(Status.ACTIVE.getStatusCode());
										}
										if(status==2) {
											account.setStatus(Status.BLOCKED.getStatusCode());
										}
										else {
											System.out.println("Invalid Status");
										}
										try {
											Helper.nullCheck(account.getStatus());
											user.updateAccountStatus(account);
											System.out.println("Status Updated");
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 15:{
										System.out.println("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										try {
											List<AccountDetails> accounts=user.getAccounts(custId);
											for(AccountDetails account:accounts) {
												System.out.print("Account Number: "+account.getAccountNumber()+" | ");
												System.out.print("Account Type: "+account.getAccountType()+" | ");
												System.out.print("Branch Id: "+account.getBranchId()+" | ");
												System.out.print("Account Balance "+account.getBalance()+" | ");
												System.out.println("Account Status: "+account.getStatus());
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 16:{
										System.out.println("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										try {
											CustomerDetails customer=user.getCustomerDetails(custId);
											System.out.println("Name: "+customer.getName());
											System.out.println("DOB: "+customer.getDob());
											System.out.println("Gender: "+customer.getGender());
											System.out.println("Email: "+customer.getEmail());
											System.out.println("Mobile: "+customer.getName());
											System.out.println("Aadhar Number: "+customer.getAadhar());
											System.out.println("PAN Number: "+customer.getPan());
											int level=customer.getUserStatus();
											if(level==Status.ACTIVE.getStatusCode()) {
												System.out.println("User Status: "+TableProp.ACTIVE);
											}
											if(level==Status.BLOCKED.getStatusCode()) {
												System.out.println("User Status: "+TableProp.BLOCKED);	
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									default:{
										System.out.println("Invalid Option");
									}
									}
								}
								user.disconnectDB();
							}
							if(userLevel==UserLevel.ADMIN.getLevelCode()){
								System.out.println("Logged In Succesfully");
								Admin user=new Admin();
								user.setId(userId);
								user.setConnection();
								System.out.println("Select your Service: \n1.Add New User \n2.Add Customer \n3.Create New Account ");
								System.out.println("4.Check Balance \n5.Tranfer Fund \n6.Transaction History \n7.Deposit Fund");
								System.out.println("8.Withdraw Amount \n9.Customer ID \n10.Update Mobile \n11.Delete Account \n12.Delete Customer");
								System.out.println("13.Update User Status \n14.Update Account Status");
								System.out.println("15.Add new Employee \n16.Add New Branch \n17.Delete User \n18.Delete Branch");
								System.out.println("19.Update Branch Details \n20.Get Accounts \n21.Get customer details \n22.get Employee details");
								int service=1;
								while(service>0) {
									System.out.print("Enter your Service: ");
									service=scanner.nextInt();
									scanner.nextLine();
									switch(service) {
									case 1:{
										try {
											List<UserDetails> users=Helper.getArrayList();
											System.out.print("Enter the Number of Customers to add: ");
											int count=scanner.nextInt();
											scanner.nextLine();
											while(count>0) {
												UserDetails newUser=new UserDetails();
												System.out.print("Enter User Name: ");
												String name=scanner.nextLine();
												System.out.print("Enter User DOB(YYYY-MM-DD): ");
												String dob=scanner.nextLine();
												System.out.print("Enter User Gender ");
												String gender=scanner.nextLine();
												System.out.print("Enter User Email: ");
												String email=scanner.nextLine();
												System.out.println("Enter User Type \n1.Customer \n2.Employee \n3.Admin: ");
												int authLevel=scanner.nextInt();
												scanner.nextLine();
												System.out.print("Enter User Mobile: ");
												long mobile=scanner.nextLong();
												scanner.nextLine();
												if(authLevel==1) {
													newUser.setUserLevel(UserLevel.CUSTOMER.getLevelCode());
												}
												if(authLevel==2) {
													newUser.setUserLevel(UserLevel.EMPLOYEE.getLevelCode());
												}
												if(authLevel==3) {
													newUser.setUserLevel(UserLevel.ADMIN.getLevelCode());
												}
												else {
													System.out.println("Invalid User Type");
												}
												newUser.setName(name);
												newUser.setDob(LocalDate.parse(dob));
												newUser.setGender(gender);
												newUser.setEmail(email);
												newUser.setMobile(mobile);
												Helper.nullCheck(newUser.getUserLevel());
												Helper.addElement(users,newUser);
												count--;
											}
											user.addUser(users);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 2:{
										try {
											List<CustomerDetails> customers=Helper.getArrayList();
											System.out.print("Enter the Number of Customers to add: ");
											int count=scanner.nextInt();
											scanner.nextLine();
											while(count>0) {
												CustomerDetails customer=new CustomerDetails();
												System.out.print("Enter Customer ID: ");
												int custId=scanner.nextInt();
												scanner.nextLine();
												System.out.print("Enter Customer PAN Number: ");
												String pan=scanner.nextLine();
												System.out.print("Enter Customer Aadhar Number: ");
												long aadhar=scanner.nextLong();
												scanner.nextLine();

												customer.setId(custId);
												customer.setPan(pan);
												customer.setAadhar(aadhar);
												Helper.addElement(customers,customer);
												count--;
											}
											user.addCustomer(customers);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 3:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										System.out.print("Enter Branch ID: ");
										int branch=scanner.nextInt();
										scanner.nextLine();
										System.out.print("Enter Account Type: ");
										String type=scanner.nextLine();
										System.out.print("Enter Initial Deposited: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										AccountDetails account=new AccountDetails();
										account.setId(custId);
										account.setAccountType(type);
										account.setBalance(amount);
										account.setBranchId(branch);
										try {
											user.createAccount(account);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 4:{
										System.out.print("Enter the account Number: ");
										long accountNumber=scanner.nextLong();
										scanner.nextLine();
										try {
											System.out.println("Available Account Balance: "+user.checkBalance(accountNumber));
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 5:{
										System.out.println("Enter Sender AccountNumber: ");
										long senderAccount=scanner.nextLong();
										System.out.print("Enter Receiver Account: ");
										long receiverAccount=scanner.nextLong();
										System.out.print("Enter Your Amount to Transfer: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										System.out.print("Enter Your Description: ");
										String note=scanner.nextLine();
										Transaction transaction=new Transaction();
										transaction.setPrimaryAccount(senderAccount);
										transaction.setSecondaryAccount(receiverAccount); 
										transaction.setAmount(amount);
										transaction.setDescription(note);
										transaction.setTimeInMillis(System.currentTimeMillis());
										transaction.setTxnType("Transfer");
										try {
											user.transferFund(transaction);
											System.out.println("Transaction Successful");   
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 6:{
										try {
											System.out.print("Enter account Number: ");
											int accountNumber=scanner.nextInt();
											scanner.nextLine();
											int pageSize = 10;
											int offset=0;
											int pageNumber = 1;
											boolean nextFlag=true;
											while(nextFlag) {
												offset = (pageNumber - 1) * pageSize;
												pageNumber++;
												List<Transaction> transactions=user.getTransactionDetails(accountNumber, pageSize, offset);
												for(Transaction transaction:transactions) {
													System.out.print("Transaction ID: "+transaction.getTxnId()+" | ");
													System.out.print("Account Number: "+transaction.getPrimaryAccount()+" | ");
													System.out.print("Secondary Account: "+transaction.getSecondaryAccount()+" | ");
													System.out.print("Transaction Type: "+transaction.getTxnType()+"  | ");
													System.out.print("Amount: "+transaction.getAmount()+"  | ");
													System.out.println("DateTime: "+getDateTime(transaction.getTimeInMillis())+" | ");
													System.out.print("Status: "+transaction.getTxnStatus()+" | ");
													System.out.print("Description: "+transaction.getDescription()+" | ");
													System.out.println("Account Balance: "+transaction.getBalance());
												}
												System.out.println("Enter 1 for next page");
												System.out.println("Enter 2 to exit");
												int next=scanner.nextInt();
												scanner.nextLine();
												if(next!=1) {
													nextFlag=false;
												}
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;

									}
									case 7:{
										System.out.print("Enter Customer Account Number: ");
										long accountNumber=scanner.nextLong();
										System.out.print("Enter Amount deposited: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										Transaction deposit=new Transaction();
										deposit.setPrimaryAccount(accountNumber);
										deposit.setSecondaryAccount(accountNumber);
										deposit.setAmount(amount);
										deposit.setTimeInMillis(accountNumber);
										deposit.setTxnType("Deposited");
										try {
											user.depositFund(deposit);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
									}
									case 8:{
										System.out.print("Enter Customer Account Number: ");
										long accountNumber=scanner.nextLong();
										System.out.print("Enter Amount To Withdraw: ");
										double amount=scanner.nextDouble();
										scanner.nextLine();
										Transaction withdraw=new Transaction();
										withdraw.setPrimaryAccount(accountNumber);
										withdraw.setSecondaryAccount(accountNumber);
										withdraw.setAmount(amount);
										withdraw.setTimeInMillis(accountNumber);
										withdraw.setTxnType("Withdrawal");
										try {
											user.withdrawFund(withdraw);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 9:{
										System.out.print("Enter Customer Aadhar Number: ");
										long aadhar=scanner.nextLong();
										scanner.nextLine();
										try {
											System.out.println("The Customer Id is: "+user.getCustomerId(aadhar));
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 10:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										System.out.print("Enter New Mobile Number: ");
										long mobile=scanner.nextLong();
										scanner.nextLine();
										CustomerDetails customer=new CustomerDetails();
										customer.setId(custId);
										customer.setMobile(mobile);
										try {
											user.updateMobile(customer);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 11:{
										System.out.print("Enter Customer Account Number: ");
										long accountNumber=scanner.nextLong();
										scanner.nextLine();
										try {
											user.deleteAccount(accountNumber);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 12:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										try {
											user.deleteCustomer(custId);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 13:{
										System.out.print("Enter Customer ID: ");
										int custId=scanner.nextInt();
										System.out.println("Update Status \n1.Active \n2.Block");
										System.out.print("Select Status: ");
										int status=scanner.nextInt();
										scanner.nextLine();
										UserDetails customer=new UserDetails();
										customer.setId(custId);
										if(status==1) {
											customer.setUserStatus(Status.ACTIVE.getStatusCode());
										}
										if(status==2) {
											customer.setUserStatus(Status.BLOCKED.getStatusCode());
										}
										else {
											System.out.println("Invalid Status");
										}
										try {
											user.updateUserStatus(customer);
											System.out.println("Status Updated");
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 14:{
										System.out.print("Enter Account Number: ");
										long accountNumber=scanner.nextLong();
										System.out.println("Update Status \n1.Active \n2.Block");
										System.out.print("Select Status: ");
										int status=scanner.nextInt();
										scanner.nextLine();
										AccountDetails account=new AccountDetails();
										account.setAccountNumber(accountNumber);
										if(status==1) {
											account.setStatus(Status.ACTIVE.getStatusCode());
										}
										if(status==2) {
											account.setStatus(Status.BLOCKED.getStatusCode());
										}
										else {
											System.out.println("Invalid Status");
										}
										try {
											Helper.nullCheck(account.getStatus());
											user.updateAccountStatus(account);
											System.out.println("Status Updated");
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 15:{
										System.out.print("Enter the number of Employees to add: ");
										int count=scanner.nextInt();
										scanner.nextLine();
										try {
											List<EmployeeDetails> empDetails=Helper.getArrayList();
											for(int i=1;i<=count;i++) {
												System.out.println("Record "+i);
												EmployeeDetails emp=new EmployeeDetails();
												System.out.print("Enter Employee User ID: ");
												int empId=scanner.nextInt();
												System.out.print("Enter Employee's Branch ID: ");
												int branch=scanner.nextInt();
												scanner.nextLine();
												emp.setId(empId);
												emp.setBranchId(branch);
												Helper.addElement(empDetails,emp);
											}
											user.addEmployee(empDetails);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 16:{
										System.out.print("Enter the number of Branches to add: ");
										int count=scanner.nextInt();
										scanner.nextLine();
										try {
											List<BranchDetails> branches=Helper.getArrayList();

											for(int i=1;i<=count;i++){
												BranchDetails branch=new BranchDetails();
												System.out.print("Enter Branch IFSC code: ");
												String ifsc=scanner.nextLine();
												System.out.print("Enter the Branch Location: ");
												String location=scanner.nextLine();
												System.out.print("Enter Branch Manager Name: ");
												String name=scanner.nextLine();
												System.out.print("Enter Branch Manager User ID: ");
												int empId=scanner.nextInt();
												scanner.nextLine();
												branch.setIfscCode(ifsc);
												branch.setLocation(location);
												branch.setManager(name);
												branch.setEmpId(empId);
												Helper.addElement(branches,branch);
											}
											user.addBranch(branches);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 17:{
										System.out.print("Enter Employee ID: ");
										int empId=scanner.nextInt();
										scanner.nextLine();
										try {
											user.deleteUser(empId);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 18:{
										System.out.print("Enter Branch Id: ");
										int branch=scanner.nextInt();
										scanner.nextLine();
										try {
											user.deleteBranch(branch);
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 19:{
										System.out.print("Enter Branch Id: ");
										int branch=scanner.nextInt();
										scanner.nextLine();
										System.out.print("Enter Manager Name: ");
										String name=scanner.nextLine();
										System.out.print("Enter Manager User Name: ");
										int empId=scanner.nextInt();
										scanner.nextLine();
										BranchDetails updateBranch=new BranchDetails();
										updateBranch.setBranchId(branch);
										updateBranch.setManager(name);
										updateBranch.setEmpId(empId);
										try {
											user.updateBranchDetails(updateBranch);
											System.out.println("Updated Successfully");
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 20:{
										System.out.println("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										try {
											List<AccountDetails> accounts=user.getAccounts(custId);
											for(AccountDetails account:accounts) {
												System.out.print("Account Number: "+account.getAccountNumber()+" | ");
												System.out.print("Account Type: "+account.getAccountType()+" | ");
												System.out.print("Branch Id: "+account.getBranchId()+" | ");
												System.out.print("Account Balance "+account.getBalance()+" | ");
												System.out.println("Account Status: "+account.getStatus());
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 21:{
										System.out.println("Enter Customer ID: ");
										int custId=scanner.nextInt();
										scanner.nextLine();
										try {
											CustomerDetails customer=user.getCustomerDetails(custId);
											System.out.println("Name: "+customer.getName());
											System.out.println("DOB: "+customer.getDob());
											System.out.println("Gender: "+customer.getGender());
											System.out.println("Email: "+customer.getEmail());
											System.out.println("Mobile: "+customer.getMobile());
											System.out.println("Aadhar Number: "+customer.getAadhar());
											System.out.println("PAN Number: "+customer.getPan());
											int status=customer.getUserStatus();
											if(status==Status.ACTIVE.getStatusCode()) {
												System.out.println("User Status: "+TableProp.ACTIVE);
											}
											if(status==Status.BLOCKED.getStatusCode()) {
												System.out.println("User Status: "+TableProp.BLOCKED);	
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									case 22:{
										System.out.println("Enter Employee ID: ");
										int empId=scanner.nextInt();
										scanner.nextLine();
										try {
											EmployeeDetails employee=user.getEmployeeDetails(empId);
											System.out.println("Name: "+employee.getName());
											System.out.println("DOB: "+employee.getDob());
											System.out.println("Gender: "+employee.getGender());
											System.out.println("Email: "+employee.getEmail());
											System.out.println("Mobile: "+employee.getMobile());
											System.out.println("Branch Id: "+employee.getBranchId());
											int status=employee.getUserStatus();
											if(status==Status.ACTIVE.getStatusCode()) {
												System.out.println("User Status: "+TableProp.ACTIVE);
											}
											if(status==Status.BLOCKED.getStatusCode()) {
												System.out.println("User Status: "+TableProp.BLOCKED);	
											}
											int level=employee.getUserLevel();
											if(level==UserLevel.EMPLOYEE.getLevelCode()) {
												System.out.println("UserLevel: "+TableProp.EMPLOYEE);
											}
											if(level==UserLevel.ADMIN.getLevelCode()) {
												System.out.println("UserLevel: "+TableProp.ADMIN);
											}
										}
										catch(ApplicationException e) {
											logCause(e);
											System.out.println(e.getMessage());
										}
										break;
									}
									default:{
										System.out.println("Invalid Option");
									}
									}
								}
								user.disconnectDB();
							}
							else {
								System.out.println("Unauthorized User!");
							}
					}
					else {
						System.out.println("Incorrect Password");
					}
				}
				catch (ApplicationException e) {
					logCause(e);
					System.out.println(e.getMessage());
				}
				break;

			}
			default:{
				System.out.println("Invalid Option");
			}
			}
		}
		try {
			login.disconnectDB();
		}
		catch (ApplicationException e) {
			logCause(e);
			System.out.println(e.getMessage());
		}
		scanner.close();
	}
	private static boolean passwordValidation(String input) throws ApplicationException{
		Helper.nullCheck(input);
		String regEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!#$%&‘*+–/=?^_`.{|}~@]).{8,}$";
		return input.matches(regEx);
	}
	private static String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	private static boolean checkPassword(String plainPassword, String hashedPassword) {
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
	private static void logCause(Exception exp) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("BankLog.txt", true);
			fileHandler.setFormatter(new SimpleFormatter());
			log.setLevel(Level.SEVERE);
			log.setUseParentHandlers(false);
			log.addHandler(fileHandler);
			log.log(Level.SEVERE, "Exception occurred: " + exp.getMessage(), exp.getCause());
		}
		catch (SecurityException | IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (fileHandler != null) {
				fileHandler.close();
			}
		}
	}	
	private static ZonedDateTime getDateTime(long timeInMillis) {
		Instant instant=Instant.ofEpochMilli(timeInMillis);
		return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
	}
}
