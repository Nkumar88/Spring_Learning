package com.nk;

public class Main {

	public static void main(String[] args) {
		XMLCurd xmlops = new XMLCurd();
		Customer cust = xmlops.getCustomerbyId("101");
		if (cust.getId() != null)
			System.out.println(cust.toString());
		else
			System.out.println("Customer not found.");
		
		System.out.println(xmlops.getCustomerByFirstName("Sandra"));
		System.out.println(xmlops.getAllCustomer());
		Customer cus = new Customer();
		cus.setId("0");
		cus.setFirstName("Hardik");
		cus.setLastName("Pandya");
		cus.setCity("3");
		
		//xmlops.createCustomer(cus);
		//xmlops.deleteCustomer("108");
		xmlops.updateCustomer("110", "Virat", "Kohli", "4");
	}

}
