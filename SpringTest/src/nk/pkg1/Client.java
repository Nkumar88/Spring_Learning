package nk.pkg1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address ad = new Address("Laagveld","Nieuw-Vennep",2151,"Netherlands");
		Employee emp = new Employee();
		
		emp.setEid(1001);
		emp.setEname("Nitesh Kumar");
		emp.setEsalary(64000);
		emp.setAdrs(ad);
		
		//System.out.println(ad);
		//System.out.println(emp);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
		Employee emp1 = (Employee)context.getBean("employee");
		Address ad1 = context.getBean("address", nk.pkg1.Address.class);
		
		System.out.println(emp1);
		System.out.println(ad1);

	}

}
