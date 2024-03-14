package interfaces;

import java.util.List;

import helperpojo.BranchDetails;
import helperpojo.EmployeeDetails;
import util.ApplicationException;

public interface AdminOperations extends EmployeeOperations {
	void addEmployee(List<EmployeeDetails> employees) throws ApplicationException;
	EmployeeDetails getEmployeeDetails(int empId) throws ApplicationException;
	void addBranch(List<BranchDetails> branches) throws ApplicationException;
	void deleteBranch(int ifsc) throws ApplicationException;
	void updateBranchDetails(BranchDetails branch) throws ApplicationException;
}
