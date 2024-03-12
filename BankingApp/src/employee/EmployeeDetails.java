package employee;

import helperpojo.UserDetails;

public class EmployeeDetails extends UserDetails{
	private int branchId;

	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
}
