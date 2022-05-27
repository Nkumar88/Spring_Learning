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
		cus.setId("106");
		cus.setFirstName("Rishabh");
		cus.setLastName("Pant");
		cus.setCity("1");
		
		xmlops.createCustomer(cus);
	}

}
