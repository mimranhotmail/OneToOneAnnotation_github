package net.viralpatel.hibernate;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	public static void main(String[] args) {

		System.out.println("Hibernate One-To-One example (Annotation)");
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();

		EmployeeDetail employeeDetail = new EmployeeDetail("10th Street", "LA", "San Francisco", "U.S.");
		
		Employee employee = new Employee("Nina", "Mayers", new Date(121212),
				"114-857-965");
		employee.setEmployeeDetail(employeeDetail);
		employeeDetail.setEmployee(employee);
		
		
		session.save(employee);

		
		List<Employee> employees = session.createQuery("from Employee").list();
		for (Employee employee1 : employees) {
			System.out.println(employee1.getFirstname() + " , "
					+ employee1.getLastname() + ", "
					+ employee1.getEmployeeDetail().getState());
		}

		session.getTransaction().commit();
      /*
        Query query = session.createQuery("update Employee set FirstName = :FirstName" +
                " where employee_Id = :employee_Id");
        query.setParameter("FirstName", " Ms. Nina");
        query.setParameter("employee_Id", "1");
        int result = query.executeUpdate();
        session.getTransaction().commit();
        System.out.println("Result "+result);
        */

        //Query query = session.createSQLQuery("select emp.employee_id from Employee emp INNER join EmployeeDetail empd where emp.employee_id=empd.employee_id");

        Query query = session.createSQLQuery("select * from phy_password");

        List list = query.list();
        System.out.println("Result "+list);

        System.out.println("From 1");
		session.close();

	}
}
