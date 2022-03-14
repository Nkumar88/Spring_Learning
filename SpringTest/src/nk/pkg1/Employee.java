package nk.pkg1;

public class Employee {
	int eid;
	String ename;
	int esalary;
	Address adrs;
	
	public Employee()
	{
		
	}
	public Employee(Address adrs)
	{
		this.adrs = adrs;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getEsalary() {
		return esalary;
	}

	public void setEsalary(int esalary) {
		this.esalary = esalary;
	}

	public Address getAdrs() {
		return adrs;
	}

	public void setAdrs(Address adrs) {
		this.adrs = adrs;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", esalary=" + esalary + ", adrs=" + adrs + "]";
	}
	
	

}
